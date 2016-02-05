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
	private boolean camstoped_ = false;
	private boolean camrunning_ = false;
	private double x_=0, y_=0;
	private ScriptInterface app_;
	private CMMCore core_;
	private double mindiff = 5;
	
	public AcqMonitor(Device d, ScriptInterface app){
		super(d, 3);
		app_ = app;
		core_ = app_.getMMCore();
	}

	public boolean isAcqRunning(){
		return app_.isAcquisitionRunning();
	}
	
	public void checkAcqStatus(){
		boolean b = isAcqRunning();
		if(b==true && acqrunning_==false){
			acqstarted_ = true;
		} else if(b==false && acqrunning_==true){
			acqended_ = true;
		}
		acqrunning_ = b;
	}

	public boolean isAcqPaused(){
		return app_.isPaused();
	}
	
	public boolean isCameraRunning(){
		return core_.isSequenceRunning();
	}
	
	public void checkCaneraStatus(){
		boolean b = isCameraRunning();
		if(b==false && acqrunning_==true){
			camstoped_ = true;
		}
		camrunning_ = b;
	}
	
	public boolean hasCameraStopedAcquiring(){
		if(camstoped_){
			camstoped_ = false;
			return true;
		}
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
		// Acq paused 
		output_[0] = boolToInt(hasAcqStarted());
		output_[1] = boolToInt(hasAcqEnded());
		output_[2] = boolToInt(isAcqPaused());
		
		// Sequence stoped running 
		//output_[0] = boolToInt(hasAcqStarted());
		//output_[1] = boolToInt(hasAcqEnded());
		//output_[2] = boolToInt(hasCameraStopedAcquiring());
		
		// XYStage moved
		//output_[0] = boolToInt(hasAcqStarted());
		//output_[1] = boolToInt(hasAcqEnded());
		//output_[2] = boolToInt(hasXYPositionChanged());
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
