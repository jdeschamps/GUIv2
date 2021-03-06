package threader;

import device.Device;

public abstract class Updater {

	Device device_;
	Boolean running_ = false;
	int nOut_;
	protected double[] output_;
	
	Updater(Device d, int nOut){
		device_ = d;
		nOut_ = nOut;
		output_ = new double[nOut_];
	}
	
	public Device getDevice(){
		return device_;
	}

	public void start(){
		running_ = true;
	}
	
	public void stop(){
		running_ = false;
	}
	
	public boolean isRunning(){
		return running_;
	}
	
	public double getOutput(int index){
		if(index>=0 && index<nOut_){
			refresh();
			return output_[index];
		}
		return 0;
	}
	
	public abstract void refresh();
	
	public abstract void update();
	
	public String outputToString(){
		String s = "Output from "+device_.getLabel()+" :";
		for(int i=0;i<nOut_;i++){
			s += " "+output_[i];
		}
		return s;
	}
}
