package micromanager;

import java.awt.Color;

public class MConfiguration {


	//////////////////////////////////////////////
	//// Log 
	
	public static final String logpath = "Log_GUI.txt";
	
	//////////////////////////////////////////////
	//// Devices 
	
	// Lasers
	public static final String[] laserlabel = {"Luxx405","Luxx488","Luxx638"};  
	public static final String[] ardproplabel = {"Behavior","Pulse length (us)"}; 
	public static final String[] luxxproplabel = {"Laser Operation Select","Laser Power Set-point Select [%]"};
	
	public static final String[] ardlaserlabel = {"A405","A488","A638","A561"};  
	public static final int ardlasermaxpulse = 65535;
	
	public static final String sslaserlabel = "Cobolt561";
	public static final String ard2laserlabel = "Arduino-Output"; 
	public static final String ard2proplabel = "561 power[%]"; 
	public static final String[] coboltproplabel = {"Laser","PowerSetpoint"}; 
	
	public static final String[] laserkeys = {"405","488","638","561"}; 
	
	public static final double loweringfactor = 0.9;
	
	// QPD
	public static final String qpdlabel = "Arduino-Input";
	public static final String[] qpdproplabel = {"AnalogInput10","AnalogInput9","AnalogInput8"}; 
	
	// PI
	public static final String pilabel = "PIZStage";
	public static final String[] piproplabel = {"External sensor","Position"};
	public static final String smaractlabel = "SmaractZ";
	
	//Servos
	public static final String[] servolabel = {"MaestroServo","Arduino-Servo","Arduino-Servo"};
	public static final String[] proplabel = {"Position","BFP State","3DA state"};
	public static final int[] numposservo = {5,4,2};
	public static final String[] servokeys = {"FW","BFP","3DA"};
	public static final int bfpPosition = 3;															/// here matters
	public static final int[] maestropos = {1950,1740,1500,1250,1040};

	//////////////////////////////////////////////
	//// GUI
	
	// Colors
	public static final Color red = new Color(255, 0, 51);
	public static final Color green = new Color(0,204,51);
	public static final Color uv = new Color(102,102,255);
	public static final Color blue = new Color(0,102,204);
	public static final Color neutral = new Color(204,204,204);
	public static final Color black = new Color(0,0,0);
	
	// Filters panel
	public static final String[] filters = {"525/50","600/60","700/100","Sp","Empty","Empty"};
	
	// Laser parameters tab
	public static final String[] laserbehaviourlabel = {"Camera","Rising","Falling","On","Off"};
	
	
}
