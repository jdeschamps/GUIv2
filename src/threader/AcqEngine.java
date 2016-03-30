package threader;

import gui.MainFrame;

import java.awt.Frame;
import java.awt.Window;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.IAcquisitionEngine2010;
import org.micromanager.api.MultiStagePosition;
import org.micromanager.api.PositionList;
import org.micromanager.api.ScriptInterface;
import org.micromanager.api.SequenceSettings;
import org.micromanager.utils.MMScriptException;

import advancedacq.Acquisition;
import device.MSystem;
import utils.StringText;
import mmcorej.CMMCore;

public class AcqEngine{
	MainFrame parent_;
	UVThreader uv;
	ScriptInterface app;
	AcqRunner t;
	MSystem sys_;
		
	public AcqEngine(MainFrame parent, MSystem sys){
		parent_ = parent;
		sys_ = sys;
		this.app = parent_.getApp();
	}

	public void runAcq(int numFrames, String path, String acqname, int sleepTime, boolean stopmaxUV){
		t = new AcqRunner( numFrames,  path,  acqname,  sleepTime, stopmaxUV);
        t.execute();
	}

	public void runAcqList(ArrayList<Acquisition> acqlist, String path, String acqname, int sleepTime, boolean stopmaxUV){
		t = new AcqRunner( acqlist,  path,  acqname,  sleepTime, stopmaxUV);
        t.execute();
	}
		
	public void stopAcq(){
		t.stop();
	}
	
	class AcqRunner extends SwingWorker<Integer,Double[]>{
		private int numFrames_;
		private String path_;
		private String acqname_;
		private String individualname;
		private int sleepTime_;
		private boolean stopmaxUV_;
		private int numPosition;
		private String currAcq;
		
		private boolean stop_ = false;

		private StringText st;
		private JProgressBar pb;
		
		private boolean advanced=false;
		
		private ArrayList<Acquisition> acqlist;

		public AcqRunner(int numFrames, String path, String acqname, int sleepTime, boolean stopmaxUV){
			numFrames_ = numFrames;
			path_ = path;
			acqname_ = acqname;
			sleepTime_ = sleepTime;
			this.stopmaxUV_ = stopmaxUV;
			advanced = false;
		}
		
		public AcqRunner(ArrayList<Acquisition> acqlist, String path, String acqname, int sleepTime, boolean stopmaxUV){
			this.acqlist = acqlist;
			path_ = path;
			acqname_ = acqname;
			sleepTime_ = sleepTime;
			this.stopmaxUV_ = stopmaxUV;
			advanced = true;
		}

		public void stop(){
			stop_ = true;
			closeCurrAcq();
		}
		
