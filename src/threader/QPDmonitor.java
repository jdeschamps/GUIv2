package threader;

import device.Device;
import device.QPD;

public class QPDmonitor extends Updater{

	double X,Y,S;
	
	public QPDmonitor(Device d) {
		super(d, 3);
	}

	@Override
	public void refresh() {
		X = ((QPD) device_).getXSignal();
		output_[0] = X;
		Y = ((QPD) device_).getYSignal();
		output_[0] = Y;
		S = ((QPD) device_).getSSignal();
		output_[0] = S;
	}

	@Override
	public void update() {
		// Leave blank
	}
}
