package model;

import java.util.ArrayList;

import mmcorej.CMMCore;

import org.micromanager.api.ScriptInterface;

import configuration.MConfiguration;

public class MSystem {

	private ScriptInterface mm_;
	private CMMCore core_;
	private DeviceList devices_;
	private MConfiguration conf_;
	private ArrayList<Task> taskslist_;
	
	public MSystem(ScriptInterface mm, MConfiguration conf){
		mm_ = mm;
		core_ = mm_.getMMCore();
		conf_ = conf;
		
		buildDeviceList();
		registerTasks();
	}

	private void registerTasks(){			/// observable things
		taskslist_ = new ArrayList<Task>();
	}

	public ArrayList<Task> getTasksList(){
		return taskslist_;
	}
	
	private void buildDeviceList(){
		devices_ = new DeviceList(core_, conf_);
	}
	
	
	
}
