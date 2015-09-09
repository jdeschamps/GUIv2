package micromanager;

import java.io.File;

public class Log {

	String path;
	txtWriter w;
	 
	public Log(){
		path = MConfiguration.logpath;
		w = new txtWriter(new File(path));
	}

	Log(String s){
		path = s;
		w = new txtWriter(new File(path));
	}
	
	public void writeToLog(String s){
	 	w.process(new java.util.Date()+" "+s+"\r\n");
	}
	
	public void closeLog(){
		w.close();
	}
}
