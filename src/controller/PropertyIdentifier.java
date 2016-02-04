package controller;

public class PropertyIdentifier {
	
	private String device_;
	private String propertyname_;
	
	public PropertyIdentifier(String device, String propertyname){
		device_ = device;
		propertyname_ = propertyname;
	}
	
	public String getDeviceName(){
		return device_;
	}

	public String getPropertyName(){
		return propertyname_;
	}
	
}
