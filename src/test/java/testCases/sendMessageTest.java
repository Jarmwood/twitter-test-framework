package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import Pages.explorePage;
import Pages.loginPage;
import Pages.messagesPage;
import Pages.profilePage;
import base.testBase;

public class sendMessageTest extends baseTestClass {
	WebDriver driver;
@Test(dataProvider = "getTestData")
	public void SendMessageTestCase(String UserName,String Password,String NewTweet,String UpdateProfile,String SendMessage,String MessageRecipient) {
		setUp();
		startBrowser("chrome","https://www.Twitter.com");
		Config.getProperty("implicit.wait");
		loginPage login = new loginPage(driver);
		
				//logging in
				login.UserNameField.sendKeys(UserName);
				log.info("username successfully entered into text box");
				login.PasswordField.sendKeys(Password);
				log.info("password successfully entered into text box");
				login.UserLoginButton.click();
				Config.getProperty("explicit.wait");

		
		explorePage explore = new explorePage(driver);

		explore.ExploreSearch.click();
		explore.ExploreSearch.sendKeys("john doe");
		explore.SearchedUserList.click();
		
		profilePage profile = new profilePage(driver);

		profile.FollowButton.click();
		
		messagesPage message = new messagesPage(driver);
		
		message.messagePageButton.click();
		message.newMessage.click();
		message.messageToRecipient.sendKeys("");// who is the message being sent to?
		
		
	}

@DataProvider
public Object[][] getTestData() throws EncryptedDocumentException, IOException{
	
	Object [][] testData = excel.readExcelData("TwitterData");
	
	return testData;
}}