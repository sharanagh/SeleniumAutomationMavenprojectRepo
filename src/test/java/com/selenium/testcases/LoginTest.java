package com.selenium.testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
import com.selenium.utilities.Utility;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class LoginTest extends Base {

	public WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void startChromeBrowser() throws IOException {
		prop = readingDataFromPropFile();
		driver = browserInitiolizationAndLaunchUrl(prop.getProperty("browserName"));

		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		}

	@DataProvider(name = "supplyTestData")
	public Object[][] supplyDataFromFile() {
		Object[][] data = Utility.readDataFromExcelFile("LoginData");
		return data;
	}

	@Test(dataProvider = "supplyTestData", priority = 1)
	public void loginWithValidUserNamePassword(String email, String password) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAndPassword(email,password);
	    loginPage.clickOnLoginButton();
		
	}

	@Test(priority = 2)
	public void loginWithValidUsernameAndInvalidPassword() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAndPassword("sharangoudah@gmail.com", "asgdgdgdhds@12222");
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginFailedWarningMessage();

	}

	// 3. HW - 3rd test case for loginWithInvalidUsernameAndValidPassword
	@Test(priority =3)
	public void loginWithInvalidUsernameAndValidPassword() {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterEmailAndPassword("sharangoaasssaaaudah@gmail.com", "Pass@123");
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginFailedWarningMessage();

	}

	// 4. HW - 4th test case for loginWithNoCredentials
	@Test(priority = 3)
	public void loginWithNoCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginFailedWarningMessage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
