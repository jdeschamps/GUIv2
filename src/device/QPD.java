package device;

import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class QPD extends Device{

	InputDeviceProperty signalX_;
	InputDeviceProperty signalY_;
	InputDeviceProperty signalS_;
	
	public QPD(String label, CMMCore core, Log log, boolean isLoaded) {
		super(label,core,log, isLoaded);
		createProperties();
	}

	private void createProperties() {
		signalX_ = new InputDeviceProperty(label_, MConfiguration.qpdproplabel[2],core_,log_, !detected_);
		signalY_ = new InputDeviceProperty(label_, MConfiguration.qpdproplabel[1],core_,log_, !detected_);
		signalS_ = new InputDeviceProperty(label_, MConfiguration.qpdproplabel[1],core_,log_, !detected_);

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
