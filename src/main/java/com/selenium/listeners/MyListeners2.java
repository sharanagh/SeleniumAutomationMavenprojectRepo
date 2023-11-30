package com.selenium.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.utilities.ExtentReporter;

public class MyListeners2 implements ITestListener{
ExtentReports extentReport;
ExtentTest extentStep;
WebDriver driver;

public void OnStart(ITestResult result) {
	System.out.println("Project Test case Started");
	extentReport= ExtentReporter.generateExtentReport();
}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentStep= extentReport.createTest(result.getName());
		extentStep.log(Status.INFO, result.getName() +"- Test stared executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentStep.log(Status.PASS, result.getName() +"- Test executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentStep.log(Status.FAIL, result.getName()+"- Test execution skipped");
		extentStep.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
