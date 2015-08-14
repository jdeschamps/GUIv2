package device;

import micromanager.Log;
import mmcorej.CMMCore;

public class DeviceProperty extends InputDeviceProperty{

	private double initialvalue_;
	private double currentvalue_;
	private double min_, max_;

	
	DeviceProperty(String device, String property, double value, double min, double max, CMMCore core, Log log){
		super(device, property, core, log);
		min_ = min;
		max_ = max;
		
		if(value<=max_ && value>=min_){
			initialvalue_ = value;
			setValue(value);
		} else {		
			initialvalue_ = min;
			setValue(min);
		}
	}
	
	public double getInitialValue(){
		return initialvalue_;
	}

	public double getCurrentValue(){
		return currentvalue_;
	}

	public double getMinValue(){
		return min_;
	}

	public void setMinValue(double min){
		min_ = min;
	}
	
	public double getMaxValue(){
		return max_;
	}

	public void setMaxValue(double max){
		max_ = max;
	}

	public boolean initialize(){
		/// check if device loaded
		
		/// change property within micromanager
		
		/// try catch, if didn't work return false
		
		return true;
	}
	
	public boolean setValue(double val){
		/// check if device loaded

		if(val<= max_ && val>=min_){
			try {
				core_.setProperty(device_, property_, Double.toString(val));
				currentvalue_ = val;
				return true;
			} catch (Exception e) {
				log_.writeToLog(device_+" : error setting "+property_+" to "+Double.toString(val));
			}
		}
		
		return false;
	}
	
	public boolean isEditable(){
		return true;
	}
}
