package com.qa.gpp.baseTest;
import org.openqa.selenium.WebDriver; import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.qa.gpp.factory.DriverFactory;
import com.qa.gpp.pages.HomePage;
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected HomePage homePage;
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		driver= df.initDriver("chrome");
		homePage = new HomePage(driver);
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
