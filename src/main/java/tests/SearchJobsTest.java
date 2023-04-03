package tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NavMenuPage;
import utils.BaseTest;
import utils.TestNgListener;

//@Listeners(TestNgListener.class)
public class SearchJobsTest extends BaseTest {
	
	
	@Test ()
	public void loginTest() {
		
		app.click(app.menu.loginLink);
		app.loginPage.loginInApp("johnnybravo@key-training.ro", "johnnybravo@123");
		app.explicitWaitFor(app.menu.topMenu);		
		app.click(app.menu.findJobsLink);		
		app.jobs.findJobs("Test Engineer");
		app.click(app.jobs.getSelectedJob("Test Engineer at KeyTest"));
		assertTrue(app.getElement(app.selectedJobs.applyButton).isDisplayed());
		assertTrue(app.getElement(app.selectedJobs.companySection).isDisplayed());
		app.click(app.selectedJobs.applyButton);
		assertTrue(app.getElement(app.selectedJobs.applyPopup).isDisplayed());
		app.click(app.selectedJobs.phoneApply);
		//driver.switchTo().alert().accept();
		
	}
	
	


}
