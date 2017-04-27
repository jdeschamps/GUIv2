package threader;


import gui.ActivationTab;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.GaussianBlur;
import ij.process.ImageProcessor;
import ij.process.ShortProcessor;
import micromanager.Log;
import micromanager.MConfiguration;
import mmcorej.CMMCore;
import mmcorej.TaggedImage;
import activation.NMS;
import device.Device;
import device.Laser;
import device.MSystem;

public class UVautomator extends Updater{

	int N_, newpulse_,maxpulse_;
	double pulse_, prevpulse_, avparam_;
	MSystem sys_;
	CMMCore core_;
	Log log_;
	ActivationTab pane_;
	ImageProcessor ip_;
	boolean cutoffinit_ = false;
	double cutoff_;
	boolean restart_ = false;
	
	// Constants
	double min = 0.4;
	
	
	public UVautomator(Device d, MSystem sys, Log log, ActivationTab activationTab) {
		super(d, 3);
		sys_ = sys;
		log_ = log;
		core_ = sys_.getCore();
		pane_ = activationTab;

		ip_ = new ShortProcessor(200,200);
		

		
		maxpulse_ = (int) (sys_.getExposureTime()*1000);
	}

	@Override
	public void refresh() {	
		if(!sys_.isCameraAcquiring()){
			restart_ = true;
		}
		
		if(!restart_){
			N_ = getN();
			newpulse_ = getNewPulse();
		
			output_[0] = N_;
			output_[1] = newpulse_;
			output_[2] = cutoff_;
			update();
		} else if(restart_){
			restart_ = false;
			N_ = 0;
			pulse_ = min;
			prevpulse_ = min;
			cutoff_ = 0;
			newpulse_ = 0;
			output_[0] = 0;
			output_[1] = 0;
			output_[2] = 0;
			update();
		}
	}

	public int getN() { 
		if(core_.isSequenceRunning() && core_.getBytesPerPixel() == 2){
			
			int width, height;
			double tempcutoff;

			TaggedImage tagged1 = null, tagged2 = null;
			ShortProcessor ip, ip2;
			ImagePlus imp, imp2;
			ImageCalculator calcul = new ImageCalculator(); 
			ImagePlus imp3;
			GaussianBlur gau = new GaussianBlur();
			NMS NMSuppr = new NMS();
		   	 
			width = (int) core_.getImageWidth();
			height = (int) core_.getImageHeight(); 
			
			int  buffsize = core_.getImageBufferSize();
			System.out.println("[buffer] buffer size is: "+buffsize);
			
			if(buffsize>MConfiguration.timeDistanceBckgd){
				try {
					tagged1 = core_.getLastTaggedImage();
					tagged2 = core_.getNBeforeLastTaggedImage(MConfiguration.timeDistanceBckgd);
				} catch (Exception e) {
					log_.writeToLog("---[UV]--- Couldn't get tagged images");
				}		
				
				ip = new ShortProcessor(width, height);
				ip2 = new ShortProcessor(width, height);
			
				ip.setPixels(tagged1.pix);
				ip2.setPixels(tagged2.pix);

				imp = new ImagePlus("", ip);
				imp2 = new ImagePlus("", ip2);
				
				// Subtraction
				imp3 = calcul.run("Substract create", imp, imp2);
				
				// Gaussian filter
				gau.blurGaussian(imp3.getProcessor(), MConfiguration.gaussianMaskSize,  MConfiguration.gaussianMaskSize,  MConfiguration.gaussianMaskPrecision);

				try{
					tempcutoff = imp3.getStatistics().mean+pane_.getThreshold()*imp3.getStatistics().stdDev;
				} catch(Exception e){
					tempcutoff = cutoff_;
				}
				
				if( pane_.isAutoCutoffOn() || pane_.isCutoffNeeded()){
					avparam_ = 1/((double)pane_.getdT());
					cutoff_ = (1-avparam_)*cutoff_+tempcutoff*avparam_;
				} else {
					cutoff_ = pane_.getCutoff();
					if(cutoff_ == 0){
						cutoff_ = tempcutoff;
					}
				}
				ip_ = NMSuppr.run(imp3, MConfiguration.nmsMaskSize, cutoff_);

			} else {
				return 0;
			}
/*
			writer.println("[UV] temporary cutoff: "+tempcutoff);
			writer.println("[UV] is auto cutoff: "+pane_.isAutoCutoffOn());
			writer.println("[UV] is get cutoff: "+pane_.isCutoffNeeded());
			writer.println("[UV] kept cutoff: "+cutoff_);
	*/		
			return NMSuppr.getN();
		}
		return 0;
	}

	public ImageProcessor getNMSresult(){
		return ip_;
	}

	public int getNewPulse(){			// code should be cleaned, variables are not used properly
		double N = (double) N_;
		double N0 = pane_.getN();
		double temppulse=0;

		//writer.println("[UV] calculated N: "+N);
		//writer.println("[UV] requested N: "+N0);
		
		pulse_ = sys_.getUVPulse(); 
		//writer.println("[UV] current pule: "+pulse_);

		if(sys_.isCameraAcquiring() && pane_.isUVselected()){			
			if(prevpulse_ < min){
				pulse_ = min;			
			} else {
				pulse_ = prevpulse_;	// avoid getting stuck between 0 and 1 (otherwise newp=0.4+0.4*1.99*coeff < 1 unless coeff ~> 0.7 which is not good for higher values), but now it is not bounded t0 <1, keep or change????
			}
			//writer.println("[UV] used pulse: "+pulse_);

			// calculate new pulse
			if(N0 != 0){
				temppulse = pulse_*(1+pane_.getFeedback()*(1-N/N0));
			} else {
				return 0;
			}
			//writer.println("[UV] temporary pulse: "+temppulse);

			if(temppulse < min){
				temppulse = min;
			}
	
			// if new pulse is higher than camera exposure
			double exp = 1000*sys_.getExposureTime();
			if(temppulse > exp) {
				temppulse = exp; 
			}

			prevpulse_ = temppulse;
		//	writer.println("[UV] calculated pulse: "+prevpulse_);

		} else {
			prevpulse_ = pulse_;
		}
		
		if(prevpulse_ > maxpulse_){
			prevpulse_ = maxpulse_;
		}
		
	//	writer.println("[UV] returned pulse: "+(int) Math.floor(prevpulse_));
		
		return (int) Math.floor(prevpulse_);
	}
	
	@Override
	public void update() {
		if(sys_.isCameraAcquiring() && pane_.isUVselected()){
			try{
				((Laser) device_).setPulseLength((int) this.getOutput(1));
			} catch (Exception e){
			}
		}
	}
	
	public void resetPulse() {
		if(pane_.isUVselected()){
			try{
				((Laser) device_).setPulseLength(0);
			} catch (Exception e){
			}
		}
	}
	
	public void restart() {
		restart_ = true;
		resetPulse();
	}
	
	public void setMaxPulse(int maxpulse){
		maxpulse_ = maxpulse;
	}
}
