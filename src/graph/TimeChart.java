package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TimeChart {

	String name_, nameX_, nameY_;
	int width_, height_, maxN_, minY_=0, maxY_=100;
    private XYSeries series, buffer;
    ChartPanel cp;
    int	time_counter = 0;
    boolean ranged_ = false;
    int[] t;
    
	public TimeChart(String name, String nameX, String nameY, int maxN, int minY, int maxY, int width, int height){
		name_ = name;
		nameX_ = nameX;
		nameY_ = nameY;
		width_ = width;
		height_ = height;
		maxN_ = maxN;
		minY_ = minY;
		maxY_ = maxY;
		
		ranged_ = true;
		
		initialize();
	}

	public TimeChart(String name, String nameX, String nameY, int maxN, int width, int height){
		name_ = name;
		nameX_ = nameX;
		nameY_ = nameY;
		width_ = width;
		height_ = height;
		maxN_ = maxN;

		ranged_ = false;
		
		initialize();
	}
	
	public void initialize(){	
		t = new int[maxN_];
		for(int i=0;i<maxN_;i++){
			t[i] = i;
		}
		
	    series = new XYSeries(name_);
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(null, null,
            null, dataset, PlotOrientation.VERTICAL, false, false, false);
        cp = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width_, height_);
            }
        };

        chart.setBackgroundPaint(new Color(240,240,240));
        
    	XYPlot plot = (XYPlot) chart.getPlot();
    	plot.setBackgroundPaint(new Color(230,230,230));
    	plot.setDomainGridlinePaint(new Color(100,100,100));
    	plot.setRangeGridlinePaint(new Color(100,100,100));

    	XYItemRenderer renderer = plot.getRenderer();  
    	renderer.setSeriesPaint(0, new Color(255,91,91));    	
    	renderer.setSeriesStroke(0, new BasicStroke(2));
    	
    	ValueAxis xAxis = plot.getDomainAxis();
    	xAxis.setRange(0,maxN_);
    	
    	NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    	// Text string of the decimal format
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	rangeAxis.setNumberFormatOverride(formatter);
    	
        if(ranged_){
        	ValueAxis yAxis = plot.getRangeAxis();
        	yAxis.setRange(minY_,maxY_);
        }
	}
	
	public ChartPanel getChart(){
		return cp;
	}
	
	public void clearChart(){
		time_counter = 0;
		series.clear();
	}
	
	public void addPoint(double point){
		point = Math.floor(point * 100) / 100;
		int n = series.getItemCount();
		time_counter ++;
		if(n>=maxN_){
			series.remove(0);
			try {
				buffer = (XYSeries) series.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			series.clear();
			for(int i=0;i<maxN_-1;i++){
				series.add(t[i],buffer.getY(i));
			}
			series.add(t[maxN_-1], point);
		} else {
			series.add(t[time_counter%maxN_], point);
		}
	}
	
	public double getLastPoint(){
		if(series.getItemCount()>0){
			return (Double) series.getY(series.getItemCount()-1);
		}
		return 0;
	}
	
}
