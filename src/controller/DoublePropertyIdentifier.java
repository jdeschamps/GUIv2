package controller;

public class DoublePropertyIdentifier extends PropertyIdentifier {
	
	private double value_=0;
	
	public DoublePropertyIdentifier(String device, String propertyname) {
		super(device, propertyname);
	}

	public double getValue(){
		return value_;
	}
	
	public void setValue(double val){
		value_ = val;
	}
}