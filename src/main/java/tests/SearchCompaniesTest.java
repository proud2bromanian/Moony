package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NavMenuPage;
import utils.BaseTest;
import utils.TestNgListener;

//@Listeners(TestNgListener.class)
public class SearchCompaniesTest extends BaseTest {
	
	@Parameters("searchItem")
	@Test ()
	public void SearchTest(String searchItem) throws InterruptedException  {
		
		app.click(app.menu.companiesLink);
		app.companies.searchCompanies(searchItem);	
	    app.click(app.companies.companyLink);
	    app.explicitWaitFor(app.selectedCompany.infoField);
		
	    assertEquals(app.getElementText(app.selectedCompany.companySizeInfo), "200-300");
	    assertEquals(app.getElementText(app.selectedCompany.foundedYearInfo), "2017");
	    assertEquals(app.getElementText(app.selectedCompany.locationInfo), "San Francisco");	   
	    assertEquals(app.selectedCompany.getInfoCategoriesListText(app.selectedCompany.infoCategoriesList), "B2B SaaS  Web Development");
	    
	}
	

}
