package device;

import micromanager.Log;
import mmcorej.CMMCore;

public class DeviceProperty extends InputDeviceProperty{

	private double min_, max_;
	private boolean str_;
	String current_;
	
	DeviceProperty(String device, String property, double min, double max, CMMCore core, Log log, boolean str){
		super(device, property, core, log);
		min_ = min;
		max_ = max;
		str_ = str;
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
	
	public boolean setToInitial(){
		return setValue(initialvalue_);
	}

	public boolean setValue(double val){
		/// check if device loaded
		System.out.println(val);
		if(val<= max_ && val>=min_){
			try {
				if(!isDeviceBusy()){
					core_.setProperty(device_, property_, val);
					currentvalue_ = val;
					return true;
				}
			} catch (Exception e) {
				log_.writeToLog(device_+" : error setting "+property_+" to "+Double.toString(val));
			}
		}
		
		return false;
	}

	public boolean setValue(String val){
		try {
			if(!isDeviceBusy()){
				core_.setProperty(device_, property_, val);
				current_ = val;
				return true;
			}
		} catch (Exception e) {
			log_.writeToLog(device_+" : error setting "+property_+" to "+val);
		}
		return false;
	}
	
	public boolean isEditable(){
		return true;
	}
	
	public boolean isDeviceBusy() throws Exception{
		return core_.deviceBusy(getDevice());
	}
}
