package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Stage extends Device{

	DeviceProperty sensorState_;
	DeviceProperty position_;
	boolean smaractDetected_;

	public Stage(String label, CMMCore core, Log log, boolean isLoaded, boolean smaractDetected) {
		super(label, core, log, isLoaded);
		smaractDetected_ = smaractDetected;
		createProperties();
	}

	private void createProperties() {
		sensorState_ = new DeviceProperty(label_, MConfiguration.piproplabel[0], 0, 1,core_,log_,false, detected_);
		position_ = new DeviceProperty(label_, MConfiguration.piproplabel[1], 0, 100,core_,log_,false, detected_);					/// to check
		
		add(sensorState_);
		add(position_);
	}
	
	public void setSensorState(int val){									
		if(val==1){
			setProperty(sensorState_.getPropertyName(),val);
			try {
				if(smaractDetected_){
					core_.setFocusDevice(MConfiguration.smaractlabel);
				}
			} catch (Exception e) {
				log_.writeToLog(label_+" : error setting focus device "+MConfiguration.smaractlabel);
			}
		} else if(val==0){
			//setProperty(sensorState_.getPropertyName(),val);							/// maybe modify directly the object
			try {
				if(detected_){
					setProperty(sensorState_.getPropertyName(),val);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(detected_){
					core_.setFocusDevice("PIZStage");
				}
			} catch (Exception e) {
				log_.writeToLog(label_+" : error setting focus device "+label_);
			}
		} else {
			log_.writeToLog(label_+" : Invalid sensor state ("+val+")");
		}
	}
	
	public void setPosition(double val){
		if(val>=position_.getMinValue() && val<=position_.getMaxValue() ){
			//setProperty(position_.getPropertyName(),val);
			try {
				setProperty(position_.getPropertyName(),val);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			log_.writeToLog(label_+" : Invalid position requested ("+val+")");
		}
	}

	public double getSensorState(){									
		return getProperty(sensorState_.getPropertyName());									
	}
	
	public double getPosition(){									
		try {
			return getProperty(position_.getPropertyName());	
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
