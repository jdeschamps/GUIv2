package threader;

import java.util.ArrayList;

import gui.ActivationTab;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.GaussianBlur;
import ij.process.ImageProcessor;
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
	double pulse_, newpulse_,prevpulse_;
	MSystem sys_;
	CMMCore core_;
	Log log_;
	ActivationTab pane_;
	//ArrayList<Double> cutoffArray, nArray, pulseArray;
	//double cutoffArray[];
	//int nArray[];
	int count = 0;
	ImageProcessor ip_;
	boolean cutoffinit_ = false;
	double cutoff_;
	
	// Constants
	double min = 0.4;

	
	public UVautomator(Device d, MSystem sys, Log log, ActivationTab activationTab) {
		super(d, 3);
		sys_ = sys;
		log_ = log;
		core_ = sys_.getCore();
		pane_ = activationTab;

		ip_ = new ShortProcessor(200,200);
		
		//cutoffArray = new ArrayList<Double>();
		//nArray = new ArrayList<Double>();
		
	   /* cutoffArray = new double[10];
	    for(int i=0;i<10;i++){
	    	cutoffArray[i]=0;
	    }
	    nArray = new int[sizeNarray];
	    for(int i=0;i<sizeNarray;i++){
	    	nArray[i]=0;
	    }*/
		
		if(!d.getLabel().equals("Luxx405")){
			// exception!
		}
	}

	@Override
	public void refresh() {
		System.out.println("[UV]0 Get N start");
		N_ = getN();
		System.out.println("[UV]15 Got N: "+N_);

		System.out.println("[UV]16 Get new pulse");
		newpulse_ = getNewPulse();
		//System.out.println("Got new pulse");
		
		output_[0] = N_;
		output_[1] = (int) newpulse_;
		output_[2] = cutoff_;
		update();

	}

	public int getN() { 
		if(core_.isSequenceRunning() && core_.getBytesPerPixel() == 2){
			System.out.println("[UV]1 Sequence running");
			//System.out.println("Sequence running");
			int width, height;
			double tempcutoff;
			//int tempn;
			TaggedImage tagged1 = null, tagged2 = null;
			ShortProcessor ip, ip2;
			ImagePlus imp, imp2;
			ImageCalculator calcul = new ImageCalculator(); 
			ImagePlus imp3;
			GaussianBlur gau = new GaussianBlur();;
			NMS NMSuppr = new NMS();
	
			count++;

			if(count == 1000000){
				count = 0;
			}
		   	 
			width = (int) core_.getImageWidth();
			height = (int) core_.getImageHeight();
			System.out.println("[UV]2 got width and height");
 
			
			try {
				tagged1 = core_.getLastTaggedImage();
				tagged2 = core_.getNBeforeLastTaggedImage(20);
			} catch (Exception e) {
				log_.writeToLog("Couldn't get tagged images");
			}		
			System.out.println("[UV]3 got tagged images");


			ip = new ShortProcessor(width, height);
			ip2 = new ShortProcessor(width, height);
		
			ip.setPixels(tagged1.pix);
			ip2.setPixels(tagged2.pix);
		
			System.out.println("[UV]4 conversion into short processors");

			imp = new ImagePlus("", ip);
			imp2 = new ImagePlus("", ip2);
			
			System.out.println("[UV]5 conversion  to imageplus");
			
			// Subtraction
			imp3 = calcul.run("Substract create", imp, imp2);
			
			System.out.println("[UV]6 subtraction");
			
			// Gaussian filter
			gau.blurGaussian(imp3.getProcessor(), 3, 3, 0.02);
			//ImagePlus imp4 = imp3.duplicate();
	   	      				
			System.out.println("[UV]7 gaussian blur");
			System.out.println("[UV]8 threshold is: "+pane_.getThreshold());

			try{
				tempcutoff = imp3.getStatistics().mean+pane_.getThreshold()*imp3.getStatistics().stdDev;
				System.out.println("[UV]8-a got threshold: "+tempcutoff);

			} catch(Exception e){
				tempcutoff = cutoff_;
				System.out.println("[UV]8-b got threshold: "+tempcutoff);

			}
			System.out.println("[UV]9 got threshold: "+tempcutoff);

	        //addCutOff(tempcutoff);
			
			if( pane_.isAutoCutoffOn() || pane_.isCutoffNeeded()){
				//cutoff_ = meanArrayListWOzeros(cutoffArray);
				System.out.println("[UV]10 autocutoff, keep temp cutoff");

				cutoff_ = tempcutoff;
			} else {
				System.out.println("[UV]10 autocutoff: "+pane_.isAutoCutoffOn());
				System.out.println("[UV]10 cutoff needed: "+ pane_.isCutoffNeeded());
				
				cutoff_ = pane_.getCutoff();
				System.out.println("[UV]11 no auto, get user cutoff");
				System.out.println("[UV]11-a user cutoff: "+cutoff_);
				
				
				if(cutoff_ == 0){
					System.out.println("[UV]12 cutoff is 0, use temp cutoff");

					//cutoff_ = meanArrayListWOzeros(cutoffArray);
					cutoff_ = tempcutoff;
				}
			}

			ip_ = NMSuppr.run(imp3,7,cutoff_);
			System.out.println("[UV]13 ran nms with cutoff: "+cutoff_);

			//addN(NMSuppr.getN());
			
					
			//return (int) Math.floor(meanArrayListWOzeros(nArray)+0.5);
			System.out.println("[UV]14 return N");

			return NMSuppr.getN();
		}

		//System.out.println("Got N");
		return 0;
	}

	public ImageProcessor getNMSresult(){
		return ip_;
	}
