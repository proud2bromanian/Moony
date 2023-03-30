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
	
	@Parameters({"user", "pass"})
	@Test ()
	public void loginTest(String username, String parola) {
		
		app.click(app.menu.loginLink);
		app.loginPage.loginInApp(username, parola);
		//assertTrue(app.loginPage.loginSucessMessageIsDisplayed());
		//app.waitForElementToBeVisible(driver.findElement(app.menu.topMenu));
		app.explicitWaitFor(app.menu.topMenu);
		app.explicitWaitFor(By.cssSelector("h2[class='entry-title']"));
		driver.findElement(app.menu.findJobsLink).click();		
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
