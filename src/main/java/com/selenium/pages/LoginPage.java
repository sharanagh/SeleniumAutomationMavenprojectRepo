package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterEmailAndPassword(String email, String password) {
		driver.findElement(By.xpath("//input[@name= 'email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name= 'password']")).sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		driver.findElement(By.xpath("//input[@value= 'Login']")).click();
	}
	
	public void verifyLoginFailedWarningMessage() {
		String actualText = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		System.out.println(actualText);
		Assert.assertTrue(actualText.contains("Warning: No match for E-Mail Address and/or Password."),
				"loginWithValidUsernameAndInvalidPassword test case failed");
	}
	
	public void verifyLoginPageSuccessfulOrNot() {
		WebElement elm = driver.findElement(By.linkText("Edit your account information"));
		if (elm.isDisplayed()) {
			System.out.println("Login success:" + elm.isDisplayed());
		} else {
			System.out.println("Login Failed:" + elm.isDisplayed());
		}
		String actualText = driver.findElement(By.linkText("Edit your account information")).getText();
		String expectedText = "Edit your account information";
		Assert.assertEquals(actualText, expectedText, "loginWithValidUserNamePassword test case failed");
		System.out.println("Actual Text:" + actualText);
		System.out.println("Expected Text:" + expectedText);
	}
}
