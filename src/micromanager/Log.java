package micromanager;

import java.io.File;

public class Log {

	String path;
	txtWriter w;
	boolean enabled = true;
	 
	public Log(){
		path = MConfiguration.logpath;
		enabled = MConfiguration.logenabled;
		
		if(enabled){
			w = new txtWriter(new File(path));
		}
	}

	Log(String s){
		path = s;
		enabled = MConfiguration.logenabled;
		if(enabled){
			w = new txtWriter(new File(path));
		}
	}
	
	public void writeToLog(String s){
		if(enabled){
			w.process(new java.util.Date()+" "+s+"\r\n");
		}
	}
	
	public void closeLog(){
		if(enabled){
			w.close();
		}
	}
}
