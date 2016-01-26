package micromanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Log {

	///////////////////////////////////////////////////////////////////////
	/// rewrite so that it appends (like UVautomator) text and can be seen live, so far it shows nothing
	
	
	String path;
	PrintWriter w;
	boolean enabled = true;
	 
	public Log(){
		path = MConfiguration.logpath;
		enabled = MConfiguration.logenabled;
		
		if(enabled){
			try {
				w = new PrintWriter(new File(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	Log(String s){
		path = s;
		enabled = MConfiguration.logenabled;
		if(enabled){
			try {
				w = new PrintWriter(new File(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeToLog(String s){
		if(enabled){
			w.println(new java.util.Date()+" [GUI] "+s+"\r\n");
		}
	}
	
	public void closeLog(){
		if(enabled){
			w.close();
		}
	}
}
