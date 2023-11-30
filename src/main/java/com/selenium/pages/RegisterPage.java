package com.selenium.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterPage {
	WebDriver driver;
	Properties prop;

	public RegisterPage(WebDriver driver, Properties prop) {
		this.driver = driver;
		this.prop = prop;
	}

	public void registrationDetails(String firstname, String lastname, String regemail, String telephone, String pass) {
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(prop.getProperty("firstname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(prop.getProperty("lastname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("regemail"));
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(prop.getProperty("telephone"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("pass"));
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys(prop.getProperty("pass"));
	}

	public void regWithoutSubscription() {
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}

	public void regWithSubscription() {
		driver.findElement(By.xpath("//input[@type='radio'][@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}

	public void regWithoutPolicyChecker() {
		driver.findElement(By.xpath("//input[@type='radio'][@name='newsletter'][@value='1']")).click();
//		  driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualtext = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		System.out.println("Actual text is:" + actualtext);
		Assert.assertTrue(actualtext.contains("Warning: You must agree to the Privacy Policy!"), "Test failed");
	}

	public void regWithEmptyFields() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String fname = driver.findElement(By.xpath("//div[text()='First Name must be between 1 and 32 characters!']"))
				.getText();
		Assert.assertTrue(fname.contains("First Name must be between 1 and 32 characters!"), "FnameFaled");
		String lname = driver.findElement(By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']"))
				.getText();
		Assert.assertTrue(lname.contains("Last Name must be between 1 and 32 characters!"), "LnameFaled");
		String email = driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']"))
				.getText();
		Assert.assertTrue(email.contains("E-Mail Address does not appear to be valid!"), "EmailFaled");
		String mobilNo = driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']"))
				.getText();
		Assert.assertTrue(mobilNo.contains("Telephone must be between 3 and 32 characters!"), "mobileNo failed");
		String passwd = driver.findElement(By.xpath("//div[text()='Password must be between 4 and 20 characters!']"))
				.getText();
		Assert.assertTrue(passwd.contains("Password must be between 4 and 20 characters!"), "Passwd Failed");
//		String confPass=driver.findElement(By.xpath("//input[@name='confirm']")).getText();
//		Assert.assertTrue(confPass.contains("//input[@name='confirm']"), "confirnFailed");
	}
}
