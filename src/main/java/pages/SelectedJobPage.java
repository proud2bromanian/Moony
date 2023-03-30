package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class SelectedJobPage extends SeleniumWrappers {

	public SelectedJobPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public By applyButton = By.cssSelector("div[class*='jobs-apply-sidebar'] a");
	public By companySection = By.xpath("//div[contains(@class, 'jobs-company-sidebar') and div[contains(@class, 'company-header')]]");
	public By applyPopup = By.cssSelector("div[class='apply-popup']");
	public By phoneApply = By.cssSelector("div[class='apply-popup'] a");
}
