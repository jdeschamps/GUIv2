package threader;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import swing.GUIFrame;
import swing.Graph;
import swing.LogarithmicJSlider;
import device.MSystem;
import micromanager.Configuration;
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
	
	public Threader(MSystem sys, Log log, GUIFrame frame){

		sys_ = sys;
		log_ = log;
		frame_ = frame;
		
		initializeUpdaters();
	}

	private void initializeUpdaters() {
		pim_ = new PImonitor(sys_.getPIStage());
		qpdm_ = new QPDmonitor(sys_.getQPD());
		uva_ = new UVautomator(sys_.getLaser(Configuration.laserkeys[0]));
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


	////// does not use propertychange which should be much better, investigate
	public class UIupdater extends SwingWorker<Integer,Double[]>{
		
		Double[] result;
		Graph pig, qpdg1, qpdg2, qpdg3, uvg;
		LogarithmicJSlider uvlgs;
		JTextField uvjtf;
		
		public UIupdater(){
			result = new Double[2];
			pig = frame_.getFocusGraph();
			qpdg1 = frame_.getQPDGraph1();
			qpdg2 = frame_.getQPDGraph2();
			qpdg3 = frame_.getQPDGraph3();
			uvg = frame_.getNGraph();
			uvlgs = frame_.getUVSlider();
			uvjtf = frame_.getUVtext();
		}

		@Override
		protected Integer doInBackground() throws Exception {
			while(running_ && !isCancelled()){
				if(pim_.isRunning()){
					result[0] = (double) 0;
					pim_.refresh();
					result[1] = pim_.getOutput(0);
					publish();
				}

				if(qpdm_.isRunning()){
					for(int i=0;i<qpdm_.getNOutput();i++){
						result[0] = (double) i+1;
						qpdm_.refresh();
						result[1] = qpdm_.getOutput(i);
						publish(result);
					}
				}

				if(uva_.isRunning()){
					uva_.refresh();
					result[0] = (double) 4;
					result[1] = uva_.getOutput(0);
					publish(result);
					result[0] = (double) 5;
					result[1] = uva_.getOutput(1);
					publish(result);
				}
				Thread.sleep(400);
			}
			return null;
		}
		
		  @Override
		  protected void process(List<Double[]> chunks) {
			  for(Double[] result : chunks){
				  switch(result[0].intValue()){
				  case 0:	// PI pos
					  pig.addPoint((int) (100*result[1]));
					  pig.repaint();
					  break;
				  case 1:	// QPD x
					  qpdg1.addPoint(result[1].intValue());
					  break;
				  case 2:	// QPD y
					  qpdg2.addPoint(result[1].intValue());
					  break;
				  case 3:	// QPD s
					  qpdg3.addPoint((int) qpdm_.getOutput(0),(int) qpdm_.getOutput(2));
					  break;
				  case 4:	// UV N
					  uvg.addPoint(result[1].intValue());
					  break;
				  case 5:	// UV pulse
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
