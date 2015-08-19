package device;

import micromanager.Log;
import mmcorej.CMMCore;

public class Servo extends Device{
	
	int nPos_;
	String prop_;							////// to be uniformized!!!! (same state name in the device adapters)
	DeviceProperty position_;
	
	Servo(String label, String prop, int nPos, CMMCore core, Log log) {
		super(label,core,log);
		prop_ = prop;
		nPos_ = nPos;
		createProperties();
	}
	
	private void createProperties() {
		position_ = new DeviceProperty(label_, prop_, 0, nPos_,core_,log_);

		add(position_);
	}

	public String getPropertyName(){
		return prop_;
	}
	
	public double getState(){									
		return position_.getCurrentValue();									
	}

	public void setState(int val){
		if(val>=0 && val<nPos_){
			setProperty(position_.getPropertyName(), val);									
		} else {
			log_.writeToLog(label_+" : Invalid position requested ("+val+")");
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
