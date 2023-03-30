package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.BasePage;
import utils.SeleniumWrappers;

public class Companies extends SeleniumWrappers {

	public Companies(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//public String searchItem;
	
	public By searchCompaniesField = By.cssSelector("input[class*='company-search-control']");
	public By searchCompaniesButton= By.cssSelector("button[name='company-top-filter']");
	public By companyLink = By.xpath("//h2[@class='company-title']");
	public By searchedInfo=By.xpath("//span[@class='result-count']");
	
	
	public void searchCompanies(String searchItem)  {	
		sendKeys(searchCompaniesField, searchItem);		
		
		click(searchCompaniesButton);
		
		waitForElementToBeVisible(driver.findElement(By.xpath("//div[@class='entry-left']//span[@class='result-count' and contains(text(), '" + searchItem + "')]")));
		
	}
	
	
	
	
}
