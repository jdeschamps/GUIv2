package advancedacq;

import java.io.Serializable;
import java.util.ArrayList;

import org.micromanager.api.SequenceSettings;

import device.MSystem;


public class Acquisition implements Serializable {

	public enum acqtype implements Serializable {
		BFPSNAP("bfp"), ZSTACK("zstack"), TSTACK("time"), SNAPSHOT("snapshot");
		
		String name;
		int diversity = 4;
		acqtype(String s){
			name = s;
		}
		
		public String getName(){
			return name;
		}
		public int getDiversity(){
			return diversity;
		}
	}

	private SequenceSettingsWraper seqw;
	private acqtype acqType;
	private String path;
	private int numFrames=0;
	private int filterNumber=0;
	private int exposuretime=0;
	private int waitingtime;
	private double z0=0;
	private double stepsize=0;
	private int numbersteps=0;
	private boolean activation=false;
	private ArrayList<LaserSettings> laserlist;
	
	public Acquisition(){
		seqw = new SequenceSettingsWraper();
		
		setOperator(acqType.SNAPSHOT);
	}
	

	public Acquisition(acqtype op, ArrayList<LaserSettings> laserlist, int filter, int exposuretime, int numFrames, int waitingtime,boolean activation,double z0, double stepsize, int numbersteps, String path){
		seqw = new SequenceSettingsWraper();
		
		this.numFrames = numFrames;
		this.path = path;
		this.filterNumber = filter;
		this.exposuretime = exposuretime;
		this.waitingtime = waitingtime;
		this.laserlist = laserlist;
		this.activation = activation;
		this.z0 = z0;
		this.stepsize = stepsize;
		this.numbersteps = numbersteps;
		
		setOperator(op);
	}

	public Acquisition(String acq, ArrayList<LaserSettings> laserlist, int filter, int exposuretime, int numFrames, int waitingtime, boolean activation,double z0, double stepsize, int numbersteps, String path){
		seqw = new SequenceSettingsWraper();
		
		this.numFrames = numFrames;
		this.path = path;
		this.filterNumber = filter;
		this.exposuretime = exposuretime;
		this.waitingtime = waitingtime;
		this.laserlist = laserlist;
		this.activation = activation;
		this.z0 = z0;
		this.stepsize = stepsize;
		this.numbersteps = numbersteps;
		
		if(acq.equals(acqtype.BFPSNAP.name)){
			setOperator(acqtype.BFPSNAP);
		} else if(acq.equals(acqtype.ZSTACK.name)){
			setOperator(acqtype.ZSTACK);
		} else if(acq.equals(acqtype.TSTACK.name)){
			setOperator(acqtype.TSTACK);
		} else if(acq.equals(acqtype.SNAPSHOT.name)){
			setOperator(acqtype.SNAPSHOT);
		} 
	
	}
		
	private void setOperator(acqtype op){
		acqType = op;
	}
	
	public String[] getAcqType(){
		String[] s = {acqtype.TSTACK.name,acqtype.ZSTACK.name,acqtype.BFPSNAP.name,acqtype.SNAPSHOT.name,}; 
		return s;
	}
	
	public acqtype getAcq(){
		return acqType;
	}
	
	public String getPath(){
		return path;
	}
	
	public int getNumberFrames(){
		return numFrames;
	}
	
	public String getAcqTypeName(){
		switch(acqType){
		case BFPSNAP:
			return acqtype.BFPSNAP.name;
		case TSTACK:
			return acqtype.TSTACK.name;
		case ZSTACK:
			return acqtype.ZSTACK.name;
		default:
			return acqtype.SNAPSHOT.name;
		}
	}
	
	public ArrayList<LaserSettings> getLaserList(){
		return laserlist;
	}
	
	public int getWaitingTime(){
		return waitingtime;
	}
	
	public int getExposureTime(){
		return exposuretime;
	}
	
	public int getFilterNumber(){
		return filterNumber;
	}

	public boolean getActivation(){
		return activation;
	}
	public double getZ0(){
		return z0;
	}
	
	public double getStepSize(){
		return stepsize;
	}
	
	public int getNSteps(){
		return numbersteps;
	}


	public SequenceSettings getAcquisitionSettings(){
		if(seqw == null){
			System.out.println("ahahah");
		}
		
		
		return seqw.getSettings(this);
	}
	
	public void setUpSystem(MSystem sys){
		System.out.println("----------set up system");
		sys.turnoffLasers();
		//sys.setAstig(astigmatism==true ? 1:0);
		sys.setBFP(0);
		sys.setFilter(filterNumber);
		sys.setExposureTime(exposuretime);
		
		if(laserlist.size()>0){
			for(int i=0;i<laserlist.size();i++){
				sys.setLaserState(laserlist.get(i).getLabel(),laserlist.get(i).getMode(),laserlist.get(i).getPowerPerc(),laserlist.get(i).getPulseLength(),laserlist.get(i).getSequence());
			}
		}
			
		switch(acqType){
		case BFPSNAP:
			sys.setBFP(1);
			break;
		case ZSTACK:
			sys.setStageSensor(0);
			break;
		default:
			break;
		}
	}
	public void endAcqSystem(MSystem sys){
		//sys.setAstig(0);
		
		System.out.println("----------end system");	
		switch(acqType){
		case BFPSNAP:
			sys.setBFP(0);
			break;
		case ZSTACK:
			sys.setStageSensor(1);
			break;
		default:
			break;
		}
	}
	
	public String settingsToString(){
		String s = "--------------------\r\n";

		s += "Acquisition type: "+acqType.getName()+"\r\n";
		s += "--- Number of frames: "+numFrames+"\r\n";
		s += "--- Filter: "+filterNumber+"\r\n";
		s += "--- Exposure: "+exposuretime+" ms"+"\r\n";
		s += "--- Waiting time: "+waitingtime+" s"+"\r\n";
		s += "--- Activation: "+activation+"\r\n";

		for(int i=0;i<laserlist.size();i++){
			s += "----- Laser "+laserlist.get(i).getLabel()+"\r\n";
			s += "------- mode: "+laserlist.get(i).getMode()+"\r\n";
			s += "------- power(%): "+laserlist.get(i).getPowerPerc()+"\r\n";
			s += "------- pulse(ms): "+laserlist.get(i).getPulseLength()+"\r\n";
		}
		
		return s;
	}
}
