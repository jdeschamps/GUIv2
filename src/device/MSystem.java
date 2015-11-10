package device;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;
import mmcorej.StrVector;

public class MSystem {

	CMMCore core_;
	MConfiguration config_;
	QPD qpd_;
	Stage pi_;
	Laser l405_, l488_, l638_;
	SSLaser l561_;
	Servo fw_, bfp_, astig_; 
	Log log_;
	ArrayList<Device> deviceList_;
	StrVector devices_;
	StrVector devicesAbsent_;
	int numDevices_ = 9;
	
	public MSystem(){
		// empty to test
	}
	
	public MSystem(CMMCore core, Log log, MConfiguration config){
		core_ = core;
		log_ = log;
		config_ = config;
		
		devicesAbsent_ = new StrVector();
		
		detectDevices();
		initializeDevices();
		areDevicesAvailable();
	}
	
	private void detectDevices(){
		devices_ = core_.getLoadedDevices();
		for(String s:devices_){
			System.out.println("[GUI] Available device: "+s);
		}
	}
	
	private boolean isLoaded(String s){
		for(int i=0;i<devices_.size();i++){
			if(devices_.get(i).equals(s)){
				System.out.println("[GUI] Device "+s+" loaded");
				return true;
			} else {
				devicesAbsent_.add(s);
			}
		}
		System.out.println("[GUI] Device "+s+" not loaded");
		return false;
	}
	
	private void initializeDevices() {
		qpd_ = new QPD(MConfiguration.qpdlabel,core_,log_, isLoaded(MConfiguration.qpdlabel));
		pi_ = new Stage(MConfiguration.pilabel,core_,log_, isLoaded(MConfiguration.pilabel), isLoaded(MConfiguration.smaractlabel));
		l405_ = new Laser(MConfiguration.laserlabel[0], MConfiguration.ardlaserlabel[0],core_,log_, isLoaded(MConfiguration.laserlabel[0]));
		l488_ = new Laser(MConfiguration.laserlabel[1], MConfiguration.ardlaserlabel[1],core_,log_, isLoaded(MConfiguration.laserlabel[1]));
		l638_ = new Laser(MConfiguration.laserlabel[2], MConfiguration.ardlaserlabel[2],core_,log_, isLoaded(MConfiguration.laserlabel[2]));
		l561_ = new SSLaser(MConfiguration.sslaserlabel, MConfiguration.ardlaserlabel[3], MConfiguration.ard2laserlabel,core_,log_, isLoaded(MConfiguration.sslaserlabel));
		fw_ = new Servo(MConfiguration.servolabel[0], MConfiguration.proplabel[0], MConfiguration.numposservo[0],core_,log_, isLoaded(MConfiguration.servolabel[0]));
		bfp_ = new Servo(MConfiguration.servolabel[1], MConfiguration.proplabel[1], MConfiguration.numposservo[1],core_,log_, isLoaded(MConfiguration.servolabel[1]));
		astig_ = new Servo(MConfiguration.servolabel[2], MConfiguration.proplabel[2], MConfiguration.numposservo[2],core_,log_, isLoaded(MConfiguration.servolabel[2]));
		
		deviceList_ = new ArrayList<Device>(numDevices_);
		deviceList_.add(qpd_);
		deviceList_.add(pi_);
		deviceList_.add(l405_);
		deviceList_.add(l488_);
		deviceList_.add(l638_);
		deviceList_.add(l561_);
		deviceList_.add(fw_);
		deviceList_.add(bfp_);
		deviceList_.add(astig_);

		if(areDevicesAvailable()){
			for(Device d:deviceList_){
				d.initialize();
			}
		}
	}

	private boolean areDevicesAvailable() {
		boolean b;
		String[] dev = (core_.getLoadedDevices()).toArray();
		for(Device d:deviceList_){
			b = false;
			for(int i=0;i<dev.length;i++){
				if(dev[i].equals(d.getLabel())){
					b = true;
					break;
				}
			}
			if(!b){
				log_.writeToLog("Error checking availability: "+d.getLabel()+" not available.");
				return false;
			}
		}
		return true;
	}						

	public void shutDown(){
		pi_.setSensorState(0);
		l405_.setOperation(0);
		l488_.setOperation(0);
		l561_.setOperation(0);
		l638_.setOperation(0);
	}
	
	//////////////////////////////////////////////////////
	////// Special functions
	public double getExposureTime(){
		double val = 0;
		
		try {
			val=core_.getExposure();
		} catch (Exception e) {
			log_.writeToLog("Error: unable to get exposure time.");
		}
		
		return val;
	}
	
	public CMMCore getCore(){
		return core_;
	}
	
	//////////////////////////////////////////////////////
	////// QPD
	public QPD getQPD(){
		return qpd_;
	}
	
	public double getQPDX(){
		double val = 0;
	    try{
	    	val = qpd_.getXSignal();
	    } catch(NullPointerException e){
	    	log_.writeToLog("Error: ");
	    }
		return val;
	}
	
	public double getQPDY(){
		double val = 0;
	    try{
	    	val = qpd_.getYSignal();
	    } catch(NullPointerException e){
	    	log_.writeToLog("Error: ");
	    }
		return val;
	}
	
	public double getQPDS(){
		double val = 0;
	    try{
	    	val = qpd_.getSSignal();
	    } catch(NullPointerException e){
	    	log_.writeToLog("Error: ");
	    }
		return val;
	}

