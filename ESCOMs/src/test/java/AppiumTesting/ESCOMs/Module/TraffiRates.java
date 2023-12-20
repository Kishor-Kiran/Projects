package AppiumTesting.ESCOMs.Module;

public enum TraffiRates {
	
	LT1(110),
    LT2(110),
    LT2b(180),
    LT3(200),
    LT3Above50KW(300),
    LT3DBT(220),
    LT3DBTAbove50KW(320),
    LT4(135),
    LT5BELOW500(140),
    LT5ABOVE500(250),
    LT5ABOVE500DBT(300),
    LT5BELOW500DBT(190),
    LT6A(175),
    LT6B(175),
    LT6C(70),
    LT7(200); 
 
 private int FCrate;

	  TraffiRates(int FCrate){
	    this.FCrate = FCrate;
	  }

	
	  
	  
	  
	  
	  
	  public int getID(){
	    return FCrate;
	  }
 
	  public static TraffiRates findMatchingRate(String code,double  consumption) {
		  if (code.startsWith("LT-")) {
	            String numberPart = code.substring(3).split("\\(")[0]; // Extract the number part after "LT-"
	            try {
	                int number = Integer.parseInt(numberPart);
	                // Logic to map number to enum constant
	                if (number == 11) {
	                	if (consumption <= 40.0) {
	                		
	                        return LT1;
	                    } else if(consumption > 40.0) {
	                    
	                        return LT2;
	                    }
	                } else if (number == 21) {
	                    return LT2;
	                }else if (number == 22) {
	                    return LT2b;
	                }
	                else if (number == 31) {
	                    return LT3;
	                } 
	                else if (number == 32) {
	                    return  LT3Above50KW ;
	                }else if (number == 41) {
	                    return LT4;
	                } else if (number == 51) {
	                    return LT5BELOW500;
	                } else if (number == 52) {
	                    return LT5ABOVE500;
	                } else if (number == 61) {
	                    return LT6A;
	                }
	                else if (number == 62) {
	                    return LT6B;
	                }
	                else if (number == 63) {
	                    return LT6C;
	                }
	                else if (number == 7) {
	                    return LT7;
	                }
	                // Handle other cases as required
	            } catch (NumberFormatException e) {
	                // Handle invalid number format
	            }
	        }
	        return null;
	    }
	
	


}



