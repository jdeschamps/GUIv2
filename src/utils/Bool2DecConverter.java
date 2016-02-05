package utils;

public class Bool2DecConverter {

	public static int getDecimal16bits(String s){
		int val =0 ;
		if(s.length() != 16){
			return 0;
		}
	
		for(int i=0;i<16;i++){
			if(!s.substring(i, i+1).equals("0") && !s.substring(i, i+1).equals("1") ){
				return 0;
			}
		}
		
		return Integer.parseInt(s, 2);
	}
	
	
}
