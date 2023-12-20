package AppiumTesting.ESCOMs.Module;

import org.bouncycastle.jcajce.provider.asymmetric.EC;

public enum TraffiECRates {

	
	LT1(9.62),
    LT2(4.75),
    LT2Above100(7.00),
    LT2b(7.75),
    LT3A(8.50),
    LT3B(10.50),
    LT4a(4.76),
    LT4b(4.10),
    LT4c(4.25),
    LT5BELOW500(6.10),
    LT5ABOVE500(7.10),
    LT6A(5.50),
    LT6B(7.00),
    LT6C(4.50),
    LT7(11.50);
 
 private double ECrate;



	TraffiECRates(double d){
	    this.ECrate = d;
	  }

	
	  
	  
	  
	  
	  
	  public double getID(){
	    return ECrate;
	  }
 
	  Consumption con = new Consumption();
	
	  
	  public static TraffiECRates findMatchingRate(String code, double consumption, String Entitlement) {
		    double Entitle = Double.parseDouble(Entitlement);
            String numberPart = code.substring(3).split("\\(")[0]; // Extract the number part after "LT-"
try {
		    // Improved logic to handle different patterns after "LT-"
		    int number = Integer.parseInt(numberPart);
            // Logic to map number to enum constant
            if (code.contains("LT-11")) {
             	if (consumption <= 40.0) {
                    return LT1;
                } else if(consumption > 40.0 && consumption <= 100) {
                	
                    return LT2;
                }else if(consumption > 100.0  && Entitle < 100.0 ) {
                	
                    return LT2Above100;
                }else if(consumption > 100.0) {
                	
                    return LT2Above100;
                }
            } else if (code.contains("LT-21")) {
            	
                if (consumption <= 100.0) {
                    return LT2;
                } else {
                    return LT2Above100;
                }
            }else if(code.contains("LT-22")) {
           
            	return LT2b ;
            }
            else if (code.contains("LT-31")) {
            
                return LT3A;
            }  else if (code.contains("LT-32")) {
            	
                return LT3B;
            } 
            else if (code.contains("LT-41")) {
                return LT4a ;
            } 
            else if (code.contains("LT-42")) {
                return LT4b ;
            }
            else if (code.contains("LT-43")) {
                return LT4c ;
            }else if (code.contains("LT-5")) {
            	if(consumption <=500.0) {
            		return LT5BELOW500 ;
            	}else if(consumption > 500.0) {
            		return LT5ABOVE500;
            	}
            	
                
            }  else if (code.equals("LT-61")) {
                return LT6A;
            }
            else if (code.equals("LT-62")) {
                return LT6B;
            }
            else if (code.equals("LT-63")) {
                return LT6C;
            }
            else if (code.equals("LT-7")) {
                return LT7;
            }
            // Handle other cases as required
       
		    

		   
		}
	
	catch (NumberFormatException e) {
		// TODO: handle exception
	}
return null; // If no match found, return null
	  }


}


