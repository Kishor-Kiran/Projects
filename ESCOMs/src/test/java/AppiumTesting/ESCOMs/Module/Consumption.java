package AppiumTesting.ESCOMs.Module;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import AppiumTesting.ESCOMs.AppiumServer;
import AppiumTesting.ESCOMs.Utility.ExcelLibriary;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Consumption extends AppiumServer {


	public Consumption() {
		super();
		// TODO Auto-generated constructor stub
	}

	ExcelLibriary obj=new ExcelLibriary("C:\\Users\\siva.v\\Desktop\\APKtestData.xlsx");
	
	@Test(dataProvider = "ConnectionID",testName = "SbmApk" , description = "Escom SbmApk ")
	public void Billing(
			String CoonectionId,
			String TraffiCode ,
			String ConsumStatus,
			String MD,
			String PowerFactor,
			String PresentReading,
			String PreviousReading,
			String PreviousReadingCase ,
			String MeterConstant,
			String SactionLoad,
			String RebateCode,
			String ARREARS,
			String InterestOther,
			String GruhaJothi,
			String Entitlement,
			String Statu
			
			)throws NumberFormatException {
	   
		double FC = 0.0;
		double ExcessLoadPenalty = 0.0;
		double ExcessPFPenalty = 0.0;
		double EC =0.0 ;
		FluentWait<AppiumDriver<MobileElement>> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(10))
			    .pollingEvery(Duration.ofMillis(500))
			    .ignoring(NoSuchElementException.class);
		
		    MobileElement ConnectionIdSearch = driver.findElement(By.id("in.nsoft.spotbilling:id/autoBillingSearch"));

		    ConnectionIdSearch.sendKeys(CoonectionId );
			
		    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
			
		    List<MobileElement> auto = driver.findElements(By.id("lblBillingConnectionId"));
		    
		    int count = auto.size();
		    auto.get(count-1).click();
		    
		MobileElement Traffi =    driver.findElement(By.id("in.nsoft.spotbilling:id/txtBillingTariff"));
		    driver.findElement(By.id("btnBillingOk")).click();
		    
		    
		   MobileElement   BillConsumStatus =  driver.findElement(By.id("ddlBillConsumStatus"));
		   BillConsumStatus.click();
	        List<MobileElement> options=driver.findElementsByClassName("android.widget.TextView");//using class name
	        for(MobileElement e:options)
	        {
	            String val=e.getText();
	           
	            if(val.equalsIgnoreCase(ConsumStatus))
	            {
	                e.click();
	                break;
	            }
	        }
			   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			   
			   MobileElement   Reading =  driver.findElement(By.id("txtBillConsumStatusReading"));
			   if(Reading.isEnabled()) { 
				   Reading.sendKeys(PresentReading);
				   
			   }
			   else {}
			   
			   MobileElement   BillConsumMD =  driver.findElement(By.id("txtBillConsumMD"));
			   if(BillConsumMD.isEnabled()) {
			   BillConsumMD.sendKeys(MD);
			   }
			   MobileElement   BillConsumPowerFactor =  driver.findElement(By.id("txtBillConsumPowerFactor"));
			   if(BillConsumPowerFactor.isEnabled()) {
				   BillConsumPowerFactor.sendKeys(PowerFactor);
		   }
		
		   MobileElement   ConsumProcess =  driver.findElement(By.id("btnBillConsumProcess"));
		   ConsumProcess.click();
		   MobileElement SaveBtn =driver.findElement(By.id("btnBillConsumSavePrint"));
		   if(SaveBtn.isDisplayed()) {
			   SaveBtn.click();
		   }
		   
		   MobileElement DisplayData =driver.findElement(By.id("tvInflatorDisplayData"));

		   String DisplayDataString = DisplayData.getText();
		
		String[] DataArray = DisplayDataString.split("\\s+");
		String stringconsumption = DataArray[8];
		double intDataArray = Double.parseDouble(stringconsumption);
		
		
/*		consumption */		
				
		double intPresentReading = Integer.parseInt(PresentReading);
		double intpreviousReading = Integer.parseInt(PreviousReading);
			int intpreviousReadingCase = Integer.parseInt(PreviousReadingCase);
			int intMeterConstant = Integer.parseInt(MeterConstant);
			double	consumption = 0.0 ;
			if(ConsumStatus.contains("Normal")) {
			 consumption= (intPresentReading - intpreviousReading) * intMeterConstant ;
			}else if (ConsumStatus.contains("MNR")||ConsumStatus.contains("DL")||ConsumStatus.contains("DIR")||ConsumStatus.contains("MB")) {
				consumption = intpreviousReadingCase ;
				
			}else if(ConsumStatus.contains("VA")) {
				consumption = 0.0 ;
			}
			
//			double consumptionfromScreen = Double.parseDouble(DataArray[8]);
		
			 
/*		Curr.Bill Amt  */
			
			
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
		    
		    if (matchingRate != null) {
		    	
		    	
		    	if(TraffiCode.equals("LT-1") && consumption <= 40) {}
		    	else {
		         FC = intSactionLoad * matchingRate.getID();
		    	}
		        
/*		BMD */
		        String Md =  DataArray[5] ;
			   
		        double BMD = Double.parseDouble(MD);
			    double diffMD = BMD - doubleSactionLoad ;
			    if(doubleSactionLoad < BMD) {
			    	
			    	if(TraffiCode.contains("LT-1") || TraffiCode.contains("LT-2")) {
			    	
			     ExcessLoadPenalty = diffMD *  matchingRate.getID() * 1.5   ;
			    	}
			    	else {
			    		 ExcessLoadPenalty = diffMD *  matchingRate.getID() * 2.0  ;
			    	}
			   
			    }
		    }
		    	
		
/*		EC  */
		
//		    System.out.println("consumption : " + consumption);
	    TraffiECRates matchingRateEC = TraffiECRates.findMatchingRate(TraffiCode,consumption,Entitlement);
	    for (TraffiECRates rate : TraffiECRates.values()) {
	        if (TraffiCode.contains(rate.name())) {
	        	matchingRateEC = rate;
	            break;
	        }
	    }
	    
	    if (matchingRateEC != null) {
	         EC = consumption * matchingRateEC.getID();
	    }
	    double ECforGJS =  matchingRateEC.getID();
	   
/* PF PENALTY */
	    
	    String Pf = DataArray[2];
	    double PfPenalty = Double.parseDouble(PowerFactor);
	    double ExcessPfLoad = 0 ;
	    if( PfPenalty <= 0.85 && PfPenalty > 0.0 ) {
	    		double DoubleSactionLoad = Integer.parseInt(SactionLoad);
		   
	    		double diffPf = 0.85 - PfPenalty ;
	    		double multiplePFintotwo = diffPf * 2 ;
	    	 if (multiplePFintotwo > 0.3) {
	    		 multiplePFintotwo = 0.3;
	    	    }
	    	 ExcessPfLoad = multiplePFintotwo * consumption ;	
	    }
 /*  TAX  */   	
	    double tax = 0.0 ;
	    if(TraffiCode.equals("LT-1") && consumption<= 40) {}else {
		 tax =  (0.09*EC) ;
	    }
		
/* FAC  */
		double FAC =  (0.35 * consumption) ;

/* rebate  */	
		
		List<String> myList = new ArrayList<String>(Arrays.asList(RebateCode.split(",")));
		RebateProcessor rebateProcessor = new RebateProcessor(EC, FC);
		double totalRebates = rebateProcessor.calculateTotalRebates(myList, consumption,ConsumStatus);
	    double REBATES = RebateProcessor.calculateTotalRebates(myList, consumption,ConsumStatus);
	       
		
/* ARREARS  */	
		double Arrears = Double.parseDouble(ARREARS);
	
		
		double consumedBillWithoutRebates =  (FC + EC + tax + FAC);
	        
			// Now subtract REBATES from consumedBillWithoutRebates to get the final bill with rebates applied.
	        double consumedBillWithRebates =  (consumedBillWithoutRebates - totalRebates);
/* GTS */	        
	        double GJS =0.0;
	        int GruhaJS = Integer.parseInt(GruhaJothi);
	        double	EntitleMentUints =Double.parseDouble(Entitlement); 
			if(GruhaJS == 1 && EntitleMentUints <= 200 && consumption <= 200 ) {
			
	        GruhaJothiSchema GJSchema = new GruhaJothiSchema();
	        if(consumption < EntitleMentUints) {
	        	EntitleMentUints  = consumption ;
	        	
	        }
//	        System.out.println(" EntitleMentUints :  " + EntitleMentUints);
	         GJS = GJSchema.GJSchema(SactionLoad, TraffiCode, EntitleMentUints, RebateCode, ConsumStatus,Entitlement,ECforGJS);
//	         System.out.println("GJS  Value : " + GJS);
			}
				
			
//			System.out.println("CoonectionId : " + CoonectionId);
	      
	     double currentAmountWithAllChargesAndPenaltiesAndRebates =(consumedBillWithoutRebates + ExcessLoadPenalty + ExcessPfLoad  - totalRebates);
	     double InterestOtherscharges = Double.parseDouble(InterestOther);
	     double BillminusGJS = consumedBillWithRebates - GJS ;
	     double  NETBillAmt =  (currentAmountWithAllChargesAndPenaltiesAndRebates + Arrears+ InterestOtherscharges)-GJS ;
		BigDecimal bd = new BigDecimal(NETBillAmt).setScale(0, RoundingMode.HALF_UP);  
		double FinalNETAMT =  bd.doubleValue();  
		double currentAMTfromtheScreen = Double.parseDouble(DataArray[13]); 
		double netAMTfromtheScreen = Double.parseDouble(DataArray[24]); 
		MobileElement   buttonSave =  driver.findElement(By.id("android:id/button2"));
		buttonSave.click();
//		System.out.println("consumedBillWithRebates : " + consumedBillWithRebates);
//		System.out.println("consumedBillWithoutRebates : " + consumedBillWithoutRebates);
//		System.out.println("BillminusGJS : " + BillminusGJS);
//		System.out.println("consumption : " + consumption);
//		System.out.println("FC : " + FC);
//		System.out.println("EC : " + EC);
//		System.out.println("FAC : " + FAC);
//		System.out.println("TAX : " + tax);
//		System.out.println("ExcessLoadPenalty : " + ExcessLoadPenalty);
//		System.out.println("ExcessPfLoad : " + ExcessPfLoad);
//		System.out.println("totalRebates : " + totalRebates);
//		System.out.println("ARREARS : " + ARREARS);
//		System.out.println("InterestOtherscharges : " + InterestOtherscharges);
		System.out.println("FinalNETAMT : " + FinalNETAMT);
//		assertEquals(consumption, consumptionfromScreen,"CONSUMPTION RESULT");
		assertEquals(netAMTfromtheScreen,FinalNETAMT,"TOTAL BILL AMOUNT" );
//		assertEquals(currentAMTfromtheScreen,currentAmountWithAllChargesAndPenaltiesAndRebates,"CURRENT BILL AMOUNT" );
		  
		assertTrue(true);
	}
				

	
	
	@DataProvider(name="ConnectionID")
	public Object[][] getExcelData() throws Exception {
		//Total row count
		int row = obj.getRowCount("ConnectionID");

		//ToTal Columns
		System.out.println("This is total rows :" +row);
		int Column=	obj.getColumnCount("ConnectionID") - 1;
		System.out.println("This is total Column :" + Column);
		Object[][] data=new Object[row][Column];
		
		for (int i = 0; i < row; i++) {
		for (int j = 0; j < Column; j++) {
			data[i][j]=obj.getCellData("ConnectionID", j+1,i+1);
			
		}

	}

		System.out.println("This is the Excel ConnectionID Page data for Billing  : "+ data);
		return data ;
	}
	
	
	
	
	
}


