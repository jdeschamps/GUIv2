package swing;

import ij.ImagePlus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Graph extends JComponent{
	ImagePlus test;	
	int height_, width_;
	int xpos, ypos, ymin, ymax, xnew, ynew;
	int woffset,hoffset;
	public String xname, yname;
	ArrayList<Integer> X,Y;	
	Boolean evolution_ = false;
	BufferedImage img = null;
	boolean decimal_, display_, rightdisp_;
	int dec;
	int counter = 0;
	
	public Graph(int width, int height, String s1, String s2,boolean decimal, boolean display, boolean rightdisplay) {
		height_ = height;
		width_ = width;
		woffset = 30;
		hoffset = 20;
		yname = s1;
		xname = s2;
		decimal_=decimal;
		display_ = display;
		rightdisp_ = rightdisplay;
		
		if(xname == "time"){
			evolution_ = true;
		}
		
		if(evolution_){
			X = new ArrayList<Integer>();
			Y = new ArrayList<Integer>();
		} else {
			xpos = 300;
			ypos = 300;
			ymin = 0;
			ymax = 700;
		}
		

		if(decimal_){
			dec = 100;
		}
		
	}	
	public Graph(int width, int height, int offsetx, int offsety, String s1, String s2,boolean decimal, boolean display, boolean rightdisplay) {
		height_ = height;
		width_ = width;
		woffset = offsetx;
		hoffset = offsety;
		yname = s1;
		xname = s2;
		decimal_=decimal;
		display_ = display;
		rightdisp_ = rightdisplay;
		
		if(xname == "time"){
			evolution_ = true;
		}
		
		if(evolution_){
			X = new ArrayList<Integer>();
			Y = new ArrayList<Integer>();
		} else {
			xpos = 300;
			ypos = 300;
			ymin = 0;
			ymax = 700;
		}
		
		if(decimal_){
			dec = 100;
		}
		
	}
	
	public void addPoint(int x, int y){									//// not proper here....
		if(evolution_){
			counter++;
			if(X.size() <= width_){
				X.add(counter);
				Y.add(y);
			}else{
				X.remove(0);
				Y.remove(0);
				X.add(counter);
				Y.add(y);
			}
		}else if(!isOutofBoundary(x,y)){
			xpos = x;
			ypos = y;
		}
		
	}
	
	public void addPoint(int y){		
		counter++;
		if(X.size() <= width_){
			X.add(counter);
			Y.add(y);
		}else{
			X.remove(0);
			Y.remove(0);
			X.add(counter);
			Y.add(y);
		}
	}
	
	public void getBoundaries(){
		int var;
		ymin = 10000;
		ymax = 0;
		for(int i=0;i<Y.size();i++){
			var = Y.get(i);
			if(var<ymin){
				ymin=var;
			}else if(var>ymax){
				ymax=var;				
			}
		}
		if(ymin == ymax){
			ymin = ymin -1;
			ymax = ymax +1;
		}

	}
	
	public Boolean isOutofBoundary(int x, int y){
		if(!evolution_){
			if(x<ymin || x>ymax){
				return true;
			}else if(y<ymin || y>ymax){
				return true;
			}
		}
		return false;
	}

	public int getCoord(int y){
		double y_new =hoffset+15+((double) height_-30)*((double) (y-ymin))/((double)(ymax-ymin));

		ynew = (int) Math.round(y_new);
		int inverse = height_+2*hoffset-ynew;  
		return inverse;
	}
	
	public void getCoord(int x,int y){
	
		ynew = height_+2*hoffset-(int) Math.round(hoffset+15+((double) height_-30)*((double) (y-ymin))/((double)(ymax-ymin)));
		xnew = height_+2*woffset-(int) Math.round(woffset+15+((double) height_-30)*((double) (x-ymin))/((double)(ymax-ymin)));		
	}
	///////////////////////////////////////////
	public int getLastY(){
		if(Y.size()>0){
			int lasty = Y.get(Y.size()-1);
			return lasty;
		} else {
			return 0;
		}
	}
	
	public int getLastX(){
		if(X.size()>0){
			int lastx = X.get(X.size()-1);
			return lastx;
		} else {
			return 0;
		}
	}
	////////////////////////////////////////////
    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D)g;
        ga.setPaint(Color.black);
        ga.fillRect(woffset, hoffset, width_, height_);
        if(evolution_){
        	getBoundaries();
        	
        	if(decimal_){
        		String strymax=String.valueOf((double)ymax/dec);// make 0 to 0.0 
            	String strymin=String.valueOf((double)ymin/dec);// make 10000 to 1000.0
            	ga.drawString(strymax, 0, hoffset);  // 0 printing
            	ga.drawString(strymin, 0, hoffset+height_); // 1000 printing
        	}else{
        		ga.drawString(Integer.toString(ymax), 0, hoffset);  // 0 printing
        		ga.drawString(Integer.toString(ymin), 0, hoffset+height_); // 1000 printing
        	}
         
       
            ga.drawString(xname,woffset+width_/2, hoffset+height_+10);
            if(display_){
            	ga.setFont(new Font("Arial", Font.BOLD, 13));
            	ga.drawString(String.valueOf((double) getLastY()/dec), 0,hoffset+height_/2);
            	ga.setFont(new Font("Arial", Font.PLAIN, 12));

            } else {
            	ga.drawString(yname, 0,hoffset+height_/2);
            }
            
            if(rightdisp_){
            	ga.setFont(new Font("Arial", Font.BOLD, 13));
            	ga.drawString(String.valueOf(getLastY()), 2*woffset+width_,hoffset+height_/2);
            	ga.setFont(new Font("Arial", Font.PLAIN, 12));
            }
            
            
            ga.setPaint(Color.white);
	        for(int j=0;j<X.size()-1;j++){
	        	ga.drawLine((X.get(j)-X.get(0))+woffset,getCoord(Y.get(j)),(X.get(j+1)-X.get(0))+woffset,getCoord(Y.get(j+1)));
	        }
	    }else{ 
            ga.drawString(Integer.toString(ymax), 0, hoffset);
            ga.drawString(Integer.toString(ymin), 0, hoffset+height_);
            ga.drawString(Integer.toString(ymin), hoffset,hoffset+height_+10); 
            ga.drawString(Integer.toString(ymax), woffset+width_,hoffset+height_+10);
            ga.drawString(xname,woffset+width_/2, hoffset+height_+10);
            ga.drawString(yname, 0,hoffset+height_/2);
	    	ga.setPaint(Color.yellow);
            for(int i=1;i<=width_/40;i++){
            	ga.drawLine(i*50,hoffset,i*50,hoffset+height_); 
            }
            for(int i=1;i<=height_/50;i++){
            	ga.drawLine(woffset,i*50,woffset+width_,i*50);
            }
            ga.setPaint(Color.white);
            getCoord(xpos,ypos);
            g.drawRect (xnew+woffset-5,ynew+hoffset-5, 10, 10);
	    }
	}
}