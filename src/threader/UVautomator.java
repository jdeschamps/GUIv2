package threader;

import device.Device;
import device.Laser;

public class UVautomator extends Updater{

	int N;
	double pulse;
	
	public UVautomator(Device d) {
		super(d, 2);
		if(!d.getLabel().equals("Luxx405")){
			// exception!
		}
	}

	@Override
	public void refresh() {
		// dummy
		pulse = ((Laser) device_).getPulseLength()+10;
		output_[1] = pulse;
		output_[0] = N;
		update();
	}

	@Override
	public void update() {
		// update uv pulse
		((Laser) device_).setPulseLength(this.getOutput(1));
	}

}
