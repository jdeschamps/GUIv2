package micromanager;

import java.awt.Color;
import java.io.File;

import utils.txtReader;
import utils.txtWriter;

public class MConfiguration {
	
	public static final String configpath = "GUIConfiguration.txt";

	//////////////////////////////////////////////
	//// Log 
	public static final String logpath = "Log_GUI.txt";
	public static final String logUVpath = "Log_UV.txt";
	public static final boolean logenabled = true;
	
	//////////////////////////////////////////////
	//// Devices 
	
	// Lasers
	public static final String lasercontroller = "iChrome-MLE-TCP";  
	public static final String[] laserproperties3 = {"Laser 1: 1. Enable","Laser 1: 3. Level %","Laser 1: 2. Emission"}; 
	public static final String[] laserproperties4 = {"Laser 2: 1. Enable","Laser 2: 3. Level %","Laser 2: 2. Emission"}; 
	public static final String[] laserproperties2 = {"Laser 3: 1. Enable","Laser 3: 3. Level %","Laser 3: 2. Emission"}; 
	public static final String[] laserproperties1 = {"Laser 4: 1. Enable","Laser 4: 3. Level %","Laser 4: 2. Emission"}; 
	
	//public static final String[] ardproplabel = {"Behavior","Pulse length (us)"}; 
	//public static final String[] ardlaserlabel = {"A405","A488","A638","A561"};  
	//public static final int ardlasermaxpulse = 65535;
	public static final String[] mojolaser1prop = {"Mode0","Duration0","Sequence0"};
	public static final String[] mojolaser2prop = {"Mode1","Duration1","Sequence1"};
	public static final String[] mojolaser3prop = {"Mode2","Duration2","Sequence2"};
	public static final String[] mojolaser4prop = {"Mode3","Duration3","Sequence3"};
	public static final String mojolabel = "Mojo-LaserTrig";
	public static final int mojomaxpulse = 65535;

	public static final String[] laserkeys = {"405","488","638","561"}; 
	
	public static final double loweringfactor = 1;
	
	// QPD
	public static final String qpdlabel = "Mojo-Input";
	public static final String[] qpdproplabel = {"AnalogInput0","AnalogInput1","AnalogInput2"}; 
	
	// PI
	public static final String pilabel = "PIZStage";
	public static final String[] piproplabel = {"External sensor","Position"};
	public static final String smaractlabel = "SmaractZ";
	
	//Servos
	public static final String[] servolabel = {"Mojo-Servos","Mojo-Servos","Mojo-TTL"};
	public static final String[] proplabel = {"Position0","Position1","State0"};
	public static final int[] numposservo = {65535,65535,2};															//// here careful with the FW (num positions)
	public static final String[] servokeys = {"BFP","FW","Flip"};
	public static final int maxservos = 65535;															/// here matters, former 3
	public static final int[] servo2 = {52000,42000,33000,23000};
	public static final int[] servo1 = {60000,16000};

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
	public static final String[] filters = {"525/50","600/60","676/37","Empty","N/A","N/A"};
	
	// Laser parameters tab
	public static final String[] laserbehaviourlabel = {"Off","On","Rising","Falling","Camera"};
	
	// UV pulse panel
	public static int maxpulsedefault = 5000;
	
	//////////////////////////////////////////////
	//// Graphs
	public static final int maxNPI = 400;
	public static final int[] maxNQPD = {30,30,1};
	public static final int maxNUV = 800;
	
	//////////////////////////////////////////////
	//// Steps focus
	public static double defaultSmallSteps = 0.5;
	public static double defaultLargeSteps = 2;
	

	//////////////////////////////////////////////
	//// Misc parameters
	public double UVcoeff=0.8, SDcoeff=1.5;
	public static int timeDistanceBckgd = 16;
	public static int gaussianMaskSize = 3;
	public static double gaussianMaskPrecision = 0.02;
	public static int nmsMaskSize = 7;
	
	
	public int dT=10;
	public int laser1BehaviourDefault = 2;	// UV0
	public int laser2BehaviourDefault = 4;	// blue
	public int laser3BehaviourDefault = 4;	// green
	public int laser4BehaviourDefault = 4;	// red
	
	//////////////////////////////////////////////
	String configuration="Configuration", end="end;", uvcoeff="UVcoeff", sdcoeff="SDcoeff", dt="dT";
	String laser1b="Laser1Behaviour", laser2b="Laser2Behaviour", laser3b="Laser3Behaviour", laser4b="Laser4Behaviour";

	public double getDefaultUVcoeff(){
		return UVcoeff;
	}
	public double getDefaultSDcoeff(){
		return SDcoeff;
	}
	
	public int getDefaultdT(){
		return dT;
	}

	public int getLaser1DefaultBehaviour(){
		return laser1BehaviourDefault;
	}

	public int getLaser2DefaultBehaviour(){
		return laser2BehaviourDefault;
	}

	public int getLaser3DefaultBehaviour(){
		return laser3BehaviourDefault;
	}

	public int getLaser4DefaultBehaviour(){
		return laser4BehaviourDefault;
	}
	
