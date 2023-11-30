package com.selenium.testcases;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.selenium.base.Base1;

public class LoginTwo extends Base1 {

	public WebDriver driver;
	Properties prop;
	@Test
	public void startBrowser() throws IOException {
		prop=readPropData();
		driver=browserInit(prop.getProperty("BrowserName"));
		
		driver.findElement(By.xpath("//span[text()= 'My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
}
