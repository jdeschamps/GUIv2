package utils;

import java.util.ArrayList;

import javax.swing.JTextPane;

public class StringText {
	private ArrayList<String> array;
	private final String end = "\n";
	private final String state1 = "Waiting...";
	private final String state2 = "Acquiring...";
	private final String state3 = "Done...";
	private final String state4 = "Stopped...";
	
	private JTextPane display;
	
	public StringText(JTextPane jTextPane_progress){
		array = new ArrayList<String>();
		display = jTextPane_progress;
		setState(1);
	}
	
	public void add(String s){
		array.add(s+end);
		refresh();
	}
	
	public void clear(){
		array.clear();
	}
	
	public void setState(int i){
		switch(i){
		case 1:
			clear();
			add(state1);
			break;
		case 2:
			clear();
			add(state2);
			break;
		case 3:
			array.set(0, state3+end);
			refresh();
			break;
		case 4:
			array.set(0, state4+end);
			refresh();
			break;
		}
	}
	
	public void setNumberPosition(int i){
		add("Number of positions: "+i);
	}
	
	public void addFinishedExperiments(int posNumber){
		add("Experiment "+posNumber+" finished");
	}
	
	public void setAcquiring(int numPos){
		setState(2);
		setNumberPosition(numPos);
	}
	
	public void setDone(){
		setState(3);
	}
	
	public void setStopped(){
		setState(4);
	}
	
	public String toString(){
		String s = "";
		for(int i=0;i<array.size();i++){
			s += array.get(i);
		}
		return s;
	}
	
	public void refresh(){
		display.setText(this.toString());
	}
}
