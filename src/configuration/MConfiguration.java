package configuration;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import micromanager.utils;

public class MConfiguration {

	public static final Color red = new Color(255, 0, 51);
	public static final Color green = new Color(0,204,51);
	public static final Color uv = new Color(102,102,255);
	public static final Color blue = new Color(0,102,204);
	public static final Color neutral = new Color(204,204,204);
	public static final Color black = new Color(0,0,0);	
	
	public static final String laser = "Device-LaserKey";
	
	private static final String propertyfile = "GUIconfiguration.properties";
	
	private Properties props_;
	
	public MConfiguration(){
		if(!configurationExist()){
			props_ = getDefaultProperties();
			saveChanges();
		}
	}
	
	private boolean configurationExist(){
	    InputStream is = null;
	    
	    // First try loading from the current directory
	    try {
	        File f = new File(propertyfile);
	        is = new FileInputStream( f );
	    }
	    catch ( Exception e ) { 
	    	return false;
	    }
	 
	    try {
	        // Try loading properties from the file (if found)
	        props_.load( is );
	        return true;
	    } catch ( Exception e ) {
	    	return false;
	    }
	}
	
	public void saveChanges() {
	    try {
	        File f = new File(propertyfile);
	        OutputStream out = new FileOutputStream( f );
	        props_.store(out, "GUI properties");
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	    }
	}
	private Properties getDefaultProperties(){
		Properties props = new Properties();
		
		/////////////////////////////////////////////////////////
		//// System property
		props.setProperty(DefaultIdentifiers.id_settings_loggui, "Log_GUI.txt");
		props.setProperty(DefaultIdentifiers.id_settings_logactivation, "Log_UV.txt");
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_numberlasers, "4");
		props.setProperty(DefaultIdentifiers.id_filter+DefaultIdentifiers.id_filter_numfilters, "6");

		
		/////////////////////////////////////////////////////////
		//// Devices access
		
