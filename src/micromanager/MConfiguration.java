package micromanager;

import java.awt.Color;
import java.io.File;

public class MConfiguration {
	
	public static final String configpath = "GUIConfiguration.txt";

	//////////////////////////////////////////////
	//// Log 
	public static final String logpath = "Log_GUI.txt";
	public static final boolean logenabled = true;
	
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
	public static final String[] laserbehaviourlabel = {"Off","On","Camera","Rising","Falling"};
	
	//////////////////////////////////////////////
	//// Threads
	public static final int maxNPI = 30;
	public static final int[] maxNQPD = {30,30,1};
	public static final int maxNUV = 1000;
	
	//////////////////////////////////////////////
	//// 
	public double UVcoeff=0.5, SDcoeff=0.5;
	public int laser1BehaviourDefault = 3;
	public int laser2BehaviourDefault = 2;
	public int laser3BehaviourDefault = 2;
	public int laser4BehaviourDefault = 2;

	public double getUVcoeff(){
		return UVcoeff;
	}
	public double getSDcoeff(){
		return SDcoeff;
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
		writer.process("Configuration\r\n");
		writer.process("UVcoeff="+UVcoeff+";\r\n");
		writer.process("SDcoeff="+SDcoeff+";\r\n");
		writer.process("Laser1Behaviour="+3+";\r\n");
		writer.process("Laser2Behaviour="+2+";\r\n");
		writer.process("Laser3Behaviour="+2+";\r\n");
		writer.process("Laser4Behaviour="+2+";\r\n");
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
		String configuration="Configuration", end="end;", uvcoeff="UVcoeff", sdcoeff="SDcoeff";
		String laser1b="Laser1Behaviour", laser2b="Laser2Behaviour", laser3b="Laser3Behaviour", laser4b="Laser4Behaviour";

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











