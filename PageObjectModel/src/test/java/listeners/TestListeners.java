package listeners;


import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.testBase;
import utilities.TakeScreenshot;


public class TestListeners extends testBase implements ITestListener{
	/*
	 * BASIC HTML SYNTAX:
	 * <b> & </b> --> HTML for bold text
	 * <font color=xxxxxxxxx> & </font> --> specify Font color
	 * <br> --> line break
	 * \n --> new line
	 */

	public void onTestStart(ITestResult result) {
		System.out.println("Beginning Test Execution: " + result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		//getting the current test's name
		String testName = result.getMethod().getMethodName();
		
		//Test Case: LoanCalculatorTest PASSED <-- will be bolded
		String logText = "<b>" + "Test Case: " + testName + "  PASSED" + "</b>";
		
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		
		//Get the current thread's testReport and add the details to the Test Report
		testReport.get().log(Status.INFO, "------ Test Execution Completed ------");
		
		//Get the current thread's testReport and add a 'Pass' status with the specified markup details
		testReport.get().pass(m);
		
	}

	public void onTestFailure(ITestResult result) {
		/*
		 * 1. Failure's exception (stackTrace) to be presented in the Report
		 * 2. Screenshot of the browser window at time of exception/failure
		 */
		
		String testName = result.getMethod().getMethodName();
		String screenShotPath = "";
		/*
		 * 'result' contains all details of the current test method's execution
		 * from 'result' we are going to get the exception details stored in 'Throwable' class (getThrowable())
		 * getStackTrace() method will return the StackTrace as an Array
		 * toString() method to convert from Array to a string and store the stackTrace in a string variable
		 */
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		
		testReport.get().info("<details>" + "<summary>" + "<b>" + "<font color=red>"
						+ "Exception Occurred: Click to View Details" + "</font>" + "</b>" + "</summary>" 
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
							//Replacing all of the commas (,) with a line break "<br>"
		
		//Take the Screen shot
		try {
			screenShotPath = TakeScreenshot.takeScreenshot(testName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//Add the screenshot to the report
		try {
			testReport.get().addScreenCaptureFromPath(screenShotPath); //Adding the screen shot at the bottom of the report
			
			//Attach the screen shot in-line
			testReport.get().info("<b>" + "<font color=red>" + "Screenshot of Failure" + 
									"</font>" + "</b>", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		String failureMessage = "<b>" + "Test Case: " + testName + " FAILED" + "</b>";
		Markup m = MarkupHelper.createLabel(failureMessage, ExtentColor.RED);
		testReport.get().log(Status.INFO, "------ Test Execution Completed ------");
		testReport.get().fail(m);
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		
		//System.out.println(context.getName());
		
		//Create the test in ExtentReports at the time of execution start
		test = extent.createTest(context.getName());
		
		//set the created test to refer to the current thread (in case of parallel execution)
		testReport.set(test);
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}