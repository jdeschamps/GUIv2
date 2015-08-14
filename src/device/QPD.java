package device;

import micromanager.Configuration;
import micromanager.Log;
import mmcorej.CMMCore;

public class QPD extends Device{

	InputDeviceProperty signalX_;
	InputDeviceProperty signalY_;
	InputDeviceProperty signalS_;
	
	QPD(String label, CMMCore core, Log log) {
		super(label,core,log);
		createProperties();
	}

	private void createProperties() {
		signalX_ = new InputDeviceProperty(label_, Configuration.qpdproplabel[0],core_,log_);
		signalY_ = new InputDeviceProperty(label_, Configuration.qpdproplabel[1],core_,log_);
		signalS_ = new InputDeviceProperty(label_, Configuration.qpdproplabel[2],core_,log_);

		add(signalX_);
		add(signalY_);
		add(signalS_);
	}

	public double getXSignal(){									
		return getProperty(signalX_.getPropertyName());									
	}

	public double getYSignal(){									
		return getProperty(signalY_.getPropertyName());									
	}

	public double getSSignal(){									
		return getProperty(signalS_.getPropertyName());									
	}
	

	@Override
	public String getType() {
		return "QPD";
	}
}
