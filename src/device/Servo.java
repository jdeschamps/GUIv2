package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Servo extends Device{
	
	int nPos_;
	String prop_;							////// to be uniformized!!!! (same state name in the device adapters)
	DeviceProperty position_;
	
	public Servo(String label, String prop, int nPos, CMMCore core, Log log, boolean isLoaded) {
		super(label,core,log, isLoaded);
		prop_ = prop;
		nPos_ = nPos;
		createProperties();
	}
	
	private void createProperties() {
		position_ = new DeviceProperty(label_, prop_, 0, nPos_,core_,log_,false, !detected_);

		add(position_);
	}

	public String getPropertyName(){
		return prop_;
	}
	
	public double getState(){									
		return position_.getValue();									
	}

	public void setState(int val){
		if(val>=0 && val<MConfiguration.maxservos){
				setProperty(position_.getPropertyName(), val);	
				System.out.println(position_.getPropertyName()+" "+val);
				return;
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
