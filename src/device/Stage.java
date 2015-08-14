package device;

import micromanager.Configuration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Stage extends Device{

	DeviceProperty sensorState_;
	DeviceProperty position_;

	Stage(String label, CMMCore core, Log log) {
		super(label, core, log);
		createProperties();
	}

	private void createProperties() {
		sensorState_ = new DeviceProperty(label_, Configuration.piproplabel[0], 0, 0, 1,core_,log_);
		position_ = new DeviceProperty(label_, Configuration.piproplabel[1], 0, 0, 100,core_,log_);					/// to check
		
		add(sensorState_);
		add(position_);
	}
	
	public void setSensorState(int val){									
		if(val==1){
			setProperty(sensorState_.getPropertyName(),val);
			try {
				core_.setFocusDevice(Configuration.smaractlabel);
			} catch (Exception e) {
				log_.writeToLog(label_+" : error setting focus device "+Configuration.smaractlabel);
			}
		} else if(val==0){
			setProperty(sensorState_.getPropertyName(),val);							/// maybe modify directly the object
			try {
				core_.setFocusDevice("PIZStage");
			} catch (Exception e) {
				log_.writeToLog(label_+" : error setting focus device "+label_);
			}
		} else {
			log_.writeToLog(label_+" : Invalid sensor state ("+val+")");
		}
	}
	
	public void setPosition(double val){
		if(val>=position_.getMinValue() && val<=position_.getMaxValue() ){
			setProperty(position_.getPropertyName(),val);
		} else {
			log_.writeToLog(label_+" : Invalid position requested ("+val+")");
		}
	}

	public double getSensorState(){									
		return getProperty(sensorState_.getPropertyName());									
	}
	
	public double getPosition(){									
		try {
			return core_.getPosition(label_);
		} catch (Exception e) {
			log_.writeToLog(label_+" : error getting position");
		}
		return 0;									
	}

	@Override
	public String getType() {
		return "Stage";
	}
}
