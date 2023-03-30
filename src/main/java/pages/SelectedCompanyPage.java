package pages;

import java.util.List;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestException;

import utils.Log;
import utils.SeleniumWrappers;

public class SelectedCompanyPage extends SeleniumWrappers{

	public SelectedCompanyPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public By infoField = By.cssSelector("div[class*='jobs-company-sidebar']");
	public By infoCategoriesList = By.cssSelector("div[class='list-cate'] a[class*='civi-link-bottom']");
	public By companySizeInfo = By.xpath("//p[contains(text(),'Company size')]//following-sibling::div");
	public By foundedYearInfo = By.xpath("//p[contains(text(),'Founded in')]//following-sibling::p");
	public By locationInfo = By.xpath("//p[contains(text(),'Location')]//following-sibling::p");
	
	
	public String getInfoCategoriesListText(By locator) {
		Log.info("called method <getElementsList> on locator" + locator.toString() );
		List<WebElement> elementList ;
		
		try {
			elementList = driver.findElements(locator) ;
			String elementsText = "";
			
			for ( int i = 0; i < elementList.size(); i++){
				 	
				elementsText = (elementsText+ "  " + elementList.get(i).getText()).trim();
				
			}
			return elementsText;
		
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new TestException("Cannot find element on <getElement> with locator "+ locator.toString());
			
		}
		
	}
	
	
	
	
}
