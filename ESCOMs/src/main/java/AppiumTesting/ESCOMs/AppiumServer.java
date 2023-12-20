package AppiumTesting.ESCOMs;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import AppiumTesting.ESCOMs.Utility.ExcelLibriary;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class AppiumServer {
	
	 private Map<String, Object[]> TestNGResults = new HashMap<>();
	ExcelLibriary obj=new ExcelLibriary("C:\\Users\\siva.v\\Desktop\\APKtestData.xlsx");
	public static AppiumDriver<MobileElement> driver;
	private int currentTestRowIndex = 1;
	 private ExtentReports extent;
	    private ExtentTest test;
	
	@BeforeMethod
	public void startSession() throws MalformedURLException {
	   
	    DesiredCapabilities cap = new DesiredCapabilities();
	    cap.setCapability("deviceName", "ANDRIOD7.1");
	    cap.setCapability("appium:platformVersion", "7.1");
	    cap.setCapability("platformName", "android");
	    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
	    cap.setCapability("appPackage", "in.nsoft.spotbilling");
	    cap.setCapability("appActivity","in.nsoft.spotbilling.LoginActivity"); 
	    driver = new AppiumDriver<MobileElement>(new URL("http://192.168.6.66:4723/wd/hub"), cap);
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  

	    if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReports.html");
            extent.attachReporter(htmlReporter);
        }      
	    MobileElement billingButton = driver.findElement(By.id("in.nsoft.spotbilling:id/item_image"));
	    billingButton.click();  
	}



	@AfterMethod
	public void endSession(ITestResult result) throws IOException {
	String statusString;
    	switch(result.getStatus()) {
    		case ITestResult.FAILURE:
    			statusString = "FAIL";
//    			GetScreenshot.capture(driver, result.getName());
    			
    			 File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	            String screenshotPath = "./screenshots/" + result.getName() + "_failure_" + timeStamp + ".png";
    	            FileUtils.copyFile(screenshot, new File(screenshotPath));
    	            test.fail("Test failed").addScreenCaptureFromPath(screenshotPath);
    			System.out.println("TEST CASE FAILED");
    			break;
    		case ITestResult.SUCCESS:
    			statusString = "PASS";
     			break;
    		case ITestResult.SKIP:
    			statusString = "SKIP";
    			System.out.println("TEST CASE SKIPPED");
    			break;
    		default:
    			statusString = "STATUS_UNKNOWN";
    	}
    	  test = extent.createTest(result.getMethod().getMethodName());
			 obj.setCellData("ConnectionID", "Status", currentTestRowIndex, statusString);
			    currentTestRowIndex++;
    	if (driver != null) {
        	driver.quit();
        	  extent.flush();
    	}
    	
	}
	
	
	}



