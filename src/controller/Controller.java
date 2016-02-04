package controller;

import model.MSystem;

import org.micromanager.api.ScriptInterface;

import configuration.MConfiguration;
import view.UIManager;

public class Controller {
	
	private ScriptInterface mm_;
	private MConfiguration conf_;
	private MSystem sys_;
	
	private UIManager gui_;
	private TaskController taskcontrol_;
	private SystemController syscontrol_;
	
	public Controller(ScriptInterface mm){
		mm_ = mm;
	}

	public void initialize(){
		readConfiguration();
		createModel();
		createUserInterface();
		createControllers();
	}
	
	public void readConfiguration(){
		conf_ = new MConfiguration();
	}
	
	public MConfiguration getConfiguration(){
		return conf_;
	}
	
	public void createModel(){
		sys_ = new MSystem(mm_, conf_);
	}
	
	public void createUserInterface(){
		gui_ = new UIManager(this);
	}
	
	public void createControllers(){
		syscontrol_ = new SystemController(sys_, this);
		taskcontrol_ = new TaskController(sys_, this);
	}
	
	public void reportChange(PropertyIdentifier property){
		if(property instanceof IntPropertyIdentifier){																															//////////////////////// to comment
			System.out.println(property.getDeviceName()+" "+property.getPropertyName()+" "+((IntPropertyIdentifier) property).getValue());
		} else if(property instanceof BoolPropertyIdentifier){
			System.out.println(property.getDeviceName()+" "+property.getPropertyName()+" "+((BoolPropertyIdentifier) property).getValue());
		} else if(property instanceof DoublePropertyIdentifier){
			System.out.println(property.getDeviceName()+" "+property.getPropertyName()+" "+((DoublePropertyIdentifier) property).getValue());
		} else{
			System.out.println(property.getDeviceName()+" "+property.getPropertyName());
		}
		
	}
}