		public void closeCurrAcq(){
			//app.closeAcquisition(currAcq);
			IAcquisitionEngine2010 aq =  app.getAcquisitionEngine2010();
			aq.stop();
			while(!aq.isFinished()){
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		@Override
		protected Integer doInBackground() throws Exception {
			if(advanced){
				System.out.println("Run advanced");
				runAdvanced();
			} else {
				System.out.println("Run normal");
				runSingleBatch();
			}
			return 0;
		}
		
		protected void runAdvanced(){
			if(acqlist.size()>=1){
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
	    			
	    			String xystage = app.getXYStageName();						////////////////////////////////////////////////////////////////////////////

	    			result[0] = (double) 0;
	    			result[1] = (double) 0;
	    			publish(result);
	    				    			
	    			for(int i=0;i<numPosition;i++){
	    				System.out.println("Position number "+i);
	    				result[0] = (double) i+1;
	    				
	        			currPos = poslist.getPosition(i);
	        			core.setXYPosition(xystage, currPos.get(0).x, currPos.get(0).y);
	        			Thread.sleep(sleepTime_*1000);
	        			
	    				for(int k=0;k<acqlist.size();k++){
		    				System.out.println("Acquisition number "+k);
		    				
	    					// set acquisition settings
		        			app.setAcquisitionSettings(acqlist.get(k).getAcquisitionSettings());
		        			parent_.UVChecked(acqlist.get(k).getActivation());
		        			
		        			// acq name
		        			individualname = i+"_"+acqname_+"_"+acqlist.get(k).getAcqTypeName();
		        			
		        			// if none then leave, otherwise configure system
		        			if(acqlist.get(k).getAcqTypeName().equals("None")){
		        				System.out.println("None");
		        				break;
		        			} else {
		        				acqlist.get(k).setUpSystem(sys_);
		        				System.out.println("Wait");
			        			Thread.sleep(acqlist.get(k).getWaitingTime()*1000);
		        			}
		        			
		        			// run acq
		        			Thread t = new Thread() {
		        				public void run() {
		        					try {
		        						currAcq  = app.runAcquisition(individualname,path_);
		        					} catch (MMScriptException e) {
		        						e.printStackTrace();
		        					}
		        				}  
		        			};
		        			t.start();
		        			
		    				while(t.isAlive()){
		            			Thread.sleep(500);
		    					if(uv.isUVatMax() && stopmaxUV_){				/// if UV is at max or stop has been requested
		    						closeCurrAcq();
		    						t.interrupt();
		    					}
		    				}
							
							// close acq window
		        			try{
		        				app.closeAcquisitionWindow(currAcq);
		        			} catch (MMScriptException e) {
		        				System.out.println("Cannot close");
		        			}
		        		
		        			// show progress
		        			result[1] = (double) (Math.floor(100*(i+1)/numPosition));		
		        			     
		        			// restart uv
		        			uv.restartUV();
		        			
		        			// end acq settings
	        				acqlist.get(k).endAcqSystem(sys_);
		        			
		        			publish(result);
		        			        			
		        			if(stop_){
		        				stop_ = false;
		        				break;
		        			}
		        			
		    			}
		    			
		    			result[1] = (double) 100;
		    			publish(result);
	    			}
	    			
	    		} catch (MMScriptException e) {
	    			e.printStackTrace();
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
			}
		}
		
		protected void runSingleBatch(){
			Double[] result = new Double[2];
			
			uv = parent_.getcurrentThreader();
			st = parent_.getAcqString();
			pb = parent_.getAcqProgressBar();
			
    		try {

    			PrintWriter writer;
    			writer = new PrintWriter(new FileWriter("Acq-test.txt", true));
    			   
    			CMMCore core = app.getMMCore();
    			
    			// clear all previous acquisitions
    			app.closeAllAcquisitions();
    			app.clearMessageWindow();
    			
    			PositionList poslist = app.getPositionList();
    			numPosition = poslist.getNumberOfPositions();
    			MultiStagePosition currPos = poslist.getPosition(0);
    			
    			String xystage = app.getXYStageName();						////////////////////////////////////////////////////////////////////////////
                
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
    				
    				writer.println("---------------- exp "+i);
    				
        			currPos = poslist.getPosition(i);
        			core.setXYPosition(xystage, currPos.get(0).x, currPos.get(0).y);

    				writer.println("---Set position "+currPos.get(0).x+" "+ currPos.get(0).y);
        			
        			Thread.sleep(sleepTime_*1000);

    				writer.println("---Sleep "+currPos.get(0).x+" "+ currPos.get(0).y);
        			
        			app.setAcquisitionSettings(seq);
        			
        			individualname = i+"_"+acqname_;

        			writer.println("---Name "+individualname);


    				//app.refreshGUI();
        			Thread t = new Thread() {
        				public void run() {
        					try {
        						currAcq  = app.runAcquisition(individualname,path_);
        					} catch (MMScriptException e) {
        						e.printStackTrace();
        					}
        				}  
        			};
        			t.start();
        			
        			writer.println("---Start");

    				while(t.isAlive()){
            			Thread.sleep(500);
    					if(uv.isUVatMax() && stopmaxUV_){				/// if UV is at max or stop has been requested

    	        			writer.println("----------Closing");
    						closeCurrAcq();
    						//t.interrupt();
    					} else {
    	        			writer.println("---Waiting");
    					}
    				}

    				
        			try{
        				app.closeAcquisitionWindow(currAcq);
	        			writer.println("---Closed window");

        			} catch (MMScriptException e) {
        				System.out.println("Cannot close");
        			}
        		
        			result[1] = (double) (Math.floor(100*(i+1)/numPosition));		
        			   
        			uv.restartUV();
        			writer.println("---Restart UV");

        			publish(result);
        			writer.println("---Publish");

        			        			
        			if(stop_){			// stop requested, the whole acquisition stops
        				stop_ = false;
            			writer.println("---Stop all");
        				break;
        			}
        			
    			}
    			
    			result[1] = (double) 100;
    			publish(result);
    			writer.println("------------End");

    			writer.close();
    		} catch (MMScriptException e) {
    			e.printStackTrace();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		

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
