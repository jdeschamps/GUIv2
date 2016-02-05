package threader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.micromanager.api.ScriptInterface;

import device.Device;
import mmcorej.CMMCore;

public class AcqMonitor extends Updater{

	private boolean acqrunning_ = false;
	private boolean acqended_ = false;
	private boolean acqstarted_ = false;
	private double x_=0, y_=0;
	private ScriptInterface gui_;
	private CMMCore core_;
	private double mindiff = 5;
	
	public AcqMonitor(Device d, ScriptInterface gui){
		super(d, 3);
		gui_ = gui;
		core_ = gui_.getMMCore();
	}

	public boolean isAcqRunning(){
		return gui_.isAcquisitionRunning();
	}
	
	public boolean checkAcqStatus(){
		boolean b = isAcqRunning();
		if(b==true && acqrunning_==false){
			acqstarted_ = true;
		} else if(b==false && acqrunning_==true){
			acqended_ = true;
		}
		acqrunning_ = b;
		return false;
	}
	
	public boolean hasAcqStarted(){
		if(acqstarted_){
			acqstarted_ = false;
			return true;
		}
		return false;
	}
	public boolean hasAcqEnded(){
		if(acqended_){
			acqended_ = false;
			return true;
		}
		return false;
	}
	
	public boolean hasXYPositionChanged(){
		double xn, yn;
		try {
			xn = core_.getXPosition("SmaractXY");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			yn = core_.getYPosition("SmaractXY");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(Math.abs(x_-xn)>mindiff || Math.abs(y_-yn)>mindiff ){
			x_ = xn;
			y_ = yn;
			
		
			return true;
		}

		x_ = xn;
		y_ = yn;

		return false;
	}

	@Override
	public void refresh() {
		output_[0] = boolToInt(hasAcqStarted());
		output_[1] = boolToInt(hasAcqEnded());
		output_[2] = boolToInt(hasXYPositionChanged());
	}

	@Override
	public void update() {		
		
	}

	public int boolToInt(boolean b){
		if(b){
			return 1;
		} else {
			return 0;
		}
	}
	
}
