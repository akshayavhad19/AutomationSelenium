package com.qa.gpp.factory;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.gpp.exception.FrameworkException;

public class DriverFactory {
	public static String highlight;
	WebDriver driver;
	public WebDriver initDriver(String browserName) {
		System.out.println("The browser name is:" +browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break; 
		case "firefox":
			driver = new FirefoxDriver();
			break; 
		case "edge":
			driver = new EdgeDriver();
			break; 
		case "safari":	
			driver = new SafariDriver();	
			break; 
		default:
			System.out.println("Please pass right browser:"+browserName);
			throw new FrameworkException("No browser found");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = "develop";
		
		//Set the password
		String password = "bi!marketing";
		
		
		String URL = "https://" +username +":" +password +"@"+ "nginx-gpphiroba-jp-develop.bi3.bi-oneweb.com";
		driver.get(URL);
		return driver;
	}
}
