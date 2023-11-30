package com.selenium.testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.RegisterPage;

public class RegisterTest extends Base {

	public WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void startChromeBrowser() throws IOException {
		prop = readingDataFromPropFile();
		driver = browserInitiolizationAndLaunchUrl(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()= 'My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	// 1. Enter all the fields except subscription news letter and register
	// Successfully
	@Test(priority = 1)
	public void registerWithoutSubscription() {
		RegisterPage registerPage = new RegisterPage(driver, prop);
		registerPage.registrationDetails("firstname", "lastname", "regemail", "telephone", "pass");
		registerPage.regWithoutSubscription();
	}

	// 2. Enter all the fields including subscription news letter and register
	// Successfully
	@Test(priority = 2)
	public void registerWithSubscription() {
		RegisterPage registerPage = new RegisterPage(driver, prop);
		registerPage.registrationDetails("firstname", "lastname", "regemail", "telephone", "pass");
		registerPage.regWithSubscription();
	}

	// 3. Enter all the fields except Privacy Policy - capture the error message
	@Test(priority = 3)
	public void registrationWithoutPrivacyPolicyCheck() {
		RegisterPage registerPage = new RegisterPage(driver, prop);
		registerPage.registrationDetails("firstname", "lastname", "regemail", "telephone", "pass");
		registerPage.regWithoutPolicyChecker();

	}

	// 4. Do not enter any fields -just click on register button - capture all the
	// error messages and assert it.
	@Test(priority = 4)
	public void registerWithEmptyFields() {
		RegisterPage registerPage = new RegisterPage(driver, prop);
		registerPage.regWithEmptyFields();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
