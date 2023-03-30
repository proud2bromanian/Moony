package utils;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.google.common.io.Files;


public class BaseTest extends Driver {

	/**
	 * WebDriver object that will be initialised inside the setup method through the initDriver() method inherited from Driver.class
	 */
	public WebDriver driver;
	/**
	 * BasePage object that holds object instantiation for all PageObject classes
	 * it will be the starting point of each call.
	 * Ex: 
	 * app.click(app.loginPage.closeButton)
	 * app.menu.navigateTo(app.menu.contactsLink)
	 */
	public BasePage app;
	/**
	 * Method called before execution of any method in execution class.
	 * Scope is :
	 *  - to initialize the browser based on parameter passed on the @Parameters annotation
	 *  - to do navigation to application url 
	 * @param browser : this indicates the browser on which the tests whould run.Can be passed from any source
	 * @param appUrl : this indicates the application under test URL.Can be passed from any source

	 */
	@Parameters({"browser", "appUrl"})
	@BeforeClass(alwaysRun = true)
	public void setup(String browser, String appUrl) {
		
		//initialize the browser object
		driver = initBrowser(browser);
		//maximize the browser window
		driver.manage().window().maximize();
		//sets the implicit wait. will be applied to all webelements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//navigates to the passed url in the browser window
		driver.get(appUrl);
		//instantiate the BasePage object so we can have acces to all pages directly
		app = new BasePage(driver);
			
	}

	/**
	 * Method call after executions of all methods annotated @Test from a class
	 * Scope is to kill the browser instance
	 */
	@AfterClass
	public void teardown(){
		try {
			Log.info("Called method <teardown>");
			//quits the browser window
			driver.quit();
			Log.info("driver quit sucessfully");
		}catch (Exception e) {
			Log.error("Cannot close the browser");
			Log.error(e.fillInStackTrace());
		}

		
	}
	
	/**
	 * Method call after the execution of each method annotated @Test from a class
	 * Scope is to take a screenshot if the executed status is set to failed!
	 * Screenshot is saved root project folder in folder called 'screenshot'
	 * Makes use of ITestResult listener from testng in order to capture the method status of execution
	 * if execution status equals FAILURE, it will automatically trigger screenshot execution, else will do nothing
	 */
	@AfterMethod
	public void recordFailure(ITestResult result) {
		//checks the status of method execution
		if(ITestResult.FAILURE == result.getStatus()) {
			//instantiate the TakeScreenshot class from selenium
			TakesScreenshot poza = (TakesScreenshot)driver;
			File picture = poza.getScreenshotAs(OutputType.FILE);
			//attempts to take a picture and saves it
			try {
				Files.copy(picture, new File("screenshots/"+result.getName() + ".png"));
				Log.info("Saved picture in 'screenshots/' "+result.getName() + ".png");
				
			}catch (Exception e) {
				Log.error("Could not save picture!");
				Log.error(e.getMessage());
				
			}
			
		}
		
	}
	

}
