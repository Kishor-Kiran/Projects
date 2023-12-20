package AppiumTesting.ESCOMs.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportSetup {
	
	 public static ExtentReports extent;
	    public static ExtentTest test;

	    public static void initialize() {
	        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
	        extent = new ExtentReports();
	        extent.attachReporter(spark);
	    }

}
