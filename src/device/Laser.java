package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Laser extends Device{

	String arduinoLabel_;								//// might want to rename it
	DeviceProperty powerPerc_;
	DeviceProperty operation_;
	DeviceProperty behaviour_;
	DeviceProperty pulse_;
	double lowering_factor;
	
	public Laser(String label, String sublabel, CMMCore core, Log log){
		super(label,core,log);
		arduinoLabel_ = sublabel;
		
		if(label_.equals(MConfiguration.laserkeys[2])){
			lowering_factor = MConfiguration.loweringfactor;
		} else {
			lowering_factor = 1;
		}
		
		createProperties();
	}
	
	private void createProperties() {
		operation_ = new DeviceProperty(label_, MConfiguration.luxxproplabel[0], 0, 1,core_,log_,true);
		powerPerc_ = new DeviceProperty(label_, MConfiguration.luxxproplabel[1], 0, 100,core_,log_,false);
		behaviour_ = new DeviceProperty(arduinoLabel_, MConfiguration.ardproplabel[0], 0, 4,core_,log_,false);
		pulse_ = new DeviceProperty(arduinoLabel_, MConfiguration.ardproplabel[1], 0, MConfiguration.ardlasermaxpulse,core_,log_,false);

		add(operation_);
		add(powerPerc_);
		add(behaviour_);
		add(pulse_);
	}

	public String getArduinoLabel(){
		return arduinoLabel_;
	}

	public void setOperation(int val){	
		if(val==1){
			setProperty(operation_.getPropertyName(),"On");										/// maybe modify directly the object
		} else if(val==0){
			setProperty(operation_.getPropertyName(),"Off");				
		} else {
			log_.writeToLog(label_+" : Invalid operation requested ("+val+")");
		}
	}
	
	public void setPowerPercentage(int val){		
		if(val>=powerPerc_.getMinValue() && val<=powerPerc_.getMaxValue()){
			setProperty(powerPerc_.getPropertyName(),(int) (lowering_factor*val));
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
	
	public void setPulseLength(double dd){
		int d = (int) dd;
		if(d>=pulse_.getMinValue() && d<=pulse_.getMaxValue()){
			setProperty(pulse_.getPropertyName(),d);
		} else {
			log_.writeToLog(label_+" : Invalid pulse length  requested ("+d+")");
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
	
	public double getPulseLength(){									
		return getProperty(pulse_.getPropertyName());									
	}
	

	@Override
	public String getType() {
		return "Laser";
	}
}
