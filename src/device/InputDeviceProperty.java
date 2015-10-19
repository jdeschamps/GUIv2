package device;

import micromanager.Log;
import mmcorej.CMMCore;

public class InputDeviceProperty {

	protected String device_;
	protected String property_;
	protected double initialvalue_ = 0;
	protected double currentvalue_ = 0;
	protected CMMCore core_;
	protected Log log_;
	protected boolean isEmpty_;
	
	InputDeviceProperty(String device, String property, CMMCore core, Log log, boolean emptyProperty){
		device_ = device;
		property_ = property;
		core_ = core;
		log_ = log;
		isEmpty_ = emptyProperty;
		currentvalue_ = getValue();
	}
	
	public String getDevice(){
		return device_;
	}
	
	public String getPropertyName(){
		return property_;
	}
	
	public double getValue(){
		if(!isEmpty_){
			String val;
			try {
				val = core_.getProperty(device_, property_);
				if(isDouble(val)){
					currentvalue_ = Double.parseDouble(val);
					return currentvalue_;
				} else if(val.equals("On")){
					return 1;
				} else if(val.equals("Off")){
					return 0;
				} 
			} catch (Exception e) {
				log_.writeToLog(device_+" : error getting "+property_);
			}
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
	
	public boolean isDouble(String value)
	{        
	    boolean seenDot = false;
	    boolean seenExp = false;
	    boolean justSeenExp = false;
	    boolean seenDigit = false;
	    for (int i=0; i < value.length(); i++)
	    {
	        char c = value.charAt(i);
	        if (c >= '0' && c <= '9')
	        {
	            seenDigit = true;
	            continue;
	        }
	        if ((c == '-' || c=='+') && (i == 0 || justSeenExp))
	        {
	            continue;
	        }
	        if (c == '.' && !seenDot)
	        {
	            seenDot = true;
	            continue;
	        }
	        justSeenExp = false;
	        if ((c == 'e' || c == 'E') && !seenExp)
	        {
	            seenExp = true;
	            justSeenExp = true;
	            continue;
	        }
	        return false;
	    }
	    if (!seenDigit)
	    {
	        return false;
	    }
	    try
	    {
	        Double.parseDouble(value);
	        return true;
	    }
	    catch (NumberFormatException e)
	    {
	        return false;
	    }
	}
}
