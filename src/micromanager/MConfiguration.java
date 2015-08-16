    package micromanager;

public class MConfiguration {


	//////////////////////////////////////////////
	//// Log 
	
	public static final String logpath = "Log_GUI.txt";
	
	//////////////////////////////////////////////
	//// Devices 
	
	// Lasers
	public static final String[] laserlabel = {"Luxx405","Luxx488","Luxx638"};  
	public static final String[] ardproplabel = {"L1 trigger","L1 period"}; 
	public static final String[] luxxproplabel = {"Laser Operation Select","Laser Power Set-point Select [%]"};
	
	public static final String[] ardlaserlabel = {"A405","A488","A638","A561"};  
	public static final int ardlasermaxpulse = 65535;
	
	public static final String sslaserlabel = "Cobolt561";
	public static final String ard2laserlabel = "Arduino-Output"; 
	public static final String ard2proplabel = "561 power[%]"; 
	public static final String[] coboltproplabel = {"Laser","PowerSetpoint"}; 
	
	public static final String[] laserkeys = {"405","488","638","561"}; 
	
	// QPD
	public static final String qpdlabel = "Arduino-Input";
	public static final String[] qpdproplabel = {"AnalogInput10","AnalogInput9","AnalogInput8"}; 
	
	// PI
	public static final String pilabel = "PIZStage";
	public static final String[] piproplabel = {"External sensor","Position"};
	public static final String smaractlabel = "SmaractZ";
	
	//Servos
	public static final String[] servolabel = {"MaestroServo","Arduino-Servo","Arduino-Servo"};
	public static final String[] proplabel = {"Position","BFP state","3DA state"};
	public static final int[] numposservo = {5,4,2};
	public static final String[] servokeys = {"FW","BFP","3DA"};
	
}
