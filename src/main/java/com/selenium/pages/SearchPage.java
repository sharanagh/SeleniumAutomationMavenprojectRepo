package com.selenium.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchPage {
	WebDriver driver;
	Properties prop;

	public SearchPage(WebDriver driver,Properties prop) {
		this.driver=driver;
		this.prop=prop;
	}

	public void verifySearchWithSuccess(String abc) {
		driver.findElement(By.name("search")).sendKeys(prop.getProperty("validProduct"));
		driver.findElement(By.xpath("//button[@type='button']/i[@class='fa fa-search']")).click();
		String actualText = driver.findElement(By.linkText("HP LP3065")).getText();
		Assert.assertTrue(actualText.contains("HP LP3065"), "Product is not displayed");
		System.out.println(actualText);
	}
	
	/*
	 * public void clickSearchOption() { driver.findElement(By.
	 * xpath("//button[@type='button']/i[@class='fa fa-search']")).click(); }
	 */
	
	public void verifySearchFailedWarning(String inValidProduct) {
		driver.findElement(By.name("search")).sendKeys(inValidProduct);
		driver.findElement(By.xpath("//button[@type='button']/i[@class='fa fa-search']")).click();
		String actualText = driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).getText();
		Assert.assertTrue(actualText.contains("ABCD"),"Prduct is found");
		System.out.println(actualText);
	}
}
