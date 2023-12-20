package AppiumTesting.ESCOMs.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GruhaJothiSchema {
	
	
	
	
	public double GJSchema(String SactionLoad,String TraffiCode,double consumption,String RebateCode,String ConsumStatus,String Entitlement,double ECforGJS) {
		double GJSFC = 0.0;
		double GJSEC = 0.0;
		
		/*		FC */
		
		double intSactionLoad = Integer.parseInt(SactionLoad);
		  // Find matching enum constant for TraffiCode
	    TraffiRates matchingRate = TraffiRates.findMatchingRate(TraffiCode,consumption);
	    
	    for (TraffiRates rate : TraffiRates.values()) {
	        if (TraffiCode.contains(rate.name())) {
	            matchingRate = rate;
	            break;
	        }
	    }
	    double doubleSactionLoad = Double.parseDouble(SactionLoad);
	    
	    if (matchingRate != null ) {
	    	if(TraffiCode.equals("LT-1" ) && consumption <= 40 ) {}else {
	         GJSFC = intSactionLoad * matchingRate.getID();
	    	}
	    }
	
	    /*		EC */
//	    TraffiECRates matchingRateEC = TraffiECRates.findMatchingRate(TraffiCode,consumption,Entitlement);
//	    for (TraffiECRates rate : TraffiECRates.values()) {
//	        if (TraffiCode.contains(rate.name())) {
//	        	matchingRateEC = rate;
//	            break;
//	        }
//	    }
//	   
//	    
	    double EntitlementUnits = Double.parseDouble(Entitlement);
//	    if (matchingRateEC != null) {
//	    	GJSEC = EntitlementUnits * matchingRateEC.getID();
//	    }
	    if(consumption < EntitlementUnits ) {
	    	GJSEC = consumption * ECforGJS ;
	    }else {
	    GJSEC = EntitlementUnits * ECforGJS ;
	    }
//	    System.out.println("ECforGJS : " + ECforGJS);
//	    System.out.println("EntitlementUnits : " + EntitlementUnits);
//	    System.out.println("GJSEC : " + GJSEC);

	    
	    	
/*  TAX  */   	
	    
	    double tax = 0.0 ;
	    if(TraffiCode.equals("LT-1")&& consumption<= 40) {}else {
		 tax =  (0.09*GJSEC) ;
	    }
		
/* FAC  */
		double FAC =  (0.35 * consumption) ;
		
		 double totalRebates =0.0 ;
		 if(RebateCode.contains("LT-R")) {
		List<String> myList = new ArrayList<String>(Arrays.asList(RebateCode.split(",")));
		RebateProcessor rebateProcessor = new RebateProcessor(GJSEC, GJSFC);
		  totalRebates = rebateProcessor.calculateTotalRebates(myList, consumption,ConsumStatus);
	        double REBATES = RebateProcessor.calculateTotalRebates(myList, consumption,ConsumStatus);
		
	       
				  
			}   
	        
	        
//	        System.out.println("GJSEC : " + GJSEC);
//	        System.out.println("GJSFC : " + GJSFC);
//	        System.out.println("tax : " + tax);
//	        System.out.println("FAC : " + FAC);
//	        System.out.println("GJS rebate  : " + totalRebates);
	    return GJSEC+GJSFC+tax+ FAC -  totalRebates;
}
}