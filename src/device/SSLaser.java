package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class SSLaser extends Device{

	String controllerLabel1_,controllerLabel2_, name_;
	DeviceProperty powerPerc_;
	DeviceProperty operation_;
	DeviceProperty behaviour_;
	DeviceProperty pulse_;
	DeviceProperty powerMax_;
	DeviceProperty sequence_;
	
	
	public SSLaser(String label, String controllerLabel1,String name, String controllerLabel2, CMMCore core, Log log, boolean isLoaded){
		super(label,core,log, isLoaded);
		controllerLabel1_ = controllerLabel1;
		controllerLabel2_ = controllerLabel2;
		name_ = name;
		createProperties();
	}
	
	private void createProperties() {
		operation_ = new DeviceProperty(label_, "", 0, 1,core_,log_,true, !detected_);
		powerPerc_ = new DeviceProperty(controllerLabel2_, "", 0, 100,core_,log_,false, !detected_);
		powerMax_ = new DeviceProperty(label_, "", 0, 300,core_,log_,false, !detected_);
		behaviour_ = new DeviceProperty(controllerLabel1_, MConfiguration.getLaserMojoProp(name_)[0], 0, 4,core_,log_,false, !detected_);
		pulse_ = new DeviceProperty(controllerLabel1_, MConfiguration.getLaserMojoProp(name_)[1], 0, MConfiguration.mojomaxpulse,core_,log_,false, !detected_);
		sequence_ = new DeviceProperty(controllerLabel1_, MConfiguration.getLaserMojoProp(name_)[2], 0, MConfiguration.mojomaxpulse,core_,log_,false, !detected_);

		properties_.add(operation_);
		properties_.add(powerPerc_);
		properties_.add(powerMax_);
		properties_.add(behaviour_);
		properties_.add(pulse_);
		add(sequence_);
	}

	public String getcontrollerLabel1(){
		return controllerLabel1_;
	}

	public String getcontrollerLabel2(){
		return controllerLabel2_;
	}

	public void setOperation(int val){	
		if(val==1){
			setProperty(operation_.getPropertyName(),"1");										/// maybe modify directly the object
		} else if(val==0){
			setProperty(operation_.getPropertyName(),"1");	
		}else {
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

	public void setSequence(int d){
		if(d>=sequence_.getMinValue() && d<=sequence_.getMaxValue()){
			setProperty(sequence_.getPropertyName(),d);
		} else {
			log_.writeToLog(label_+" : Invalid sequence  requested ("+d+")");
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

	public int getSequence(){									
		return (int) getProperty(sequence_.getPropertyName());									
	} 

	@Override
	public String getType() {
		return "SSLaser";
	}
}
