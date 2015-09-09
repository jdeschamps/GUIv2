package threader;

import device.Device;
import device.QPD;

public class QPDmonitor extends Updater{

	double X,Y,S;
	
	public QPDmonitor(Device d) {
		super(d, 3);
		
		X=0;
		Y=700;
		S=0;
	}

	@Override
	public void refresh() {
		X = ((QPD) device_).getXSignal();
		output_[0] = X;
		Y = ((QPD) device_).getYSignal();
		output_[1] = Y;
		S = ((QPD) device_).getSSignal();
		output_[2] = S;
		System.out.println(X+" "+Y+" "+S);
	}

	@Override
	public void update() {
		// Leave blank
	}
}
