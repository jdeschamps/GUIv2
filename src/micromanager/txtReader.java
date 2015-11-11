package micromanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class txtReader {

	private Locale curLocale;
	private FileReader w;
	private char[] content;

	public txtReader(File file) {
		content = new char[200];
		this.curLocale = Locale.getDefault();
		final Locale usLocale = new Locale("en", "US"); // setting us locale
		Locale.setDefault(usLocale);
		try {
			w = new FileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void process(){
		try {
			w.read(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printContent(){
		System.out.println(content);
	}
	
	public char[] getContent(){
		return content;
	}
	
	public void close(){
		try {
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Locale.setDefault(curLocale);
	}
}
