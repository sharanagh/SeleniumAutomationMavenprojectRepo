package com.selenium.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base1 {

	WebDriver driver;
	Properties prop;
	public Properties readPropData() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\java\\MavenAutoProject\\src\\main\\java\\com\\selenium\\config\\config1.properties");
		prop= new Properties();
		prop.load(fis);
		return prop;
	}
	
	public WebDriver browserInit(String browserName) {
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("No drivers found");
		}
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
}
