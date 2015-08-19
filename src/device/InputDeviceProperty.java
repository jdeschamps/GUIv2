package device;

import micromanager.Log;
import mmcorej.CMMCore;

public class InputDeviceProperty {

	protected String device_;
	protected String property_;
	protected double initialvalue_;
	protected double currentvalue_;
	protected CMMCore core_;
	protected Log log_;
	
	InputDeviceProperty(String device, String property, CMMCore core, Log log){
		device_ = device;
		property_ = property;
		core_ = core;
		log_ = log;
		currentvalue_ = getValue();
	}
	
	public String getDevice(){
		return device_;
	}
	
	public String getPropertyName(){
		return property_;
	}
	
	public double getValue(){
																/// check if device loaded
		String val;
		try {
			val = core_.getProperty(device_, property_);
			if(isNum(val)){
				currentvalue_ = Integer.parseInt(val);
				return Integer.parseInt(val);
			}
		} catch (Exception e) {
			log_.writeToLog(device_+" : error getting "+property_);
		}

		return 0;
	}
	
	public double getInitialValue(){
		return initialvalue_;
	}

	public double getCurrentValue(){
		return currentvalue_;
	}
	
	public boolean initialize(){
		/// check if device loaded
		
		initialvalue_ = getValue();
		/// try catch, if didn't work return false

		return true;
	}
	
	public boolean isEditable(){
		return false;
	}
	
	public static boolean isNum(String strNum) {
	    boolean ret = true;
	    try {

	        Double.parseDouble(strNum);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    return ret;
	}
}
