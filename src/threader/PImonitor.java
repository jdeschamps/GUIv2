package threader;

import device.Stage;

public class PImonitor extends Updater {

	double pos;
	
	public PImonitor(Stage pi) {
		super(pi, 1);
	}

	@Override
	public void refresh() {
		pos =  ((Stage) device_).getPosition();
		output_[0] = pos;
	}

	@Override
	public void update() {
		// Leave blank
	}	
	
	
}
