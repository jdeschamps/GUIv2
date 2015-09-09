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

public class Chart {

	String name_, nameX_, nameY_;
	int width_, height_, maxN_;
    private XYSeries series;
    ChartPanel cp;
	
	public Chart(String name, String nameX, String nameY, int maxN, int width, int height){
		name_ = name;
		nameX_ = nameX;
		nameY_ = nameY;
		width_ = width;
		height_ = height;
		maxN_ = maxN;
		
		initialize();
	}
	
	public void initialize(){		
	    series = new XYSeries(name_);
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createScatterPlot(null, nameX_,
            nameY_, dataset, PlotOrientation.VERTICAL, false, false, false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(0, 700);
        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setRange(0, 700);
        
        cp = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width_, height_);
            }
        };
	}
	
	public ChartPanel getChart(){
		return cp;
	}
	
	public void clearChart(){
		series.clear();
	}
	
	public void addPoint(double x, double y){
		int n = series.getItemCount();
		if(n>=maxN_){
			series.remove(0);
			series.add(x, y);
		} else {
			series.add(x, y);
		}
	}
	
}
