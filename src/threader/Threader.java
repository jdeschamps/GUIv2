package threader;

import graph.Chart;
import graph.TimeChart;

import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingWorker;

import swing.GUIFrame;
import swing.Graph;
import swing.LogarithmicJSlider;
import device.MSystem;
import micromanager.MConfiguration;
import micromanager.Log;

public class Threader {
	
	MSystem sys_;
	Log log_;
	GUIFrame frame_;
	
	PImonitor pim_;
	QPDmonitor qpdm_;
	UVautomator uva_;
	
	UIupdater task;
	boolean running_ = false;
	
	public Threader(GUIFrame frame){
		pim_ = new PImonitor(null);
		qpdm_ = new QPDmonitor(null);
		frame_ = frame;
	}
	
	public Threader(MSystem sys, Log log, GUIFrame frame){

		sys_ = sys;
		log_ = log;
		frame_ = frame;
		
		initializeUpdaters();
	}

	private void initializeUpdaters() {
		pim_ = new PImonitor(sys_.getPIStage());
		qpdm_ = new QPDmonitor(sys_.getQPD());
		uva_ = new UVautomator(sys_.getLaser(MConfiguration.laserkeys[0]));
	}
	
	public boolean isRunning(){
		return running_;
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
		running_ = true;
	}

	public void startUpdater(String key){
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
	}


	////// it does not use propertychange which should be much better, investigate
	public class UIupdater extends SwingWorker<Integer,Double[]>{
		
		Double[] resultPI, resultQPD, resultUV;
		Graph uvg;
		Chart qpdg3;
		TimeChart pig, qpdg1, qpdg2;
		LogarithmicJSlider uvlgs;
		JTextField uvjtf;
		int counter;
		
		public UIupdater(){
			resultPI = new Double[2];
			resultQPD = new Double[4];
			resultUV = new Double[3];
		}

		@Override
		protected Integer doInBackground() throws Exception {
			int counter = 0;
			while(running_ && !isCancelled()){
				//System.out.println("Round "+counter);
				
				switch(counter%3){
				case 0:
					if(pim_.isRunning()){
						System.out.println("update pi");
						resultPI[0] = (double) 0;
						pim_.refresh();
						resultPI[1] = pim_.getOutput(0);
						System.out.println(resultPI[0]+" "+resultPI[1]);
						publish(resultPI);
					}
					break;
	
				case 1:
					if(qpdm_.isRunning()){
						System.out.println("update qpd: "+qpdm_.getNOutput());
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
						resultUV[1] = uva_.getOutput(0);
						resultUV[2] = uva_.getOutput(1);
						publish(resultUV);
					}
					break;
				}
				counter++;			
				
				if(counter==10000){
					counter =0;
				}
				
				Thread.sleep(20);
			}
			return 1;
		}
		
		  @Override
		  protected void process(List<Double[]> chunks) {
			  pig = frame_.getFocusGraph();
			  qpdg1 = frame_.getQPDGraph1();
			  qpdg2 = frame_.getQPDGraph2();
			  qpdg3 = frame_.getQPDGraph3();
			  uvg = frame_.getNGraph();
			  uvlgs = frame_.getUVSlider();
			  uvjtf = frame_.getUVtext();

			  System.out.println("Chunk size: "+chunks.size());
			  for(Double[] result : chunks){
				  System.out.println("In evt: "+result[0]+" "+result[1]);
				  switch(result[0].intValue()){
				  case 0:	// PI pos
					  if(result.length == 2){
						  pig.addPoint((int) (100*result[1]));
					  }
					  break;
				  case 1:	// QPD 
					  if(result.length == 4){
						  qpdg1.addPoint(result[1]);
						  qpdg2.addPoint(result[3]);
						  qpdg3.addPoint(result[1],result[2]);
					  }
					  break;
				  case 2:	// UV 
					  uvg.addPoint(result[1].intValue());

					  if(result[1] != 0){
						  uvlgs.setValue(result[1].intValue());
					  }
					  if(!frame_.isUVTextSelected()){
						  uvjtf.setText(result[1].toString());
					  }
					  break;
				  }
			  }
		  }
	}
}
