package device;

import micromanager.Log;
import mmcorej.CMMCore;

public class TTL extends Device {

	String prop_;							////// to be uniformized!!!! (same state name in the device adapters)
	DeviceProperty position_;
	
	public TTL(String label, String prop, CMMCore core, Log log, boolean isLoaded) {
		super(label,core,log, isLoaded);
		prop_ = prop;
		createProperties();
	}
	
	private void createProperties() {
		position_ = new DeviceProperty(label_, prop_, 0, 1,core_,log_,false, !detected_);
		
		add(position_);
	}

	public String getPropertyName(){
		return prop_;
	}
	
	public double getState(){									
		return position_.getValue();									
	}

	public void setState(int val){
		if(val==0 || val == 1){
			setProperty(position_.getPropertyName(), val);
		} 
	}
	
	public int getNState(){
		return 2;
	}
	
	@Override
	public String getType() {
		return "TTL";
	}
}
