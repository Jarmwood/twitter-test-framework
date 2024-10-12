package com.twitter.listeners;


import java.io.IOException;

import com.twitter.extentmanager.ExtentManager;
import com.twitter.extentmanager.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.testEngine;
import utilities.TakeScreenshot;


public class TestListeners extends testEngine implements ITestListener{
	/*
	 * BASIC HTML SYNTAX:
	 * <b> & </b> --> HTML for bold text
	 * <font color=xxxxxxxxx> & </font> --> specify Font color
	 * <br> --> line break
	 * \n --> new line
	 */

	public void onTestStart(ITestResult result) {
		System.out.println("Beginning Test Execution: " + result.getMethod().getMethodName());
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		
	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String screenShotPath = "";
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		//Take the Screen shot
		try {
			screenShotPath = TakeScreenshot.takeScreenshot(testName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//Add the screenshot to the report
		try {
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenShotPath); //Adding the screen shot at the bottom of the report
			
			//Attach the screen shot in-line
			ExtentTestManager.getTest().info("<b>" + "<font color=red>" + "Screenshot of Failure" +
									"</font>" + "</b>", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		String failureMessage = "<b>" + "Test Case: " + testName + " FAILED" + "</b>";
		Markup m = MarkupHelper.createLabel(failureMessage, ExtentColor.RED);
		ExtentTestManager.getTest().log(Status.INFO, "------ Test Execution Completed ------");
		ExtentTestManager.getTest().fail(m);
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {

		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		
	}

}