/*
	public void addCutOff(double newcutoff){
		int s = cutoffArray.size();
		if(s<pane_.getdT()){
			cutoffArray.add(newcutoff);
		} else {
			cutoffArray.remove(0);
			cutoffArray.add(newcutoff);
		}
	}

	public void addN(double newn){
		int s = nArray.size();
		if(s<pane_.getdT()){
			nArray.add(newn);
		} else {
			nArray.remove(0);
			nArray.add(newn);
		}
	}

	public void addPulse(double newpulse){
		int s = pulseArray.size();
		if(s<pane_.getdT()){
			pulseArray.add(newpulse);
		} else {
			pulseArray.remove(0);
			pulseArray.add(newpulse);
		}
	}

	public double meanArrayListWOzeros(ArrayList<Double> a){
		int s = a.size();
		double n = 0;
		for(int i=0;i<s;i++){
			if(a.get(i)!=0){
				n = n+a.get(i);
			} else {
				s = s-1;
			}
		}
		n=n/s;
		return n;
	}

	public double meanArrayList(ArrayList<Double> a){
		int s = a.size();
		double n = 0;
		for(int i=0;i<s;i++){
			n = n+a.get(i);
		}
		n=n/s;
		return n;
	}
	*/
	public double getNewPulse(){			// code should be cleaned, variables are not used properly
		double N = (double) N_;
		double N0 = pane_.getN();
		double temppulse=0;
		
		System.out.println("[UV]17 Got N user: "+N0);


		System.out.println("[UV]18 N requested: "+N0);
		System.out.println("[UV]19 Current N: "+N);

		pulse_ = sys_.getUVPulse(); 
		System.out.println("[UV]20 got current pulse: "+pulse_);

		if(pane_.isUVselected()){
			// If the pulse is 0, need a non-null starting point
			//if(pulse_ == 0){
				//pulse_ = min;
			//}
			System.out.println("[UV]21 UV is selected");

			if(prevpulse_ < min){
				System.out.println("[UV]22 prevpulse was 0, pulse = 0.4");

				pulse_ = min;			
			} else {
				System.out.println("[UV]23 pulse = prevpulse");

				pulse_ = prevpulse_;	// avoid getting stuck between 0 and 1 (otherwise newp=0.4+0.4*1.99*coeff < 1 unless coeff ~> 0.7 which is not good for higher values), but now it is not bounded t0 <1, keep or change????
			}
			
			
			// use the last value except if it is too far from the current pulse (e.g. user change)
			//if(Math.abs(prevpulse_-pulse_)<1){
			//	pulse_ = prevpulse_;
			//}
	
			System.out.println("[UV]24 UV feedback is: "+pane_.getFeedback());
			
			// calculate new pulse
			if(N0 != 0){
				temppulse = pulse_*(1+pane_.getFeedback()*(1-N/N0));
			} else {
				return 0;
			}
	
			System.out.println("[UV]25 New pulse: "+temppulse);
			
			if(temppulse < min){
				System.out.println("[UV]26 new pulse is too small");

				temppulse = min;
			}
	
			// if new pulse is higher than camera exposure
			double exp = 1000*sys_.getExposureTime();
			if(temppulse > exp) {
				System.out.println("[UV]27 temp pulse is longer than exposure");

				temppulse = (int) exp; 
			}
	
			/*if(Math.abs(temppulse-pulse_)<0.5){
				prevpulse_ = temppulse;
			} else if(temppulse>pulse_){
				prevpulse_ = pulse_+1;
			} else {
				prevpulse_ = pulse_-1;
			}*/
			
			//addPulse(temppulse);
			prevpulse_ = temppulse;
			System.out.println("[UV]28 keep temppulse");

		} else {

			prevpulse_ = pulse_;													/// it would maybe be better to use the isuvselected to not update at all the UV...
		}
		return prevpulse_;
	}
	
	@Override
	public void update() {
		if(sys_.isCameraAcquiring() && pane_.isUVselected()){
			System.out.println("[UV]29 change pulse");
			((Laser) device_).setPulseLength((int) this.getOutput(1));
		}
		System.out.println("[UV]30 cam acq: "+sys_.isCameraAcquiring());
		System.out.println("[UV]31 uv selected: "+pane_.isUVselected());

	}
}
