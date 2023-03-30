package utils;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Class used for Driver configuration. Uses ThreadLocal class for paralel execution.
 * The WebDriver objects it`s instantiated through ThreadLocal class to ensure thread safety
 * The browser to be instantiated is decided through method parameter that will be provided on method call
 * Ex: in BaseTest class, the method initDriver() is called and browser is passed as parameter from test-ng.xml
 */
public class Driver {
	/**
	 * ThreadLocal object of WebDriver for paralel execution scope
	 */
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	/**
	 * Browser configuration method. Takes browser as parameter and makes the driver setup through WebDriverManager dependency (pom.xml)
	 * @param browser (currently setup with Google Chrome, Mozzila Firefox and Microsoft Edge)
	 * New browsers can be added on the existing model
	 * @return browser instance
	 * browser configuration is set through the option methods (getChromeOptios, getFirefoxOptions, getEdgeOptions)
	 */
	public WebDriver initBrowser(String browser) {
	
		if(browser.equalsIgnoreCase("chrome")) {
			try {
				Log.info("Browser parameter is : CHROME");
				Log.info("Attempting to setup chrome");
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver(getChromeOptions()));
				//writes to log the thread ID of current instance of browser
				long chromeId = Thread.currentThread().getId();
				Log.info("Chrome --> Thread id = " + chromeId);
				return driver.get();
	
			}catch (Exception e) {
				Log.error("Chrome could not be setup!");
				Log.error(e.getMessage());
			}
			
		}else if(browser.equalsIgnoreCase("firefox")) {
			try {
				Log.info("Browser parameter is : FIEFOX");
				Log.info("Attempting to setup firefox");
				WebDriverManager.firefoxdriver().setup();
				driver.set(new FirefoxDriver(getFirefoxOptions()));
				//writes to log the thread ID of current instance of browser
				long firefoxID = Thread.currentThread().getId();
				Log.info("Firefox --> Thread id = " + firefoxID);
				return driver.get();
			}catch(Exception e) {
				Log.error("Firefox could not be setup!");
				Log.error(e.getMessage());
			}

			
		}else if(browser.equalsIgnoreCase("edge")) {
			try {
				Log.info("Browser parameter is : Microsoft EDGE");
				Log.info("Attempting to setup Edge");
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver(getEdgeOptions()));
				//writes to log the thread ID of current instance of browser
				long edgeID = Thread.currentThread().getId();
				Log.info("Edge --> Thread id = " + edgeID);
				return driver.get();
			}catch(Exception e) {
				Log.error("Microsoft Edge could not be setup!");
				Log.error(e.getMessage());
			}

		}
				
		return driver.get();
		
	}
	/**
	 * Method used for specific Chrome browser configuration.
	 * It is being used when the browser is setup in the initBrowser() method
	 * @return option object that is used inside driver initialisation
	 * Ex : driver.set(new ChromeDriver(getChromeOptions()));
	 * Full list of chrome arguments  :
	 * https://peter.sh/experiments/chromium-command-line-switches/
	 */
	public static ChromeOptions getChromeOptions() {
		ChromeOptions chromeOption = new ChromeOptions();
		//chromeOption.addArguments("--disable-infobars");
		//chromeOption.addArguments("--disable-gpu");
		//chromeOption.addArguments("--disable-dev-shm-usage");
		//chromeOption.addArguments("--no-sandbox");
		//chromeOption.addArguments("--disable-extensions");
		//chromeOption.addArguments("--headless");
		//chromeOption.addArguments("--window-size=1580, 1280");
		chromeOption.addArguments("--remote-allow-origins=*");
		return chromeOption;
	}
	/**
	 * Method used for specific Firefox browser configuration.
	 * It is being used when the browser is setup in the initBrowser() method
	 * @return option object that is used inside driver initialisation
	 * Ex : driver.set(new FirefoxDriver(getFirefoxOptions()));
	 */
	public static FirefoxOptions getFirefoxOptions() {
		 FirefoxBinary firefoxBinary =  new FirefoxBinary();
         firefoxBinary.addCommandLineOptions("--headless");
         FirefoxOptions firefoxOptions =  new FirefoxOptions();
         firefoxOptions.setBinary(firefoxBinary);

		return firefoxOptions;
		
	}
	/**
	 * Method used for specific Edge browser configuration.
	 * It is being used when the browser is setup in the initBrowser() method
	 * @return option object that is used inside driver initialisation
	 * Ex : driver.set(new EdgeDriver(getEdgeOptions()));
	 */
	public static EdgeOptions getEdgeOptions() {
		EdgeOptions edgeOption = new EdgeOptions();
		edgeOption.addArguments("--disable-infobars");
		edgeOption.addArguments("--disable-gpu");
		edgeOption.addArguments("--disable-dev-shm-usage");
		edgeOption.addArguments("--no-sandbox");
		edgeOption.addArguments("--disable-extensions");
	//	edgeOption.addArguments("--headless");
		edgeOption.addArguments("--window-size=1580, 1280");
		
		return edgeOption;
	}
	
}
