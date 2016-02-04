package model;

import java.util.HashMap;

import configuration.MConfiguration;
import device.Device;
import device.Laser;
import mmcorej.CMMCore;

public class DeviceList {

	private CMMCore core_;
	private MConfiguration conf_;
	private HashMap<String,Device> devicelist_;
	
	public DeviceList(CMMCore core, MConfiguration conf){
		core_ = core;
		conf_ = conf;
		
		buildDeviceList();
	}

	public void buildDeviceList(){
		
		// Lasers
		/*int N = conf_.getNumLasers();
		for(int i=1;i<=N;i++){
			Laser l = new Laser("",conf_.laser+i,core_,null,true);												////// noooooooooooooooooooo
			devicelist_.put(conf_.laser+i, l);				//// find good way for laser name
		}*/
		
		// Stage - PI
		
		// Servos - FW - BFP - 3DA
		
	}
}
