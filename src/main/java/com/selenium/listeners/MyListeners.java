package com.selenium.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.utilities.ExtentReporter;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentStep;
	WebDriver driver;

	@Override
	public void onStart(ITestContext context) {

		System.out.println("Project Test Cases started");

		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// System.out.println(result.getName() + " - Test Case started Executing");

		extentStep = extentReport.createTest(result.getName());
		extentStep.log(Status.INFO, result.getName() + " - Test Case started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// System.out.println(result.getName() + " - Test case executed Successfully");

		extentStep.log(Status.PASS, result.getName() + " - Test case executed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println(result.getName() + " - test Case got failed");
		extentStep.log(Status.FAIL, result.getName() + " - test Case got failed");
		extentStep.log(Status.INFO, result.getThrowable());
		
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} 
		
		//taking screenshot and save it in screenshot folder
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File scrScrrenshotPath = screenshot.getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir")+"\\screenshot\\"+result.getName()+".png";
		try {
			FileUtils.copyFile(scrScrrenshotPath, new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//attach screenshot to extent report
		extentStep.addScreenCaptureFromPath(destScreenshotPath);
	}
					

	@Override
	public void onTestSkipped(ITestResult result) {
		// System.out.println(result.getName() + " - test case got skipped");
		extentStep.log(Status.SKIP, result.getName() + " - test case got skipped");
		extentStep.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		// System.out.println("All the test cases execution completed!!!");
		extentStep.log(Status.INFO, "All the test cases execution completed!!!");
		
		extentReport.flush();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
