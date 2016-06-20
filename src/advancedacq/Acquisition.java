package advancedacq;

import java.util.ArrayList;

import org.micromanager.api.SequenceSettings;

import device.MSystem;


public class Acquisition {

	public enum acqtype {
		BFPSNAP("bfp"), ZSTACK("zstack"), TSTACK("time"), NONE("none");
		
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

	private SequenceSettings seq;
	private acqtype acqType;
	private String path;
	private int numFrames=0;
	private int filterNumber=0;
	private int exposuretime=0;
	private int waitingtime;
	private boolean activation=false;
	private boolean astigmatism=false;
	private ArrayList<LaserSettings> laserlist;
	
	public Acquisition(){
		setOperator(acqType.NONE);
	}
	

	public Acquisition(acqtype op, ArrayList<LaserSettings> laserlist, int filter, int exposuretime, int numFrames, int waitingtime, boolean astigmatism, boolean activation, String path){
		this.numFrames = numFrames;
		this.path = path;
		this.filterNumber = filter;
		this.exposuretime = exposuretime;
		this.waitingtime = waitingtime;
		this.laserlist = laserlist;
		this.activation = activation;
		this.astigmatism = astigmatism;
		
		setOperator(op);
	}

	public Acquisition(String acq, ArrayList<LaserSettings> laserlist, int filter, int exposuretime, int numFrames, int waitingtime, boolean astigmatism, boolean activation, String path){
		this.numFrames = numFrames;
		this.path = path;
		this.filterNumber = filter;
		this.exposuretime = exposuretime;
		this.waitingtime = waitingtime;
		this.laserlist = laserlist;
		this.activation = activation;
		this.astigmatism = astigmatism;
		
		if(acq.equals(acqtype.BFPSNAP.name)){
			setOperator(acqtype.BFPSNAP);
		} else if(acq.equals(acqtype.ZSTACK.name)){
			setOperator(acqtype.ZSTACK);
		} else if(acq.equals(acqtype.TSTACK.name)){
			setOperator(acqtype.TSTACK);
		} else if(acq.equals(acqtype.NONE.name)){
			setOperator(acqtype.NONE);
		} 
	
	}
		
	private void setOperator(acqtype op){
		acqType = op;
		setSequenceSettings();
	}
	
	public String[] getAcqType(){
		String[] s = {acqtype.TSTACK.name,acqtype.ZSTACK.name,acqtype.BFPSNAP.name,acqtype.NONE.name,}; 
		return s;
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
			return acqtype.NONE.name;
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
	public boolean getAstigmatism(){
		return astigmatism;
	}
	
	private void setSequenceSettings(){
		switch(acqType){
		case BFPSNAP:
			seq = new SequenceSettings();
			seq.numFrames = 1;
			seq.intervalMs = 0;
			seq.root = path;
			seq.save = true;
			seq.timeFirst = true;
			seq.usePositionList = false;
			break;
		case ZSTACK:
			seq = new SequenceSettings();
			seq.relativeZSlice = true;
			ArrayList<Double> slice = new ArrayList<Double>();
			Double z;
			for(int i=0;i<=4000/50;i++){
				z=-2+i*0.05;
				slice.add(z);
			}
			seq.slices = slice;
			seq.numFrames = 1;
			seq.intervalMs = 0;
			seq.root = path;
			seq.save = true;
			seq.usePositionList = false;
			break;
		case TSTACK:
  			seq = new SequenceSettings();
			seq.numFrames = numFrames;
			seq.intervalMs = 0;
			seq.root = path;
			seq.save = true;
			seq.timeFirst = true;
			seq.usePositionList = false;
			break;
		case NONE:
  			seq = new SequenceSettings();
			seq.numFrames = 0;
			seq.intervalMs = 0;
			seq.root = path;
			seq.save = false;
			seq.usePositionList = false;
			break;
		}
	}
	
	public SequenceSettings getAcquisitionSettings(){
		System.out.println("Get acquisition settings");

		return seq;
	}
	
	public void setUpSystem(MSystem sys){
		System.out.println("----------set up system");
		sys.turnoffLasers();
		sys.setAstig(astigmatism==true ? 1:0);
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
		sys.setAstig(0);
		
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
		s += "--- 3DA: "+astigmatism+"\r\n";

		for(int i=0;i<laserlist.size();i++){
			s += "----- Laser "+laserlist.get(i).getLabel()+"\r\n";
			s += "------- mode: "+laserlist.get(i).getMode()+"\r\n";
			s += "------- power(%): "+laserlist.get(i).getPowerPerc()+"\r\n";
			s += "------- pulse(ms): "+laserlist.get(i).getPulseLength()+"\r\n";
		}
		
		return s;
	}
}
