package device;

import micromanager.Configuration;
import micromanager.Log;
import mmcorej.CMMCore;

public class SSLaser extends Device{

	String arduinoLabel1_,arduinoLabel2_;
	DeviceProperty powerPerc_;
	DeviceProperty operation_;
	DeviceProperty behaviour_;
	DeviceProperty pulse_;
	DeviceProperty powerMax_;
	
	
	SSLaser(String label, String arduinolabel1, String arduinolabel2, CMMCore core, Log log){
		super(label,core,log);
		arduinoLabel1_ = arduinolabel1;
		arduinoLabel2_ = arduinolabel2;
		createProperties();
	}
	
	private void createProperties() {
		operation_ = new DeviceProperty(label_, Configuration.coboltproplabel[0], 0, 0, 1,core_,log_);
		powerPerc_ = new DeviceProperty(arduinoLabel2_, Configuration.ard2proplabel, 0, 0, 100,core_,log_);
		powerMax_ = new DeviceProperty(label_, Configuration.coboltproplabel[0], 300, 0, 300,core_,log_);
		behaviour_ = new DeviceProperty(arduinoLabel1_, Configuration.ardproplabel[0], 2, 0, 4,core_,log_);
		pulse_ = new DeviceProperty(arduinoLabel1_, Configuration.ardproplabel[1], 0, 0, Configuration.ardlasermaxpulse,core_,log_);

		properties_.add(operation_);
		properties_.add(powerPerc_);
		properties_.add(powerMax_);
		properties_.add(behaviour_);
		properties_.add(pulse_);
	}

	public String getArduinoLabel1(){
		return arduinoLabel1_;
	}

	public String getArduinoLabel2(){
		return arduinoLabel2_;
	}

	public void setOperation(int val){	
		if(val>=operation_.getMinValue() && val<=operation_.getMaxValue()){
			setProperty(operation_.getPropertyName(),val);										/// maybe modify directly the object
		} else {
			log_.writeToLog(label_+" : Invalid operation requested ("+val+")");
		}
	}
	
	public void setPowerPercentage(int val){		
		if(val>=powerPerc_.getMinValue() && val<=powerPerc_.getMaxValue()){
			setProperty(powerPerc_.getPropertyName(),val);
		} else {
			log_.writeToLog(label_+" : Invalid power percentage requested ("+val+")");
		}
	}
	
	public void setBehaviour(int val){			
		if(val>=behaviour_.getMinValue() && val<=behaviour_.getMaxValue()){
			setProperty(behaviour_.getPropertyName(),val);
		} else {
			log_.writeToLog(label_+" : Invalid behaviour requested ("+val+")");
		}
	}
	
	public void setMaxPower(int val){			
		if(val>=powerMax_.getMinValue() && val<=powerMax_.getMaxValue()){
			setProperty(powerMax_.getPropertyName(),val);
		} else {
			log_.writeToLog(label_+" : Invalid power max requested ("+val+")");
		}
	}
	
	public void setPulseLength(int val){	
		if(val>=pulse_.getMinValue() && val<=pulse_.getMaxValue()){
			setProperty(pulse_.getPropertyName(),val);
		} else {
			log_.writeToLog(label_+" : Invalid pulse length  requested ("+val+")");
		}
	}

	public int getOperation(){									
		return (int) getProperty(operation_.getPropertyName());									
	}

	public int getPowerPercentage(){									
		return (int) getProperty(powerPerc_.getPropertyName());									
	}

	public int getBehaviour(){									
		return (int) getProperty(behaviour_.getPropertyName());									
	}

	public int getMaxPower(){									
		return (int) getProperty(powerMax_.getPropertyName());									
	}

	public double getPulseLength(){									
		return getProperty(pulse_.getPropertyName());									
	}
	

	@Override
	public String getType() {
		return "SSLaser";
	}
}
