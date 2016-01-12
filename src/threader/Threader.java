package threader;

import graph.Chart;
import graph.TimeChart;
import gui.MainFrame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import device.MSystem;
import micromanager.MConfiguration;
import micromanager.Log;

public class Threader {
	
	MSystem sys_;
	Log log_;
	MainFrame frame_;
	
	PImonitor pim_;
	QPDmonitor qpdm_;
	UVautomator uva_;
	
	UIupdater task;
	boolean initialized_ = false;
	boolean running_ = false;
	int counter=0;
	DecimalFormat df;
	
	public Threader(MainFrame mainFrame){
		pim_ = new PImonitor(null);
		qpdm_ = new QPDmonitor(null);
		frame_ = mainFrame;
	}
	
	public Threader(MSystem sys, Log log, MainFrame frame){
		sys_ = sys;
		log_ = log;
		frame_ = frame;
	}

	private void initialize() {
		pim_ = new PImonitor(sys_.getPIStage());		
		qpdm_ = new QPDmonitor(sys_.getQPD());
		uva_ = new UVautomator(sys_.getLaser(MConfiguration.laserkeys[0]), sys_, log_, frame_.getActivateTab());
		initialized_ = true;
		
		DecimalFormat df = new DecimalFormat();//DecimalFormat.getInstance();
		df.setRoundingMode(RoundingMode.FLOOR);
		df.setMinimumFractionDigits(0);
		df.setMaximumFractionDigits(0);
		
		start();
	}

	public boolean isRunning(){
		return running_;
	}

	public boolean isInitialized(){
		return initialized_;
	}
	
	public void run(){
		task = new UIupdater();
		task.execute();               					////////////////////// not proper, change
	}
	
	public void start(){
		running_ = true;
		run();
	}
	
	public void stop(){
		if(isInitialized()){
			running_ = false;
			qpdm_.stop();
			pim_.stop();
			uva_.stop();
		}
	}

	public void startUpdater(String key){
		if(!isInitialized()){
			initialize();
		}
		if(key.equals("PI")){
			pim_.start();
		} else if(key.equals("QPD")){
			qpdm_.start();
		} else if(key.equals("UV")){
			uva_.start();
		} else {
			return;
		}

	}
	
	public void stopUpdater(String key){
		if(key.equals("PI")){
			pim_.stop();
		} else if(key.equals("QPD")){
			qpdm_.stop();
		} else if(key.equals("UV")){
			uva_.stop();
		}
		//if(!pim_.isRunning() && !qpdm_.isRunning() && !uva_.isRunning()){
		//}
	}


	////// it does not use propertychange which should be much better, investigate
	public class UIupdater extends SwingWorker<Integer,Double[]>{
		
		Double[] resultPI, resultQPD, resultUV;
		Chart qpdg;
		TimeChart pig, uvg;
		gui.LogarithmicJSlider uvlgs;
		JTextField uvjtf, uvcutoff;
		JSlider uvjsld;
		JProgressBar qpdpg;
		int counter, NMScounter;
		
		public UIupdater(){
			resultPI = new Double[2];
			resultQPD = new Double[4];
			resultUV = new Double[4];
			
			pig = frame_.getFocusGraph();
			qpdg = frame_.getQPDGraph();
			qpdpg = frame_.getProgressBar();
			uvg = frame_.getNGraph();
			uvlgs = frame_.getUVSlider();
			uvjtf = frame_.getUVtext();
			uvcutoff = frame_.getUVCutoff();
			uvjsld = frame_.getUVParamSlider();
		}

		@Override
		protected Integer doInBackground() throws Exception {
			int counter = 0;
			//int NMScounter = 0;
			while(running_ && !isCancelled()){
				switch(counter%3){
				case 0:
					if(pim_.isRunning()){
						System.out.println("update pi");
						resultPI[0] = (double) 0;
						pim_.refresh();
						resultPI[1] = pim_.getOutput(0);
						//System.out.println(resultPI[0]+" "+resultPI[1]);
						publish(resultPI);
					}
					break;
	
				case 1:
					if(qpdm_.isRunning()){
						System.out.println("update qpd");
						qpdm_.refresh();
						resultQPD[0] = 1.;
						for(int i=0;i<qpdm_.getNOutput();i++){
							resultQPD[i+1] = qpdm_.getOutput(i);
						}
						publish(resultQPD);
					}
					break;
	
				case 2:
					if(uva_.isRunning()){
						System.out.println("update uv");
						uva_.refresh();

						resultUV[0] = 2.;
						resultUV[1] = uva_.getOutput(0);	// N
						resultUV[2] = uva_.getOutput(1);	// pulse
						resultUV[3] = uva_.getOutput(2);	// cutoff
						publish(resultUV);
					}
					break;
				}
				counter++;			
				
				if(counter==10000){
					counter = 0;
				}
				
				Thread.sleep((long) Math.floor(sys_.getExposureTime()));
			}
			return 1;
		}
		
		  @Override
		  protected void process(List<Double[]> chunks) {
			  //System.out.println("Chunk size: "+chunks.size());
			  for(Double[] result : chunks){
				  //System.out.println("In evt: "+result[0]+" "+result[1]);
				  switch(result[0].intValue()){
				  case 0:	// PI pos
					  if(result.length == 2){
						  pig.addPoint(result[1]);
						  //counter++;
						  //pig.addPoint(counter);
					  }
					  break;
				  case 1:	// QPD 
					  if(result.length == 4){
						  if(result[1]<=700){
							  qpdpg.setValue((int) Math.round(result[1]));
						  } else {
							  qpdpg.setValue(700);
						  }
						  qpdg.addPoint(result[3],result[2]);
					  }
					  break;
				  case 2:	// UV 
					  int currpulse = (int) Double.parseDouble(uvcutoff.getText());
					  
					  // Refresh the graph
					  uvg.addPoint(result[1].intValue());

					  // Maximum of the sliders   																																/// having that every round is maybe not so great since exposure won't really change during activation
					  int max = 1000*sys_.getExposureTime() < MConfiguration.mojomaxpulse ? (int) (1000*sys_.getExposureTime()) : MConfiguration.mojomaxpulse;
					  if(max > 0){
						  uvlgs.setMaximum(max);
						  uvjsld.setMaximum(max);
					  } 
					  
					  // Update UV
					  if(frame_.isUVChecked() && result[2].intValue()!=currpulse){
						  uvjsld.setValue(result[2].intValue());
						  uvjtf.setText(String.valueOf(result[2].intValue()));
						  if(result[2] != 0 && result[2]<= max){										
							  uvlgs.setValue(result[2].intValue());
						  } else {
							  uvlgs.setValue(1);
						  }
					  }
					  
					  
					  /*if(!frame_.isUVTextSelected()){
						  uvjtf.setText(String.valueOf(result[2].intValue()));
					  }*/		////// not sure fit that was necessary or even efficient
					  
					  // Cutoff
					  if(frame_.isNewCutOff()){
						  uvcutoff.setText(Double.toString(round(result[3],2)));
						  frame_.setRequestOff();
					  }
					  
					  // Update NMS frame
					  if(frame_.isNMSChecked()){
						  NMScounter++;
						  if(NMScounter % 10 == 0){
							  frame_.setNMSImageProcessor(uva_.getNMSresult());
						  }
						  if(NMScounter == Integer.MAX_VALUE){
							  NMScounter = 0;
						  }
					  }
					  break;
				  }
			  }
		  }
	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
