package threader;

import gui.MainFrame;

import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.micromanager.api.MultiStagePosition;
import org.micromanager.api.PositionList;
import org.micromanager.api.ScriptInterface;
import org.micromanager.api.SequenceSettings;
import org.micromanager.utils.MMScriptException;

import utils.StringText;
import mmcorej.CMMCore;

public class AcqEngine{
	MainFrame parent_;
	UVThreader uv;
	ScriptInterface app;
	AcqRunner t;
		
	public AcqEngine(MainFrame parent){
		parent_ = parent;
		this.app = parent_.getApp();
	}
	
	public void runAcq(int numFrames, String path, String acqname, int sleepTime, boolean stopmaxUV){
		t = new AcqRunner( numFrames,  path,  acqname,  sleepTime, stopmaxUV);
        t.execute();
	}
	
	public void stopAcq(){
		t.stop();
	}
	
	class AcqRunner extends SwingWorker<Integer,Double[]>{
		private int numFrames_;
		private String path_;
		private String acqname_;
		private int sleepTime_;
		private boolean stopmaxUV_;
		private String individualname;
		private int numPosition;
		
		private boolean stop_ = false;

		private StringText st;
		private JProgressBar pb;
		
		public AcqRunner(int numFrames, String path, String acqname, int sleepTime, boolean stopmaxUV){
			numFrames_ = numFrames;
			path_ = path;
			acqname_ = acqname;
			sleepTime_ = sleepTime;
			stopmaxUV_ = stopmaxUV_;
		}

		public void stop(){
			stop_ = true;
			closeCurrAcq();
		}
		
		public void closeCurrAcq(){
			try {
				app.closeAcquisition(individualname);
			} catch (MMScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		protected Integer doInBackground() throws Exception {
			Double[] result = new Double[2];
			uv = parent_.getcurrentThreader();
			st = parent_.getAcqString();
			pb = parent_.getAcqProgressBar();
			
    		try {
    			CMMCore core = app.getMMCore();
    			
    			// clear all previous acquisitions
    			app.closeAllAcquisitions();
    			app.clearMessageWindow();
    			
    			PositionList poslist = app.getPositionList();
    			numPosition = poslist.getNumberOfPositions();
    			MultiStagePosition currPos = poslist.getPosition(0);
    			
    			String xystage = currPos.getDefaultXYStage();
                
    			// from other plugin
    			//gui_.setImageSavingFormat(org.micromanager.acquisition.TaggedImageStorageMultipageTiff.class);
    			
    			// create acquisition and set options
    			SequenceSettings seq = new SequenceSettings();
    			seq.numFrames = numFrames_;
    			seq.intervalMs = 0;
    			seq.root = path_;
    			seq.save = true;
    			seq.timeFirst = true;
    			seq.usePositionList = false;

    			result[0] = (double) 0;
    			result[1] = (double) 0;
    			publish(result);
    			
    			System.out.println("EDT? "+SwingUtilities.isEventDispatchThread());
    			
    			for(int i=0;i<numPosition;i++){
    				result[0] = (double) i+1;
    				
        			currPos = poslist.getPosition(i);
        			core.setXYPosition(xystage, currPos.get(0).x, currPos.get(0).y);
        		
        			app.setAcquisitionSettings(seq);
        			
        			individualname = i+"_"+acqname_;
        			
        			try{
        				//app.refreshGUI();
        				String s  = app.runAcquisition(individualname,path_);
        				app.closeAcquisitionWindow(s);
        			} catch (MMScriptException e) {
        				
        			}
        		
        			result[1] = (double) (Math.floor(100*(i+1)/numPosition));		
        			     
        			uv.restartUV();
        			
        			publish(result);
        			        			
        			if(stop_){
        				stop_ = false;
        				break;
        			}
        			
        			Thread.sleep(sleepTime_*1000);
    			}
    			
    			result[1] = (double) 100;
    			publish(result);
    			
    			
    		} catch (MMScriptException e) {
    			e.printStackTrace();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			return 0;
		}
		
		@Override
		protected void process(List<Double[]> chunks) {
			for(Double[] result : chunks){
    			//System.out.println("EDT? "+SwingUtilities.isEventDispatchThread());
				
    			pb.setValue(result[1].intValue());
    			st.addFinishedExperiments(result[0].intValue());
    			
    			if(result[1].intValue()==0) {
    				st.setAcquiring(numPosition);
    			}else if(result[1].intValue()==100){
					st.setDone();
				}
			}
		}
	}
}
