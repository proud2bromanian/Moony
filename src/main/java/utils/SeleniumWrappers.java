package utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;
/**
 * Class used to wrap the existing selenium methods and enhance them with aditional functionalitie, like retry mechanism
 * automatically wait etc
 * New methods can be added based on existing models
 */

public class SeleniumWrappers {
	
	public WebDriver driver;
	
	public SeleniumWrappers(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Custom sendKeys method/ Wraps selenium default SendKeys and enhance it with :
	 * 1. clear() method before sending the text.
	 * 2. waitForElementToBeVisible(element) before any action on webelement
	 * @param locator --> used inside method to create an WebElement object
	 * @param value --> String value
	 */
	public void sendKeys(By locator, String value) {
		WebElement element =  getElement(locator);
		waitForElementToBeVisible(element);
		try {
			Log.info("called <clear()> on element with locator " + locator.toString());
			element.clear();
			Log.info("Called <sendKeys()> on element with locator " +locator.toString());
			element.sendKeys(value);
			
		}catch(Exception e) {
			Log.error("Element not found in method <SendKeys()>");
			Log.error(e.getMessage());	
			throw new TestException("Element not found in method <SendKeys()> with locator " + locator.toString() );
		}	
	}
	/**
	 * Wrapper method over selenium default click() method enhanced with:
	 * 1 .waitForElementToBeClickable(element) method
	 * 2. retry mechanism in case of StaleElementReferenceException
	 * @param locator
	 */
	public void click(By locator) {
		
		WebElement element =  getElement(locator);
		Log.info("Called method <click()> on locator  " + locator.toString());
		try {			
			waitForElementToBeClickable(element);
			element.click();
			
		}catch(NoSuchElementException e) {
			Log.error("Element not found in method <click()> after 10 seconds");
			Log.error(e.getMessage());	
			throw new TestException("Element not found in method <click()> after 10 seconds");
			
		}catch (StaleElementReferenceException e) {
			Log.error("catch <StaleElementReferenceException> on element " + element.getAttribute("outerHTML"));
			Log.error("Attemting Retry on " + element.getAttribute("outerHTML"));
			element =  getElement(locator);
			element.click();
		}	
	}
	
	/**
	 * Method used to wait for an element to become clickable  Expected Condition elementToBeClickable Element
	 * @param element
	 */
	public void waitForElementToBeClickable(WebElement element) {
		try {
			Log.info("Called <waitForElementToBeClickable> " + element.getAttribute("outerHTML"));
			WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
			
		}catch(NoSuchElementException e) {
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <waitForElementToBeClickable> after 10 seconds");

		}
	}
	
	/**
	 * Method used to wait for an element to be visible based on Expected Condition visibilityOf Element
	 * @param element
	 */
	public void waitForElementToBeVisible(WebElement element) {
		try {
			Log.info("Called <waitForElementToBeVisible> on element " + element.getAttribute("outerHTML"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			
			
		}catch(NoSuchElementException e) {
			Log.error("Element not found in method <waitForElementToBeVisible> after 10 seconds");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <waitForElementToBeVisible> after 10 seconds ");

		}
		
	}
	
	
	/**
	 * Method used to return an webElement after passing a locator as parameter
	 * is used inside other helper methods or in test classes when you need directly an weblement from page object class
	 * but you have only the locator
	 * @param locator
	 * @return webElement
	 */
	public WebElement getElement(By locator) {
		Log.info("called method <getElement> on locator" + locator.toString() );
		waitForElementToBeVisible(driver.findElement(locator));
		WebElement element ;
		try {
			element = driver.findElement(locator) ;
			return element;
		
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new TestException("Cannot find element on <getElement> with locator "+ locator.toString());
			
		}
	}
	
	/**
	 * Method used to return a List of WebElements after passing a locator as parameter
	 * is used inside other helper methods or in test classes when locator cannot be unique
	 * @param locator
	 * @return list<webElement>
	 */
	public List<WebElement> getElementsList(By locator) {
		Log.info("called method <getElementsList> on locator" + locator.toString() );
		List<WebElement> elementList ;
		try {
			elementList = driver.findElements(locator) ;
			return elementList;
		
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new TestException("Cannot find element on <getElement> with locator "+ locator.toString());
			
		}
	}
	
	
	
	
	
	
	/**
	 * Method used to perform drag and drop of an webElement on the x, y axis
	 * Makes use of getWebElement() method who waits for element to be visible
	 * Triggers hover with Selenium Action class
	 * @param locator : webElement locator 
	 * @param xAxis : horizontal move offset.
	 * @param yAxis: vertical move offset.
	 */	
	public void dragAndDrop(By locator, int xAxis, int yAxis) {
		Log.info("Called method <dragAndDrop>");
		try {
			Log.info("call getWebElement on locator " + locator.toString());
			WebElement element = getElement(locator);
			Actions action = new Actions(driver);
			Log.info("attempting to drag and drop element with locator" +  locator.toString());
			action.dragAndDropBy(element,xAxis, yAxis).perform();
		}catch(Exception e) {
			Log.error(e.getMessage());
			throw new TestException("Could not drag and drop element with locator "+ locator.toString());
		}
	}
	
	/**
	 * Method used to hover over an webElement
	 * Makes use of getWebElement() method who waits for element to be visible
	 * Triggers hover with Selenium Action class
	 * @param locator
	 */
	public void hoverElement(By locator) {	
		Log.info("Called method <hoverElement>");
		try {
			Log.info("call getWebElement on locator " + locator.toString());
			WebElement element = getElement(locator);
			Actions action = new Actions(driver);
			Log.info("attempting to hover element with locator" +  locator.toString());
			action.moveToElement(element).perform();	
		}catch(Exception e) {
			Log.error(e.getMessage());
			throw new TestException("Could not hover element with locator "+ locator.toString());
		}
	}
	/**
	 * Wrapper method over explict wait. Can be called directly when an webelement is needed to be waited for
	 * @param locator
	 */
	public void explicitWaitFor(By locator) {
		Log.info("called method explicitWaitFor() on locator " + locator.toString());
		WebElement element = getElement(locator);
		try {
			WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			Log.error(e.getMessage());
			throw new TestException("Element was not present after explicitWait for condition visibilityOf() with locator : "+ locator.toString());
		}
	}
	/**
	 * Wrapper method over the isDisplayed() method from selenium, enhanced with explicitWait
	 * @param locator
	 * @return boolean state of element checked
	 */
	public boolean isElementDisplayed(By locator) {
		Log.info("called method isElementDisplayed() on locator " + locator.toString());
		try {
			WebElement element =  getElement(locator);
			explicitWaitFor(locator);
			return element.isDisplayed();
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new TestException("called method failed on isElementDisplayed() on locator  : "+ locator.toString());		}
	}

	/**
	 * Wrapper method over the getText() method from selenium, enhanced with explicitWait
	 * @param locator
	 * @return element text inside inner html
	 */
	public String getElementText(By locator) {
		Log.info("called method getElementText() on locator " + locator.toString());
		try {
			WebElement element =  getElement(locator);
			explicitWaitFor(locator);
			return element.getText();
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new TestException("called method failed on getElementText() on locator  : "+ locator.toString());		}
	}
	
	
	
	
	
	
	//comment for jenkins

}
