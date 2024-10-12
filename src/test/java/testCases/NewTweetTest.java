package testCases;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pages.loginPage;
import utilities.PropertiesConfigurations;
import utilities.testEngine;


public class NewTweetTest{
WebDriver driver;

	@Test(dataProvider = "getTestData")
	public void newTweetTestCase(String UserName,String Password,String NewTweet,String UpdateProfile,	String SendMessage,	String MessageRecipient) {
		setUp();
		testEngine.startBrowser("https://www.Twitter.com");
		
		loginPage login = new loginPage(driver);
		PropertiesConfigurations.Config.getProperty("explicit.wait");

//		login.UserNameField.sendKeys(UserName);
//		log.info("username successfully entered into text box");
//		login.PasswordField.sendKeys(Password);
//		log.info("password successfully entered into text box");
//		login.UserLoginButton.click();
//		Config.getProperty("explicit.wait");
//		
//		homeProfile.click();
//		newTweet.click();
//		newTweet.sendKeys(NewTweet);
//		Tweet_center.click(); 
//		log.info("user created a new 'Tweet'");
		//home.getHomeProfile().click();
		
		
	}
		
  
@DataProvider
public Object[][] getTestData() throws EncryptedDocumentException, IOException{
	
	Object [][] testData = excel.readExcelData("TwitterData");
	
	return testData;
}}
	
	

