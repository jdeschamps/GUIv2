package threader;

import swing.ActivationPanel;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.GaussianBlur;
import ij.process.ShortProcessor;
import micromanager.Log;
import mmcorej.CMMCore;
import mmcorej.TaggedImage;
import activation.NMS;
import device.Device;
import device.Laser;
import device.MSystem;

public class UVautomator extends Updater{

	int N_;
	double pulse_, newpulse_;
	MSystem sys_;
	CMMCore core_;
	Log log_;
	ActivationPanel pane_;
	double cutoffArray[];
	int count = 0;

	double cutoff_;
	
	// Constants
	double min = 0.4;

	
	public UVautomator(Device d, MSystem sys, Log log, ActivationPanel pane) {
		super(d, 3);
		sys_ = sys;
		log_ = log;
		core_ = sys_.getCore();
		pane_ = pane;
		
	    cutoffArray = new double[10];
	    for(int i=0;i<10;i++){
	    	cutoffArray[i]=0;
	    }
		
		if(!d.getLabel().equals("Luxx405")){
			// exception!
		}
	}

	@Override
	public void refresh() {
		N_ = getN();
		newpulse_ = getNewPulse();
		
		output_[0] = N_;
		output_[1] = newpulse_;
		output_[2] = cutoff_;
		update();

	}

	public int getN() { 
		if(core_.isSequenceRunning()){
			int width, height;
			long byteDepth;
			double tempcutoff;
			TaggedImage tagged1 = null, tagged2 = null;
			ShortProcessor ip, ip2;
			ImagePlus imp, imp2;
			ImageCalculator calcul = new ImageCalculator(); 
			ImagePlus imp3;
			GaussianBlur gau = new GaussianBlur();;
			NMS NMSuppr = new NMS();
	
			count++;
		   	 
			width = (int) core_.getImageWidth();
			height = (int) core_.getImageHeight();
		   	 
			byteDepth = core_.getBytesPerPixel();
	
			if(byteDepth == 2){   														
				if(!core_.isSequenceRunning()){
					try {
						sys_.setUVPulse(0);
					} catch (Exception e) {
						log_.writeToLog("Couldn't set property UV");
					}
				} else {
	
					try {
						tagged1 = core_.getLastTaggedImage();
						tagged2 = core_.getNBeforeLastTaggedImage(20);
					} catch (Exception e) {
						log_.writeToLog("Couldn't get tagged images");
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
					gau.blurGaussian(imp3.getProcessor(), 2, 2, 0.01);
					ImagePlus imp4 = imp3.duplicate();
			   	      				
					tempcutoff = imp3.getStatistics().mean+pane_.getThreshold()*imp3.getStatistics().stdDev;	
					cutoffArray[count%10] = tempcutoff;
			           	      
					if( (pane_.isAutoCutoffOn() && count%10==9) || pane_.isCutoffNeeded()){
						cutoff_ = meanArray(cutoffArray);
					}
			           	      
					NMSuppr.run(imp3,10,300,cutoff_,false);
	 			}
			}
			return NMSuppr.getN();
		}
		return 0;
	}
	
	public double meanArray(double[] a){
		int s = a.length;
		double n = 0;
		for(int i=0;i<s;i++){
			n = n+a[i];
		}
		n=n/s;
		return n;
	}
	
	public double getNewPulse(){
		double N = (double) N_;
		double N0 = pane_.getN();
		
		double temppulse=0;

		pulse_ = sys_.getUVPulse();

		// If the pulse is 0, need a non-null starting point
		if(pulse_ == 0){
			pulse_ = min;
		}

		// calculate new pulse
		if(N0 != 0){
			temppulse = pulse_*(1+pane_.getFeedback()*(1-N/N0));
		} 

		if(temppulse < min){
			temppulse = min;
		}

		// if new pulse is higher than camera exposure
		double exp = 1000*sys_.getExposureTime();
		if(temppulse > exp) {
			temppulse = (int) exp; 
		}

		return temppulse;
	}
	
	@Override
	public void update() {
		((Laser) device_).setPulseLength(this.getOutput(1));
	}
}
