package activation;


import java.util.ArrayList;

import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.gui.Roi;
import ij.process.ImageProcessor;

public class NMS {
	ImageProcessor imp, impresult;
	ImagePlus im_, imtemp_;
	ImageWindow iw;
	Roi roi;
	int width_, height_;
	int n_,max_num;
	double cutoff_;
	boolean display_;
	
	
	ArrayList<Peak> peaks;
	
	public NMS(){
		peaks = new ArrayList<Peak>();
		imtemp_ = new ImagePlus();
		imtemp_.setTitle("NMS");
		roi = new Roi(0,0,10,10);
	}
	
	public ImageProcessor run(ImagePlus im, int n, int max, double cutoff, boolean display){
		im_ = im;		
		width_ = im.getWidth();
		height_ = im.getHeight();
		imp = im.getProcessor();
		impresult = (ImageProcessor) imp.clone();
		impresult.setValue(255);
		n_ = n;
		max_num = max;
		cutoff_ = cutoff;
		display_ = display;
		peaks.clear();
		
		return process();
	}
	
	public int getN(){
		int N=0;
		
		if(!(peaks == null)){
			N = peaks.size();
		}
		
		return N;
	}
	
	public ImageProcessor process(){
		int i,j,ii,jj,ll,kk;
		int mi,mj;
		boolean failed=false;
	
		for(i=0;i<=width_-1-n_;i+=n_+1){	// Loop over (n+1)x(n+1)
			for(j=0;j<=height_-1-n_;j+=n_+1){
				mi = i;
				mj = j;
				for(ii=i;ii<=i+n_;ii++){	
					for(jj=j;jj<=j+n_;jj++){
						if(imp.get(ii,jj) > imp.get(mi,mj)){	
							mi = ii;
							mj = jj;
						}
					}
				}
				failed = false;
				
				Outer:
				for(ll=mi-n_;ll<=mi+n_;ll++){	
					for(kk=mj-n_;kk<=mj+n_;kk++){
						if((ll<i || ll>i+n_) || (kk<j || kk>j+n_)){
							if(ll<width_ && ll>0 && kk<height_ && kk>0){		
								if(imp.get(ll,kk)>imp.get(mi,mj) ){
									failed = true;
									break Outer;
								}
							}
						}
					}
				}
				if(!failed && peaks.size()<max_num){
					if(imp.get(mi,mj) > cutoff_){
						peaks.add(new Peak(mi, mj, imp.get(mi,mj)));
						roi.setLocation(mi, mj);
						impresult.draw(roi);
					}
				}
			}			
		}	
		return impresult;
	}
}
