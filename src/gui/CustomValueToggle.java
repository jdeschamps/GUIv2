package gui;

import javax.swing.JToggleButton;

public class CustomValueToggle extends JToggleButton {
	private int value_;
	
	public CustomValueToggle(int val){
		setValue(val);
	}
	
	public void setValue(int val){
		value_ = val;
		this.setText(String.valueOf(val)+"%");
	}

	public int getValue(){
		return value_;
	}
}
