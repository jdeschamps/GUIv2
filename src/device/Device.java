package device;

import java.util.ArrayList;

import micromanager.Log;
import mmcorej.CMMCore;

public abstract class Device {

	boolean detected_;
	String label_;
	ArrayList<InputDeviceProperty> properties_;
	CMMCore core_;
	Log log_;
	
	Device(String label, CMMCore core, Log log, boolean isLoaded){
		label_ = label;
		core_ = core;
		properties_ = new ArrayList<InputDeviceProperty>();
		log_ = log;
		
		detected_ = isLoaded;										
	}

	public abstract String getType();
	
	public void add(InputDeviceProperty d){
		properties_.add(d);
	}
	
	public void addProperty(String label, String property, int min, int max, boolean isString){
		properties_.add(new DeviceProperty(label,property,min,max,core_,log_,isString, !detected_));
	}

	public void initialize(){
		for(InputDeviceProperty p:properties_){
			p.initialize();	
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
	
	public boolean setProperty(String property, int val){
		System.out.println("[GUI] set property "+property+" "+val);
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				if(p.isEditable()){
					return ((DeviceProperty) p).setValue(val);	
				}
			}
		}
		return false;
	}	

	public boolean setProperty(String property, double val){
		System.out.println("[GUI] set property "+property+" "+val);
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				if(p.isEditable()){
					return ((DeviceProperty) p).setValue(val);	
				}
			}
		}
		return false;
	}

	public boolean setProperty(String property, String val){
		System.out.println("[GUI] set property "+property+" "+val);
		for(InputDeviceProperty p:properties_){
			if(p.getPropertyName().equals(property)){
				if(p.isEditable()){
					return ((DeviceProperty) p).setValue(val);	
				}
			}
		}
		return false;
	}
	
	public double getProperty(String property){
		System.out.println("[GUI] get property "+property);
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

	public boolean isDetected(){
		return detected_;
	}

	public boolean isBusy() throws Exception{
		return core_.deviceBusy(getLabel());
	}
	
}
