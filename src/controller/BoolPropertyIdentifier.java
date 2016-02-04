package controller;

public class BoolPropertyIdentifier extends PropertyIdentifier {
		
	private boolean value_ = false;
	
	public BoolPropertyIdentifier(String device, String propertyname) {
		super(device, propertyname);
	}

	public boolean getValue(){
		return value_;
	}
	
	public void setValue(boolean val){
		value_ = val;
	}
	
	public void setOppositeValue(){
		value_ = !value_;
	}
}