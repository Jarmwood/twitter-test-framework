package Pages;

import base.testBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testCases.baseTestClass;

/*
 * 
 * This class file is important because members who have an account should have access 
 * to their specific account, this class file variables and methods will help test the login capabilities of twitter
 * consisting of:
 * 	-email
 * 	-password
 * 	-login button
 *   implementing the OOPs concept encapsulation
 */

public class loginPage extends testBase {
	WebDriver driver;
	
	@FindBy(name="text")
	public WebElement UserNameField;
	
	@FindBy(name="session[password]")
	public WebElement PasswordField;
	
	@FindBy(css="div[data-testid=LoginForm_Login_Button]")
	public WebElement UserLoginButton;
	
	@FindBy(xpath="//a[@data-testid ='AppTabBar_Home_Link']")
	public WebElement homeProfile;
	
	
	public loginPage(WebDriver driver) {           
		this.driver = testBase.driver;
		PageFactory.initElements(driver,this);
		}
	
	public void login_Twitter(String UserName, String Password) throws InterruptedException {
		WebElement signIn = driver.findElement(By.xpath("//a[@data-testid='loginButton']"));
		signIn.click();
		UserNameField.sendKeys(UserName);
		//log.info("username successfully entered into text box");
		PasswordField.sendKeys(Password);
		//log.info("password successfully entered into text box");
		UserLoginButton.click();
		Thread.sleep(2000);
		if(homeProfile.isDisplayed()) {
			System.out.println("User successfully logged into Twitter account");
			//log.error("User successfully logged into Twitter account");
		}
		
	}
	public void invalidLogin_Twitter(String UserName, String Password) throws InterruptedException {
		UserNameField.sendKeys(UserName);
		//log.info("username successfully entered into text box");
		PasswordField.sendKeys(Password);
		//log.info("password successfully entered into text box");
		UserLoginButton.click();
		//log.info("Log in button successfully clicked");
		Thread.sleep(2000);
		if(!(homeProfile.isDisplayed())) {
			System.out.println("The username password you entered did not match our records. Please double-check and try again.");
		//	log.error("The username and password you entered did not match our records. Please double-check and try again.");
		
		}
	}
}
