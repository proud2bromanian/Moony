package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.SeleniumWrappers;

public class NavMenuPage extends SeleniumWrappers{
	
	public NavMenuPage(WebDriver driver) {
		super(driver);
		
	}
	
	//locatori
	
	public By loginLink = By.linkText("Login");	
	public By topMenu = By.xpath("//div[contains(@class, 'container') and div[contains(@class, 'row')]]");
	public By findJobsLink = By.xpath("//div[@class='menu-item-wrap']/span[contains(text(), 'Find jobs')]");
	public By companiesLink = By.linkText("Companies");
	
	public By searchIcon = By.cssSelector("button[class*='btn-search-horizontal']");
	public By searchField = By.cssSelector("input[class*='search-horizontal-control']");
		
	
	public void navigateTo(By locator) {
		driver.findElement(locator).click();
	}
	
	public void searchJobs(String value) {
		click(searchIcon);
		sendKeys(searchField, value);
		click(searchIcon);
	}
	
	

	
	
	
	
}
