package com.selenium.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		
		//File extentReportPath = new File("D:\\java\\MavenAutoProject\\test-output\\ExtentReports\\extentReport.html");
		File extentReportPath = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportPath);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Selenium Automation Report");
		sparkReporter.config().setReportName("Selenium Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:MM:SS");
		
		extentReport.attachReporter(sparkReporter);
		
		extentReport.setSystemInfo("Application URL:", "https://tutorialsninja.com/demo");
		extentReport.setSystemInfo("Breowser Name:", "Chrome");
		extentReport.setSystemInfo("Operating System:", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java version:", System.getProperty("java.version"));
				
		return extentReport;
	}
}
