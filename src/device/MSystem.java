package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class MSystem {

	CMMCore core_;
	QPD qpd_;
	Stage pi_;
	Laser l405_, l488_, l638_;
	SSLaser l561_;
	Servo fw_, bfp_, astig_; 
	Log log_;
	
	public MSystem(CMMCore core, Log log){
		core_ = core;
		log_ = log;
		readConfiguration();
		initializeDevices();
		areDevicesAvailable();
	}
	
	private void initializeDevices() {
		qpd_ = new QPD(MConfiguration.qpdlabel,core_,log_);
		pi_ = new Stage(MConfiguration.qpdlabel,core_,log_);
		l405_ = new Laser(MConfiguration.laserlabel[0], MConfiguration.ardlaserlabel[0],core_,log_);
		l488_ = new Laser(MConfiguration.laserlabel[1], MConfiguration.ardlaserlabel[1],core_,log_);
		l638_ = new Laser(MConfiguration.laserlabel[2], MConfiguration.ardlaserlabel[2],core_,log_);
		l561_ = new SSLaser(MConfiguration.sslaserlabel, MConfiguration.ardlaserlabel[3], MConfiguration.ard2laserlabel,core_,log_);
		fw_ = new Servo(MConfiguration.servolabel[0], MConfiguration.proplabel[0], MConfiguration.numposservo[0],core_,log_);
		bfp_ = new Servo(MConfiguration.servolabel[1], MConfiguration.proplabel[1], MConfiguration.numposservo[1],core_,log_);
		astig_ = new Servo(MConfiguration.servolabel[2], MConfiguration.proplabel[2], MConfiguration.numposservo[2],core_,log_);
	}

	private void areDevicesAvailable() {
																		/////// check if devices are available
	}						

	private void readConfiguration(){

	}

	//////////////////////////////////////////////////////
	////// QPD
	public QPD getQPD(){
		return qpd_;
	}
	
	public double getQPDX(){
		return qpd_.getXSignal();
	}
	
	public double getQPDY(){
		return qpd_.getYSignal();
	}
	
	public double getQPDS(){
		return qpd_.getSSignal();
	}

	//////////////////////////////////////////////////////
	////// PI 
	public Stage getPIStage(){
		return pi_;
	}

	public double getPIPosition(){
		return pi_.getPosition();
	}
	
	public void setStageSensor(int val){
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
	
	public void setLaserOperation(String name, int val){
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
	
	public void setLaserMaxPower(int val){
		l561_.setMaxPower(val);
	}
	
}