	public static String[] getLaserMojoProp(String s){
		if(s.equals(laserkeys[0])){ // 405 prop 1
			return mojolaser2prop; 			
		}else if(s.equals(laserkeys[1])){ // 488 prop 0
			return mojolaser1prop; 
		}else if(s.equals(laserkeys[3])){ // 638 prop 2
			return mojolaser3prop; 
		}else if(s.equals(laserkeys[2])){ // 561 prop 3
			return mojolaser4prop; 
		}else{
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////// Configuration file
	MConfiguration(){
		importConfiguration();
	}
	
	public void importConfiguration(){
		if(!doesFileExist()){
			createConfiguration();
		} else {
			readConfiguration();
		}
	}
	
	public void createConfiguration(){
		System.out.println("Create configuration");
		File f = new File(configpath);
		txtWriter writer = new txtWriter(f);
		writer.process(configuration+"\r\n");
		writer.process(uvcoeff+"="+UVcoeff+";\r\n");
		writer.process(sdcoeff+"="+SDcoeff+";\r\n");
		writer.process(dt+"="+dT+";\r\n");
		writer.process(laser1b+"="+laser1BehaviourDefault+";\r\n");
		writer.process(laser2b+"="+laser2BehaviourDefault+";\r\n");
		writer.process(laser3b+"="+laser3BehaviourDefault+";\r\n");
		writer.process(laser4b+"="+laser4BehaviourDefault+";\r\n");
		writer.process("end;");
		writer.close();
	}
	
	public boolean doesFileExist(){
		File f = new File(configpath);
		if(f.exists() && !f.isDirectory()) { 
			return true;
		}
		return false;
	}
	
	public void readConfiguration(){
		System.out.println("Read configuration:");
		File f = new File(configpath);
		txtReader reader = new txtReader(f);
		reader.process();
		reader.printContent();
		parseConfiguration(reader);
		reader.close();
	}
	
	public void parseConfiguration(txtReader reader){
		String content = String.copyValueOf(reader.getContent());
		String buffer="";
		String endpoint = ";";

		/*System.out.println("--------------------");
		for(int i=0;i<content.length();i++){
			System.out.println(i+"="+content.charAt(i));
		}
		System.out.println("--------------------");*/
		

		int i = 0;
		
		// Configuration
		buffer = content.substring(i, i+configuration.length());
		if(!buffer.equals(configuration)){
			System.out.println("Error reading configuration: "+buffer);
			return;
		} else {
			i = configuration.length()+2;
		}

		// UV coeff
		buffer = content.substring(i, i+uvcoeff.length());
		if(!buffer.equals(uvcoeff)){
			System.out.println("Error reading uvcoeff: "+buffer);
			return;
		} else {
			i = i+uvcoeff.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		UVcoeff = Double.parseDouble(buffer);
		i=i+3;
		
		// SD coeff
		buffer = content.substring(i, i+sdcoeff.length());
		if(!buffer.equals(sdcoeff)){
			System.out.println("Error reading sdcoeff: "+buffer);
			return;
		} else {
			i = i+sdcoeff.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		SDcoeff = Double.parseDouble(buffer);
		i=i+3;
		
		// dT
		buffer = content.substring(i, i+dt.length());
		if(!buffer.equals(dt)){
			System.out.println("Error reading dt: "+buffer);
			return;
		} else {
			i = i+dt.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		dT = Integer.parseInt(buffer);
		i=i+3;
		
		// Laser1 behaviour
		buffer = content.substring(i, i+laser1b.length());
		if(!buffer.equals(laser1b)){
			System.out.println("Error reading laser1 behaviour: "+buffer);
			return;
		} else {
			i = i+laser1b.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		laser1BehaviourDefault = Integer.parseInt(buffer);
		i=i+3;
		
		// Laser2 behaviour
		buffer = content.substring(i, i+laser2b.length());
		if(!buffer.equals(laser2b)){
			System.out.println("Error reading laser2 behaviour: "+buffer);
			return;
		} else {
			i = i+laser2b.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		laser2BehaviourDefault = Integer.parseInt(buffer);
		i=i+3;		
		
		// Laser3 behaviour
		buffer = content.substring(i, i+laser3b.length());
		if(!buffer.equals(laser3b)){
			System.out.println("Error reading laser3 behaviour: "+buffer);
			return;
		} else {
			i = i+laser3b.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		laser3BehaviourDefault = Integer.parseInt(buffer);
		i=i+3;
		
		// Laser4 behaviour
		buffer = content.substring(i, i+laser4b.length());
		if(!buffer.equals(laser4b)){
			System.out.println("Error reading laser4 behaviour: "+buffer);
			return;
		} else {
			i = i+laser4b.length()+1;
			buffer="";
		}
		
		while(content.charAt(i) != endpoint.charAt(0)){
			buffer = buffer+content.charAt(i);
			i++;
		}
		//System.out.println(buffer);
		laser4BehaviourDefault = Integer.parseInt(buffer);
		i=i+3;
		
		
		// End
		buffer = content.substring(i, i+end.length());
		if(!buffer.equals(end)){
			System.out.println("Error reading end of configuration: "+buffer);
		} else {
			System.out.println("End parsing configuration");
			return;
		}
	}
}