		//////////
		// Lasers
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_maxpulse, "65535");
		
		// 1
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_key, "405");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_laserdevice, "Luxx405");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_operation, "Laser Operation Select");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_powerdevice, "Luxx405");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_powerperc, "Laser Power Set-point Select [%]");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_triggerdevice, "Mojo-LaserSwitch");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_behavior, "Mode1");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_defaultbehavior, "2");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_pulselength, "Duration1");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_loweringfactor, "1");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_color, "uv");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_maxuserpulse, "5000");
		props.setProperty(DefaultIdentifiers.id_laser_label0+DefaultIdentifiers.id_laser_maxpower, "100");

		// 2
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_key, "488");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_laserdevice, "Luxx488");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_operation, "Laser Operation Select");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_powerdevice, "Luxx488");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_powerperc, "Laser Power Set-point Select [%]");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_triggerdevice, "Mojo-LaserSwitch");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_behavior, "Mode2");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_defaultbehavior, "4");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_pulselength, "Duration2");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_loweringfactor, "1");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_color, "blue");
		props.setProperty(DefaultIdentifiers.id_laser_label1+DefaultIdentifiers.id_laser_maxpower, "100");
		
		// 3
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_key, "561");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_laserdevice, "Cobolt561");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_operation, "Laser");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_powerdevice, "Arduino-Output");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_powerperc, "561 power[%]");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_triggerdevice, "Mojo-LaserSwitch");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_behavior, "Mode3");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_defaultbehavior, "4");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_pulselength, "Duration3");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_loweringfactor, "1");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_color, "green");
		props.setProperty(DefaultIdentifiers.id_laser_label2+DefaultIdentifiers.id_laser_maxpower, "300");
		
		// 4
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_key, "638");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_laserdevice, "Luxx638");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_operation, "Laser Operation Select");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_powerdevice, "Luxx638");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_powerperc, "Laser Power Set-point Select [%]");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_triggerdevice, "Mojo-LaserSwitch");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_behavior, "Mode4");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_defaultbehavior, "4");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_pulselength, "Duration4");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_loweringfactor, "0.9");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_color, "red");
		props.setProperty(DefaultIdentifiers.id_laser_label3+DefaultIdentifiers.id_laser_maxpower, "100");
		
		//////////
		// Servos
		props.setProperty(DefaultIdentifiers.id_filter+DefaultIdentifiers.id_filter_device, "MaestroServo");
		props.setProperty(DefaultIdentifiers.id_filter+DefaultIdentifiers.id_filter_positionproperty, "Position");
		props.setProperty(DefaultIdentifiers.id_filter+DefaultIdentifiers.id_filter_numfilters, "5");
		props.setProperty(DefaultIdentifiers.id_filter_label0+DefaultIdentifiers.id_filter_position, "1950");
		props.setProperty(DefaultIdentifiers.id_filter_label0+DefaultIdentifiers.id_filter_name, "525/50");
		props.setProperty(DefaultIdentifiers.id_filter_label0+DefaultIdentifiers.id_filter_color, "blue");
		props.setProperty(DefaultIdentifiers.id_filter_label1+DefaultIdentifiers.id_filter_position, "1740");
		props.setProperty(DefaultIdentifiers.id_filter_label1+DefaultIdentifiers.id_filter_name, "600/60");
		props.setProperty(DefaultIdentifiers.id_filter_label1+DefaultIdentifiers.id_filter_color, "green");
		props.setProperty(DefaultIdentifiers.id_filter_label2+DefaultIdentifiers.id_filter_position, "1500");
		props.setProperty(DefaultIdentifiers.id_filter_label2+DefaultIdentifiers.id_filter_name, "700/100");
		props.setProperty(DefaultIdentifiers.id_filter_label2+DefaultIdentifiers.id_filter_color, "red");
		props.setProperty(DefaultIdentifiers.id_filter_label3+DefaultIdentifiers.id_filter_position, "1250");
		props.setProperty(DefaultIdentifiers.id_filter_label3+DefaultIdentifiers.id_filter_name, "Empty");
		props.setProperty(DefaultIdentifiers.id_filter_label3+DefaultIdentifiers.id_filter_color, "neutral");
		props.setProperty(DefaultIdentifiers.id_filter_label4+DefaultIdentifiers.id_filter_position, "1040");
		props.setProperty(DefaultIdentifiers.id_filter_label4+DefaultIdentifiers.id_filter_name, "Empty");
		props.setProperty(DefaultIdentifiers.id_filter_label4+DefaultIdentifiers.id_filter_color, "neutral");
		props.setProperty(DefaultIdentifiers.id_filter_label5+DefaultIdentifiers.id_filter_position, "1040");
		props.setProperty(DefaultIdentifiers.id_filter_label5+DefaultIdentifiers.id_filter_name, "Empty");
		props.setProperty(DefaultIdentifiers.id_filter_label5+DefaultIdentifiers.id_filter_color, "neutral");
		
		props.setProperty(DefaultIdentifiers.id_bfp+DefaultIdentifiers.id_lens_device, "Arduino-Servo");
		props.setProperty(DefaultIdentifiers.id_bfp+DefaultIdentifiers.id_lens_positionproperty, "BFP State");
		props.setProperty(DefaultIdentifiers.id_bfp+DefaultIdentifiers.id_lens_name, "BFP");
		props.setProperty(DefaultIdentifiers.id_bfp+DefaultIdentifiers.id_lens_offposition, "0");
		props.setProperty(DefaultIdentifiers.id_bfp+DefaultIdentifiers.id_lens_onposition, "3");
		props.setProperty(DefaultIdentifiers.id_3da+DefaultIdentifiers.id_lens_device, "Arduino-Servo");
		props.setProperty(DefaultIdentifiers.id_3da+DefaultIdentifiers.id_lens_positionproperty, "3DA state");
		props.setProperty(DefaultIdentifiers.id_3da+DefaultIdentifiers.id_lens_name, "3DA");
		props.setProperty(DefaultIdentifiers.id_3da+DefaultIdentifiers.id_lens_offposition, "0");
		props.setProperty(DefaultIdentifiers.id_3da+DefaultIdentifiers.id_lens_onposition, "1");

		//////////
		// Stages

		//PI
		props.setProperty(DefaultIdentifiers.id_objectivestage+DefaultIdentifiers.id_stage_device, "PIZStage");
		props.setProperty(DefaultIdentifiers.id_objectivestage+DefaultIdentifiers.id_objectivestage_positionproperty, "Position");
		props.setProperty(DefaultIdentifiers.id_objectivestage+DefaultIdentifiers.id_objectivestage_sensorproperty, "External sensor");
		
		//SmarAct
		props.setProperty(DefaultIdentifiers.id_objectivestage+DefaultIdentifiers.id_stage_device, "SmaractZ");
		props.setProperty(DefaultIdentifiers.id_objectivestage+DefaultIdentifiers.id_stage_device, "SmaractXY");										// check if that is true	
		
		//////////
		// QPD
		props.setProperty(DefaultIdentifiers.id_qpd+DefaultIdentifiers.id_qpd_device, "Arduino-Input");
		props.setProperty(DefaultIdentifiers.id_qpd+DefaultIdentifiers.id_qpd_channel1, "AnalogInput10");
		props.setProperty(DefaultIdentifiers.id_qpd+DefaultIdentifiers.id_qpd_channel2, "AnalogInput9");
		props.setProperty(DefaultIdentifiers.id_qpd+DefaultIdentifiers.id_qpd_channel3, "AnalogInput8");
				
		/////////////////////////////////////////////////////////
		//// Tasks and device settings
		
		//////////
		// Graph
		props.setProperty(DefaultIdentifiers.id_graph_pi+DefaultIdentifiers.id_graph_numpoints, "30");
		props.setProperty(DefaultIdentifiers.id_graph_qpd+DefaultIdentifiers.id_graph_numpoints, "1");
		props.setProperty(DefaultIdentifiers.id_graph_uv+DefaultIdentifiers.id_graph_numpoints, "800");

		//////////
		// Focus
		props.setProperty(DefaultIdentifiers.id_settings+DefaultIdentifiers.id_settings_smallsteps, "0.5");
		props.setProperty(DefaultIdentifiers.id_settings+DefaultIdentifiers.id_settings_largesteps, "2");
		
		//////////
		// UV activation
		props.setProperty(DefaultIdentifiers.id_task+DefaultIdentifiers.id_task_sdcoeff, "4");
		props.setProperty(DefaultIdentifiers.id_task+DefaultIdentifiers.id_task_uvcoeff, "0.01");
		props.setProperty(DefaultIdentifiers.id_task+DefaultIdentifiers.id_task_nmstimedifference, "20");
		props.setProperty(DefaultIdentifiers.id_task+DefaultIdentifiers.id_task_nmsgaussianmasksize, "3");
		props.setProperty(DefaultIdentifiers.id_task+DefaultIdentifiers.id_task_nmsgaussianmaskprecision, "0.02");
		props.setProperty(DefaultIdentifiers.id_task+DefaultIdentifiers.id_task_nmssize, "7");		
		
		/////////////////////////////////////////////////////////
		//// User interface
		
		//////////
		// Laser settings
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_numberbehavior, "5");
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_behaviorname0, "Off");
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_behaviorname1, "On");
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_behaviorname2, "Rising");
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_behaviorname3, "Falling");
		props.setProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_behaviorname4, "Camera");
		
		return props;
	}


	public String getPropertyValue(String key){																			
		return props_.getProperty(key);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Default settings for the UI components
	public String getDefaultUVcoeff(){
		return props_.getProperty(DefaultIdentifiers.id_task_uvcoeff);
	}
	public String getDefaultSDcoeff(){
		return props_.getProperty(DefaultIdentifiers.id_task_sdcoeff);
	}
	public String getDefaultSmallSteps(){
		return props_.getProperty(DefaultIdentifiers.id_settings_smallsteps);
	}
	public String getDefaultLargeSteps(){
		return props_.getProperty(DefaultIdentifiers.id_settings_largesteps);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Lasers
	public int getNumLasers() {
		int N=0;
		try{
			N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_numberlasers));									/////////////////////////////// wrong property
		} catch(Exception e) {
		}
		return N;
	}

	public String getLaserKeys(int i){																					///// check what happens with lasers numbers
		String s="Error";
		if(i<getNumLasers()){
			s=props_.getProperty(DefaultIdentifiers.id_laser+i+DefaultIdentifiers.id_laser_key);
		}
		return s;
	}

	public Color getLaserColor(int i){	
		String s="";
		if(i<getNumLasers()){
			s=props_.getProperty(DefaultIdentifiers.id_laser+i+DefaultIdentifiers.id_laser_color);
			if(s.equals("uv")){
				return uv;
			} else if(s.equals("red")){
				return red;
			}else if(s.equals("green")){
				return green;
			}else if(s.equals("blue")){
				return blue;
			}
		}
		return null;
	}
	
	public int getLaserMaxPower(int i){	
		int N=0;
		if(i<getNumLasers()){
			N=utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_laser+i+DefaultIdentifiers.id_laser_maxpower));
		}
		return N;
	}
	
	public String[] getLaserBehaviorList(){
		int N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_numberbehavior));
		String[] s = new String[N];
		for(int i=0;i<N;i++){
			s[i] = props_.getProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_behaviorname+i);
		}
		return s;
	}

	public int getLaserDefaultBehavior(int i){	
		int val = 0;
		if(i<getNumLasers()){
			val=utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_laser+i+DefaultIdentifiers.id_laser_defaultbehavior));
		}
		return val;
	}

	public int getMaxPulseLasers(){
		int N = 0;
		N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_laser+DefaultIdentifiers.id_laser_maxpulse));
		return N;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Filters
	public int getNumFilters() {
		int N=0;
		try{
			N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_filter+DefaultIdentifiers.id_filter_numfilters));					
		} catch(Exception e) {
		}
		return N;
	}
	
	public Color getFilterColor(int i){	
		String s="Error";
		if(i<getNumFilters()){
			s=props_.getProperty(DefaultIdentifiers.id_filter+i+DefaultIdentifiers.id_filter_color);

			if(s.equals("uv")){
				return uv;
			} else if(s.equals("red")){	
				return red;
			}else if(s.equals("green")){
				return green;
			}else if(s.equals("blue")){
				return blue;
			}else if(s.equals("neutral")){
				return neutral;
			}else if(s.equals("black")){
				return black;
			}
		}
		return neutral;
	}
	public String getFilterName(int i){																					
		String s="Error";
		if(i<getNumFilters()){
			s=props_.getProperty(DefaultIdentifiers.id_filter+i+DefaultIdentifiers.id_filter_name);
		}
		return s;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Graph
	public int getGraphNumberPoints(String graphname){
		int N = 0;
		if(graphname.equals(DefaultIdentifiers.id_graph_pi)){
			N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_graph_pi+DefaultIdentifiers.id_graph_numpoints));
		} else if(graphname.equals(DefaultIdentifiers.id_graph_uv)){
			N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_graph_uv+DefaultIdentifiers.id_graph_numpoints));
		} else if(graphname.equals(DefaultIdentifiers.id_graph_qpd)){
			N = utils.getIntFromString(props_.getProperty(DefaultIdentifiers.id_graph_qpd+DefaultIdentifiers.id_graph_numpoints));
		} 
		return N;
	}	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Lenses

	public String getLensLabel(String id){
		System.out.println(props_.getProperty(id+DefaultIdentifiers.id_lens_name));
		return props_.getProperty(id+DefaultIdentifiers.id_lens_name);
	}

	public String getLensOffPosition(String id){
		return props_.getProperty(id+DefaultIdentifiers.id_lens_offposition);
	}
	
	public String getLensOnPosition(String id){
		return props_.getProperty(id+DefaultIdentifiers.id_lens_onposition);
	}
}
