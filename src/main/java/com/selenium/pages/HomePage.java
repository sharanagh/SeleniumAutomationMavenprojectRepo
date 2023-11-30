package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickOnMyAccount() {
		driver.findElement(By.xpath("//span[text()= 'My Account']")).click();
	}
	
	public void selectLoginOption() {
		driver.findElement(By.linkText("Login")).click();
	}
}
