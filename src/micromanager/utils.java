package micromanager;

public class utils {


    public static boolean isNumeric(String s) {  
    	if(s.matches("[-+]?\\d*\\.?\\d+")){
    		return true;
    	} else if(s.matches("[-+]?\\d*\\,?\\d+")){
    		return true;
    	}
        return false;  
    }  
    
	
}
