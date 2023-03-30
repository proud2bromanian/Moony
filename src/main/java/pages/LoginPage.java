package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import utils.SeleniumWrappers;

public class LoginPage extends SeleniumWrappers {
	
	
	public LoginPage(WebDriver driver) {
		super(driver);

	}

	public By usernameField = By.cssSelector("input[id='ip_email']"); 
	public By passwordField = By.cssSelector("input[id='ip_password']");
	public By submitButton = By.cssSelector("form[id='ux-login'] button[class*='gl-button']");
	
	public By loginErrorMessage = By.cssSelector("p[class*='text-error']");
	public By loginSuccessMessage = By.cssSelector("//form[@id='ux-login']/p[contains(text(), 'Login success')]");
	
	
	
	public By closePopUpButton =  By.cssSelector("div[class*='inner-popup'] a[class='btn-close']");
	
	
	public void closeLoginPopUp() {
		click(closePopUpButton);
	}
	
	
	public void loginInApp(String username, String password) {	
		sendKeys(usernameField, username);
		sendKeys(passwordField, password);
		click(submitButton);
	
	}
	

	
	
	

}
