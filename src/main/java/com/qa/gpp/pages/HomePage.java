package com.qa.gpp.pages;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class HomePage {
 
	private WebDriver driver;
	
	//BY locators: Page Object Repo
	
    private By userName=By.id("");
    private By password=By.id("");
    private By loginBtn=By.linkText("");
	private By logo = By.cssSelector("div[class='logo-holder'] a[aria-label='home page of [site:name]']");
	
	//Page constructor
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	// page actions/ methods
	

	public String getHomePageTitle() {
		String title = driver.getTitle();
		System.out.println("HomePageTitle:"+title);
		return title;	
	}
	public boolean isLogoExist() {
		return driver.findElement(logo).isDisplayed();
	}
	public void doLogin(String username, String Password) {
		driver.findElement(userName).sendKeys(username);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(loginBtn).click();
		System.out.println("User loggedIn");
		
	}
	
}
