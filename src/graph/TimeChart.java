package graph;

import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TimeChart {

	String name_, nameX_, nameY_;
	int width_, height_, maxN_, minY_=0, maxY_=100;
    private XYSeries series;
    int time_counter = 0;
    ChartPanel cp;
    boolean ranged_ = false;

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
	    series = new XYSeries(name_);
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(null, nameX_,
            nameY_, dataset, PlotOrientation.VERTICAL, false, false, false);
        cp = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width_, height_);
            }
        };
        
        if(ranged_){
        	XYPlot plot = (XYPlot) chart.getPlot();
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
		int n = series.getItemCount();
		time_counter ++;
		if(n>=maxN_){
			series.remove(0);
			series.add(time_counter, point);
		} else {
			series.add(time_counter, point);
		}
	}
	
	public double getLastPoint(){
		if(series.getItemCount()>0){
			return (Double) series.getY(series.getItemCount()-1);
		}
		return 0;
	}
	
}
