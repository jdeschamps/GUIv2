package device;

import java.util.ArrayList;

import micromanager.Log;
import mmcorej.CMMCore;

public abstract class Device {

	String label_;
	ArrayList<InputDeviceProperty> properties_;
	CMMCore core_;
	Log log_;
	
	Device(String label, CMMCore core, Log log){
		label_ = label;
		core_ = core;
		properties_ = new ArrayList<InputDeviceProperty>();
		log_ = log;
		
		boolean b=isLoaded();										///// use this as a test
	}

	public abstract String getType();
	
	public void add(InputDeviceProperty d){
		properties_.add(d);
	}
	
	public void addProperty(String label, String property, int val, int min, int max){
		properties_.add(new DeviceProperty(label,property,val,min,max,core_,log_));
	}

	public void initialize(){
		for(InputDeviceProperty p:properties_){
			if(p.isEditable()){
				((DeviceProperty) p).initialize();		///// better way to do that??
			}
		}
	}
	
	public boolean isProperty(String property){
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				return true;
			}
		}
		return false;
	}
	
	public void setProperty(String property, int val){
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				if(p.isEditable()){
					boolean b = ((DeviceProperty) p).setValue(val);	
																		//// check if worked
				}
			}
		}
	}	
	
	public void setProperty(String property, double val){
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				if(p.isEditable()){
					boolean b = ((DeviceProperty) p).setValue(val);	
																		//// check if worked
				}
			}
		}
	}
	
	public double getProperty(String property){
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				double val = p.getValue();	
				return val;
			}
		}
		return 0;													/// what is the convention here? input reference as return argument?
	}
	
	public String getLabel(){
		return label_;
	}
	
	public int getNProp(){
		return properties_.size();
	}

	public boolean isLoaded(){
			////////// test here
		return false;
	}

	public boolean isAvailable(){
		////////// test here
	return false;
	}
	
}
