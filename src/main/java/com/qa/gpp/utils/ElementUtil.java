package com.qa.gpp.utils;
 
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import com.qa.gpp.exception.FrameworkException;
 
public class ElementUtil {
 
 
	private WebDriver driver;
 
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
 
	public By getBy(String locatorType, String locatorValue) {
		By by = null;
 
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;
 
		default:
			System.out.println("wrong locator type is passed..." + locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}
 
		return by;
 
	}
 
	// locatorType = "id", locatorValue = "input-email", value = "tom@gmail.com"
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}
 
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
 
	public void doClick(By locator) {
		getElement(locator).click();
	}
 
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}
 
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
 
	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}
 
	public String doGetElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
 
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
 
	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}
 
	// WAF : capture the text of all the page links and return List<String>.
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();// pc=0 {}
		for (WebElement e : eleList) {
			String text = e.getText();
 
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
 
	// WAF: capture specific attribute from the list:
	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
 
		List<String> eleAttrList = new ArrayList<String>();// pc=0 {}
 
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttrList.add(attrValue);
		}
 
		return eleAttrList;
 
	}
 
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
 
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
 
	public boolean checkSingleElementPresent(By locator) {
		return driver.findElements(locator).size() == 1 ? true : false;
	}
 
	public boolean checkElementPresent(By locator) {
		return driver.findElements(locator).size() >= 1 ? true : false;
	}
 
	public boolean checkElementPresent(By locator, int totalElements) {
		return driver.findElements(locator).size() == totalElements ? true : false;
	}
 
	public void Search(By searchField, By suggestions, String searchKey, String suggName) throws InterruptedException {
		doSendKeys(searchField, searchKey);
		Thread.sleep(3000);
		List<WebElement> suggList = getElements(suggestions);
 
		System.out.println(suggList.size());
 
		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggName)) {
				e.click();
				break;
			}
		}
	}
 
	public void clikcOnElement(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(eleText)) {
				e.click();
				break;
			}
		}
	}
 
	// ***************Select drop Down Utils***************//
 
	private Select createSelect(By locator) {
		Select select = new Select(getElement(locator));
		return select;
	}
 
	public void doSelectDropDownByIndex(By locator, int index) {
		createSelect(locator).selectByIndex(index);
	}
 
	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		createSelect(locator).selectByVisibleText(visibleText);
	}
 
	public void doSelectDropDownByValue(By locator, String value) {
		createSelect(locator).selectByValue(value);
	}
 
	public int getDropDownOptionsCount(By locator) {
		return createSelect(locator).getOptions().size();
	}
 
	public List<String> getDropDownOptions(By locator) {
		List<WebElement> optionsList = createSelect(locator).getOptions();
 
		List<String> optionsTextList = new ArrayList<String>();
 
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
 
		return optionsTextList;
	}
 
	public void selectDropDownOption(By locator, String dropDownValue) {
 
		List<WebElement> optionsList = createSelect(locator).getOptions();
 
		System.out.println(optionsList.size());
 
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(dropDownValue)) {
				e.click();
				break;
			}
		}
	}
 
	public void selectDropDownValue(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
 
	public boolean isDropDownMultiple(By locator) {
		return createSelect(locator).isMultiple() ? true : false;
	}
}