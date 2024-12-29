package api.utility;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter; 
	public ExtentReports extent; 
	public ExtentTest test; 
	
	String repName;

	public void onStart(ITestContext testContext)

	{
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
	repName="Test-Report-"+timeStamp;

	sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);

	sparkReporter.config().setDocumentTitle("Offer_Validation_Test_Report"); 
	sparkReporter.config().setReportName("Offer Validation Test Report"); 
	sparkReporter. config().setTheme(Theme.DARK) ;

	extent=new ExtentReports();
	extent .attachReporter(sparkReporter);

	extent. setSystemInfo("Application", "Zomato");
	extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	extent. setSystemInfo("User Name", System.getProperty("user.name"));
	extent. setSystemInfo("Environment","QA");
	extent. setSystemInfo("User","Chandrashekhar");
	}
	

	public void onTestSuccess (ITestResult result) {
		test = extent.createTest(result.getName()); 
		
		test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
	}
	public void onTestFailure (ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		test.log(Status.FAIL, "Test Case FAILED cause is:" + result.getThrowable());
	}
	public void onTestSkipped (ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
	}
	public void onFinish(ITestContext context) {
	    extent.flush();
	}
	
	
	

}