	//////////////////////////////////////////////////////
	////// PI 
	public Stage getPIStage(){
		return pi_;
	}

	public double getPIPosition(){
		double val = 0;
	    try{
	    	if(pi_ != null){
	    		val = pi_.getPosition();
	    	}
	    } catch(NullPointerException e){
	    	log_.writeToLog("Error: ");
	    }
		return val;
	}

	public double getPISensorState(){
		double val = 0;
	    try{
	    	val = pi_.getSensorState();
	    } catch(NullPointerException e){
	    	log_.writeToLog("Error: ");
	    }
		return val;
	}
	
	public void setStageSensor(int val){

		System.out.println("[GUI] Stage sensor set to "+val);
		pi_.setSensorState(val);
	}
	
	public void setStagePosition(int val){
		pi_.setPosition(val);
	}
	
	//////////////////////////////////////////////////////
	////// Servos 
	public Device getServo(String name){
		Device d = null;
		if(name.equals(MConfiguration.servokeys[0])){
			d = fw_;
		} else if(name.equals(MConfiguration.servokeys[1])){
			d = bfp_;
		} else if(name.equals(MConfiguration.servokeys[2])){
			d = astig_;
		} 
		return d;
	}
	
	public void setServoState(String name, int val){
		if(name.equals(MConfiguration.servokeys[0])){
			fw_.setState(val);
		} else if(name.equals(MConfiguration.servokeys[1])){
			bfp_.setState(val);
		} else if(name.equals(MConfiguration.servokeys[2])){
			astig_.setState(val);
		} 
	}
	
	public int getServoState(String name){
		if(name.equals(MConfiguration.servokeys[0])){
			fw_.getState();
		} else if(name.equals(MConfiguration.servokeys[1])){
			bfp_.getState();
		} else if(name.equals(MConfiguration.servokeys[2])){
			astig_.getState();
		} 
		return 0;
	}
	
	//////////////////////////////////////////////////////
	////// Lasers 
	public Device getLaser(String name){
		Device d = null;
		if(name.equals(MConfiguration.laserkeys[0])){
			d = l405_;
		} else if(name.equals(MConfiguration.laserkeys[1])){
			d = l488_;
		} else if(name.equals(MConfiguration.laserkeys[3])){
			d = l561_;
		} else if(name.equals(MConfiguration.laserkeys[2])){
			d = l638_;
		} 
		return d;
	}
	
	public double getUVPulse(){
		if(l405_ != null){
			return l405_.getPulseLength();
		}
		return 0;
	}
	
	public void setLaserOperation(String name, int val){
		System.out.println("[GUI] set laser operation "+name+" "+val);
		if(name.equals(MConfiguration.laserkeys[0])){
			l405_.setOperation(val);
		} else if(name.equals(MConfiguration.laserkeys[1])){
			l488_.setOperation(val);
		} else if(name.equals(MConfiguration.laserkeys[3])){			
			l561_.setOperation(val);
		} else if(name.equals(MConfiguration.laserkeys[2])){
			l638_.setOperation(val);
		} 
	}	
	
	public void setLaserPowerPerc(String name, int val){
		if(name.equals(MConfiguration.laserkeys[0])){
			l405_.setPowerPercentage(val);
		} else if(name.equals(MConfiguration.laserkeys[1])){
			l488_.setPowerPercentage(val);
		} else if(name.equals(MConfiguration.laserkeys[3])){			
			l561_.setPowerPercentage(val);
		} else if(name.equals(MConfiguration.laserkeys[2])){
			l638_.setPowerPercentage(val);
		} 
	}
	
	public void setLaserBehaviour(String name, int val){
		if(name.equals(MConfiguration.laserkeys[0])){
			l405_.setBehaviour(val);
		} else if(name.equals(MConfiguration.laserkeys[1])){
			l488_.setBehaviour(val);
		} else if(name.equals(MConfiguration.laserkeys[3])){			
			l561_.setBehaviour(val);
		} else if(name.equals(MConfiguration.laserkeys[2])){
			l638_.setBehaviour(val);
		} 
	}	

	public void setLaserPulseLength(String name, int val){
		if(name.equals(MConfiguration.laserkeys[0])){
			l405_.setPulseLength(val);
		} else if(name.equals(MConfiguration.laserkeys[1])){
			l488_.setPulseLength(val);
		} else if(name.equals(MConfiguration.laserkeys[3])){			
			l561_.setPulseLength(val);
		} else if(name.equals(MConfiguration.laserkeys[2])){
			l638_.setPulseLength(val);
		} 
	}

	public int getLaserOperation(String name){
		if(name.equals(MConfiguration.laserkeys[0])){
			return l405_.getOperation();
		} else if(name.equals(MConfiguration.laserkeys[1])){
			return l488_.getOperation();
		} else if(name.equals(MConfiguration.laserkeys[3])){			
			return l561_.getOperation();
		} else if(name.equals(MConfiguration.laserkeys[2])){
			return l638_.getOperation();
		} 
		return 0;
	}

	public void setUVPulse(int val){
		l405_.setPulseLength(val);
	}
	
	public void setLaserMaxPower(int val){
		l561_.setMaxPower(val);
	}
	
	///////////////////////////////////////
	public void writeToLog(String s){
		log_.writeToLog(s);
	}
}
