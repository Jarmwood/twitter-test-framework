package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.loginPage;
import base.testBase;

/*
 * 
 *  testing the functionalities of logging in with valid and invalid input 
 *  to ensure users are able to log in with their specific credentials and are connected to their account 
 *   
 * 
 */
public class LoginTest extends testBase {
	WebDriver driver;

	loginPage login = new loginPage(driver);



	@Test(dataProvider = "getTestData")
	public void VerifyValidLogin(String UserName, String Password, String NewTweet, String UpdateProfile,String SendMessage, String MessageRecipient) throws InterruptedException, IOException {

		startBrowser("chrome","https://www.Twitter.com");
		login.login_Twitter(UserName, Password);
		tearDown();
	}
	
	
	@Test(dataProvider = "getTestData")
	public void VerifyInvalidLogin(String UserName, String Password, String NewTweet, String UpdateProfile,String SendMessage, String MessageRecipient) throws InterruptedException, IOException {
		startBrowser("chrome","https://www.Twitter.com");
		login.invalidLogin_Twitter(UserName, Password);
		tearDown();
	}

	@DataProvider
	public Object[][] getTestData() throws EncryptedDocumentException, IOException {

		Object[][] testData = excel.readExcelData("TwitterData");

		return testData;
	}
}