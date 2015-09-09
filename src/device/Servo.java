package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class Servo extends Device{
	
	int nPos_;
	String prop_;							////// to be uniformized!!!! (same state name in the device adapters)
	DeviceProperty position_;
	
	public Servo(String label, String prop, int nPos, CMMCore core, Log log) {
		super(label,core,log);
		prop_ = prop;
		nPos_ = nPos;
		createProperties();
	}
	
	private void createProperties() {
		if(!label_.equals(MConfiguration.servolabel[0])){
			position_ = new DeviceProperty(label_, prop_, 0, nPos_,core_,log_,false);
		} else {
			position_ = new DeviceProperty(label_, prop_, 0, 2000,core_,log_,false);
		}
		add(position_);
	}

	public String getPropertyName(){
		return prop_;
	}
	
	public double getState(){									
		return position_.getCurrentValue();									
	}

	public void setState(int val){
		if(!label_.equals(MConfiguration.servolabel[0])){
			if(val>=0 && val<nPos_){
				setProperty(position_.getPropertyName(), val);	
				return;
			} else {
				log_.writeToLog(label_+" : Invalid position requested ("+val+")");
			}
		} else {			// maestro
			switch(val){
			case 0:
				val = MConfiguration.maestropos[0];
				break;
			case 1:
				val = MConfiguration.maestropos[1];
				break;
			case 2:
				val = MConfiguration.maestropos[2];
				break;
			case 3:
				val = MConfiguration.maestropos[3];
				break;
			default:
				val = MConfiguration.maestropos[4];
				break;
			}
			setProperty(position_.getPropertyName(), val);									
			return;
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
