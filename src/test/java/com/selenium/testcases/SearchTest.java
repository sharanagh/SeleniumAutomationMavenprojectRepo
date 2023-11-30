package com.selenium.testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void startChromeBrowser() throws IOException {
		prop = readingDataFromPropFile();
		driver = browserInitiolizationAndLaunchUrl(prop.getProperty("browserName"));
	}

	@Test(priority = 1)
	public void searchWithValidProduct() {
		SearchPage searchPage = new SearchPage(driver, prop);
		searchPage.verifySearchWithSuccess("validProduct");
		/* searchPage.clickSearchOption(); */
	}

	@Test(priority =2)
	public void searchWithInvalidProduct() {
		SearchPage searchPage = new SearchPage(driver, prop);
		searchPage.verifySearchFailedWarning(prop.getProperty("inValidProduct"));
		/* searchPage.clickSearchOption(); */
	}
	
	@Test(priority = 3, dependsOnMethods = {"searchWithInvalidProduct"})
	public void searchWithoutProduct() {
		SearchPage searchPage = new SearchPage(driver, prop);
		searchPage.verifySearchFailedWarning("");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
