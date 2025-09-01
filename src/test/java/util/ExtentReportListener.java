package util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportListener  implements ITestListener {
	
	public static ExtentReports extent;

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


	
	 @Override
     public void onStart(ITestContext context) {
		 try {
		 String reportPath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\reports.html";
//		 System.out.println(reportPath);
		 System.out.println("Into Listeners");
         ExtentSparkReporter  sreporter = new ExtentSparkReporter(reportPath);
         
         sreporter.config().setDocumentTitle("LaunchQA Test");
         sreporter.config().setReportName("Rough Test");
         sreporter.config().setTheme(Theme.DARK);
         
         extent = new ExtentReports();
         extent.attachReporter(sreporter);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
         
     }

     @Override
     public void onTestStart(ITestResult result) {
         ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
         test.set(extentTest);
         test.get().info("Test started");
     }

     @Override
     public void onTestSuccess(ITestResult result) {
         test.get().log(Status.PASS, "Test Passed");
     }

     @Override
     public void onTestFailure(ITestResult result) {
    	 System.out.println("Into Listeners --Test Failure");
         test.get().log(Status.FAIL, "Test Failed");
         test.get().fail(result.getThrowable()); // Log the exception
      // Get driver instance from the test class instance using reflection
         Object testClassInstance = result.getInstance();
         
     }
         
         // Add screenshot capture logic here if needed
   

     @Override
     public void onTestSkipped(ITestResult result) {
         test.get().log(Status.SKIP, "Test Skipped");;
     }

     @Override
     public void onFinish(ITestContext context) {
         extent.flush();// Flush the reports at the end of the test suite
     }


}
