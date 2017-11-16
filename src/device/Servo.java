package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Servo extends Device{
	
	int nPos_;
	String prop_;							////// to be uniformized!!!! (same state name in the device adapters)
	DeviceProperty position_;
	int[] vals_;
	
	public Servo(String label, String prop, int nPos, CMMCore core, Log log, boolean isLoaded, int[] vals) {
		super(label,core,log, isLoaded);
		prop_ = prop;
		nPos_ = nPos;
		vals_ = vals;
		createProperties();
	}
	
	private void createProperties() {
		position_ = new DeviceProperty(label_, prop_, 0, MConfiguration.mojomaxpulse ,core_,log_,false, !detected_);
		
		add(position_);
	}

	public String getPropertyName(){
		return prop_;
	}
	
	public double getState(){									
		return position_.getValue();									
	}

	public void setState(int val){
		if(val>=0 && val<nPos_){
			setProperty(position_.getPropertyName(), vals_[val]);	
		}
	}
	
	public int getNState(){
		return nPos_;
	}
	
	@Override
	public String getType() {
		return "Servo";
	}
}
