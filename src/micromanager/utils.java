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

    public static int getIntFromString(String s){
		int val = 0;
    	try{
			val = Integer.parseInt(s);
		} catch(Exception e){
			val = (int) Math.floor(Double.parseDouble(s));
		}
    	return val;
    }
    
    public static double getDoubleFromString(String s){
		double val = 0;
    	try{
			val = Double.parseDouble(s);
		} catch(Exception e){
		}
    	return val;
    }
}
