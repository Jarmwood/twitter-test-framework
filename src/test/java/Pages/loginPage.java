package Pages;




import base.testBase;
import com.aventstack.extentreports.Status;
import com.twitter.extentmanager.ExtentManager;
import com.twitter.extentmanager.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


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
	WebElement UserNameField;
	
	@FindBy(name="session[password]")
	WebElement PasswordField;
	
	@FindBy(xpath="//a[@data-testid=loginButton]")
	WebElement signIn;
	
	@FindBy(xpath="//a[@data-testid ='AppTabBar_Home_Link']")
	WebElement homeProfile;

	@FindBy(xpath="//div[@data-testid='LoginForm_Login_Button']")
	WebElement UserLoginButton;
	public loginPage(WebDriver driver) {           
		this.driver = driver; 	
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
		}

		public loginPage signinUser(){
			signIn.click();
			ExtentTestManager.getTest().log(Status.PASS,"User clicked on the 'Sign In' button");
			return this;
		}

		public loginPage sendPassword(String passwordInput){
			PasswordField.sendKeys(passwordInput);
			ExtentTestManager.getTest().log(Status.PASS,"User typed: "+passwordInput+" into the password field");
			return this;
		}
		public loginPage sendUsername(String usernameInput){
			UserNameField.sendKeys(usernameInput);
			return this;
		}
		public HomePage clickLoginButton(){
			UserLoginButton.click();
			ExtentTestManager.getTest().log(Status.PASS,"User clicked on the 'Login' button");
			return new HomePage(driver);
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
