package threader;

import graph.TimeChart;
import gui.MainFrame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import device.MSystem;
import micromanager.MConfiguration;
import micromanager.Log;

public class UVThreader {
	
	MSystem sys_;
	Log log_;
	MainFrame frame_;
	
	UVautomator uva_;
	
	UIupdater task;
	boolean initialized_ = false;
	boolean running_ = false;
	int counter=0;
	DecimalFormat df;
	
	public UVThreader(MainFrame mainFrame){
		frame_ = mainFrame;
	}
	
	public UVThreader(MSystem sys, Log log, MainFrame frame){
		sys_ = sys;
		log_ = log;
		frame_ = frame;
	}

	private void initialize() {
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
			uva_.stop();
		}
	}

	public void startUpdater(String key){
		if(!isInitialized()){
			initialize();
		}
		if(key.equals("UV")){
			uva_.start();
		} else {
			return;
		}
	}
	
	public void stopUpdater(String key){
		if(key.equals("UV")){
			uva_.stop();
		}
		//if(!pim_.isRunning() && !qpdm_.isRunning() && !uva_.isRunning()){
		//}
	}


	////// it does not use propertychange which should be much better, investigate
	public class UIupdater extends SwingWorker<Integer,Double[]>{
		
		Double[] resultUV;
		TimeChart uvg;
		gui.LogarithmicJSlider uvlgs;
		JTextField uvjtf, uvcutoff;
		JSlider uvjsld;
		int counter, NMScounter;
		
		public UIupdater(){
			resultUV = new Double[4];

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
				if(uva_.isRunning()){
					System.out.println("[UV] refresh UV");
					uva_.refresh();
					System.out.println("[UV] UV refreshed");

					resultUV[0] = 2.;
					resultUV[1] = uva_.getOutput(0);	// N
					resultUV[2] = uva_.getOutput(1);	// pulse
					resultUV[3] = uva_.getOutput(2);	// cutoff
					publish(resultUV);
					System.out.println("[UV] published results");
				}
				counter++;			
				
				if(counter==10000){
					counter = 0;
				}
				System.out.println("[UV] sleep");
				Thread.sleep((long) Math.floor(sys_.getExposureTime()));
				System.out.println("[UV] end sleeping");
			}
			return 1;
		}
		
		@Override
		protected void process(List<Double[]> chunks) {
			//System.out.println("Chunk size: "+chunks.size());
			for(Double[] result : chunks){
				//System.out.println("In evt: "+result[0]+" "+result[1]);
				switch(result[0].intValue()){
				case 2:	// UV 															////// very unnecessary now
					System.out.println("[UV] will update the GUI");
					int currpulse = sys_.getUVPulse();
					System.out.println("[UV] got current pulse");
						
					// Refresh the graph
					System.out.println("[UV] will refresh graph with new point: "+result[1]);
					System.out.println("[UV] will refresh graph with new point (int value): "+result[1].intValue());
					uvg.addPoint(result[1]);
					System.out.println("[UV] refresh graph with new point");
					
					// Maximum of the sliders   																										/// having that every round is maybe not so great since exposure won't really change during activation
					int max = (int) (1000*sys_.getExposureTime());
					System.out.println("[UV] max pulse: "+max);
					uvjsld.setMaximum(max);
					uvlgs.setMaximum(max);
					  
					// Update UV
					System.out.println("[UV] update UV");

					if(sys_.isCameraAcquiring() && frame_.isUVChecked()){
						System.out.println("[UV] camera is acquiring, UV is checked and result is different than current value");
						System.out.println("[UV] result: "+result[2].intValue()+" and current: "+currpulse);

						int res = result[2].intValue();
						if(res > 0){			
							System.out.println("[UV] 0<result");
							uvlgs.setValueWithin(res);
							uvjsld.setValue(uvlgs.getValue());
							uvjtf.setText(String.valueOf(uvlgs.getValue()));
						}  else{
							System.out.println("[UV] 0>result");
							uvlgs.setValueWithin(1);
							uvjsld.setValue(0);
							uvjtf.setText(String.valueOf(0));
						} 
					}
					  
					// Cutoff
					if(frame_.isNewCutOff()){
						System.out.println("[UV] we asked for new cutoff");
						
						uvcutoff.setText(Double.toString(round(result[3],2)));
						frame_.setRequestOff();
						System.out.println("[UV] request off");
					}
					  
					// Update NMS frame
					if(frame_.isNMSChecked()){
						System.out.println("[UV] NMS is checked");

						NMScounter++;
						if(NMScounter % 10 == 0){
							frame_.setNMSImageProcessor(uva_.getNMSresult());
						}
						if(NMScounter == Integer.MAX_VALUE){
							NMScounter = 0;
						}
					}
					System.out.println("[UV] done");

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
