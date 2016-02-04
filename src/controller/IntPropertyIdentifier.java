package controller;

public class IntPropertyIdentifier extends PropertyIdentifier {

	private int value_ = 0;
	
	public IntPropertyIdentifier(String device, String propertyname) {
		super(device, propertyname);
	}

	public int getValue(){
		return value_;
	}
	
	public void setValue(int val){
		value_ = val;
	}
}
