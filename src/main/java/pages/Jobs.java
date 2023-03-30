package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class Jobs extends SeleniumWrappers {

	public Jobs(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public By searchJobsField = By.cssSelector("div[class='form-group'] input[id='jobs_filter_search']");
	public By searchJobsButton = By.cssSelector("button[name='jobs-top-filter']");
	
	
	public void findJobs(String searchItem) {
		sendKeys(searchJobsField, searchItem);		
		
		click(searchJobsButton);
		
		waitForElementToBeVisible(driver.findElement(By.xpath("//div[@class='entry-left']//span[@class='result-count' and contains(text(), '" + searchItem + "')]")));
	}
	
	public By getSelectedJob(String searchItem) {
		
		String selected = "//h3[@class='jobs-title']/a[contains(text(), '"+ searchItem +"')]";
		
		return By.xpath(selected.trim());
	}
	
	
}
