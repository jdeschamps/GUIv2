package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Laser extends Device{

	String controllerLabel_, em_, en_, perc, name_;								//// might want to rename it
	DeviceProperty powerPerc_;
	DeviceProperty enable_;
	DeviceProperty emission_;
	DeviceProperty behaviour_;
	DeviceProperty pulse_;
	DeviceProperty sequence_;
	double lowering_factor;
	boolean enalways_;
	int defaultmode = 4;
	
	public Laser(String label, String controllerlabel, String name, String enable, String percen, String emission, CMMCore core, Log log, boolean isLoaded, boolean enalways){
		super(label,core,log, isLoaded);

		em_ = emission;
		en_ = enable;
		perc = percen;
		controllerLabel_ = controllerlabel;
		name_=name;
		enalways_ = enalways;
		
		if(label_.equals(MConfiguration.laserkeys[2])){
			lowering_factor = MConfiguration.loweringfactor;
		} else {
			lowering_factor = 1;
		}
		
		createProperties();
	}
	
	private void createProperties() {

		emission_ = new DeviceProperty(label_, em_, 0, 1,core_,log_,true, !detected_);
		enable_ = new DeviceProperty(label_, en_, 0, 1,core_,log_,true, !detected_);
		powerPerc_ = new DeviceProperty(label_, perc, 0, 100,core_,log_,false, !detected_);

		
		behaviour_ = new DeviceProperty(controllerLabel_, MConfiguration.getLaserMojoProp(name_)[0], 0, 4,core_,log_,false, !detected_);
		pulse_ = new DeviceProperty(controllerLabel_, MConfiguration.getLaserMojoProp(name_)[1], 0, MConfiguration.mojomaxpulse,core_,log_,false, !detected_);
		sequence_ = new DeviceProperty(controllerLabel_, MConfiguration.getLaserMojoProp(name_)[2], 0, MConfiguration.mojomaxpulse,core_,log_,false, !detected_);

		System.out.println("--------- "+controllerLabel_+" "+label_+" "+MConfiguration.getLaserMojoProp(name_)[0]);

		add(emission_);
		add(enable_);
		add(powerPerc_);
		add(behaviour_);
		add(pulse_);
		add(sequence_);
	}

	public String getArduinoLabel(){
		return controllerLabel_;
	}

	public void setOperation(int val){	
		if (enalways_) {
			if (val == 1) {
				if(((int) getProperty(enable_.getPropertyName())) == 0){
					setProperty(enable_.getPropertyName(), "1");
					setProperty(behaviour_.getPropertyName(),defaultmode);
				} else if(((int) getProperty(enable_.getPropertyName())) == 1){
					setProperty(behaviour_.getPropertyName(),defaultmode);
				}
			} else if (val == 0) {
				if(((int) getProperty(enable_.getPropertyName())) == 1){
					defaultmode = (int) behaviour_.getCurrentValue();
					setProperty(behaviour_.getPropertyName(),0);
				}
			} else {
				log_.writeToLog(label_ + " : Invalid operation requested (" + val + ")");
			}
		} else {
			if (val == 1) {
				setProperty(enable_.getPropertyName(), "1"); 
			} else if (val == 0) {
				setProperty(enable_.getPropertyName(), "0");
			} else {
				log_.writeToLog(label_ + " : Invalid operation requested (" + val + ")");
			}
		}
	}
	
	public void setEmissionOff(){
		setProperty(emission_.getPropertyName(), "0");
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
			System.out.println(behaviour_.getPropertyName());
			setProperty(behaviour_.getPropertyName(),val);
		} else {
			log_.writeToLog(label_+" : Invalid behaviour requested ("+val+")");
		}
	}

	public void setPulseLength(int d){
		if(d>=pulse_.getMinValue() && d<=pulse_.getMaxValue()){
			setProperty(pulse_.getPropertyName(),d);
		} else {
			log_.writeToLog(label_+" : Invalid pulse length  requested ("+d+")");
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
		return (int) getProperty(enable_.getPropertyName());									
	}
	
	public int getPowerPercentage(){									
		return (int) getProperty(powerPerc_.getPropertyName());									
	}
	
	public int getBehaviour(){									
		return (int) getProperty(behaviour_.getPropertyName());									
	}

	public int getPulseLength(){									
		return (int) getProperty(pulse_.getPropertyName());									
	} 

	public int getSequence(){									
		return (int) getProperty(sequence_.getPropertyName());									
	} 
	

	@Override
	public String getType() {
		return "Laser";
	}
}
