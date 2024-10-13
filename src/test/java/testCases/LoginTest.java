package testCases;

import java.io.IOException;
import Pages.loginPage;
import com.twitter.extentmanager.ExtentManager;
import com.twitter.extentmanager.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.testEngine;

/*
 *  testing the functionalities of logging in with valid and invalid input 
 *  to ensure users are able to log in with their specific credentials and are connected to their account
 */

public class LoginTest {
	WebDriver driver;
	loginPage login;

	@BeforeClass
	public void startDriver(){
		ExtentTestManager.startTest("********** Login page Tests Started Running ***********");
		login = new loginPage();
		driver = testEngine.getDriver();
		login.goToPage();
	}

	@Test
	public void VerifyValidLogin(
			String UserName,
			String Password,
			String NewTweet,
			String UpdateProfile,
			String SendMessage,
			String MessageRecipient
	) throws InterruptedException, IOException {
		login.login_Twitter(UserName, Password);
		//login.tearDown();
	}
	

	@Test(dataProvider = "getTestData")
	public void VerifyInvalidLogin(
			String UserName,
			String Password,
			String NewTweet,
			String UpdateProfile,
			String SendMessage,
			String MessageRecipient
	) throws InterruptedException, IOException {
		login.invalidLogin_Twitter(UserName, Password);
	//	tearDown();
	}

	@Test
	public void navigateToApplication(){
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.equals("twitter"));
	}
}