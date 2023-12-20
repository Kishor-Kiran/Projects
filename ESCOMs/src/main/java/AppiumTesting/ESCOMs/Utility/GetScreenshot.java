package AppiumTesting.ESCOMs.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GetScreenshot {
	
	

	public static String capture(AppiumDriver<MobileElement> driver, String set ) throws IOException {
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-mm-s");
		Date date=new Date();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + set + dateFormat.format(date)+" .png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination); 

		return dest;
	}

}
