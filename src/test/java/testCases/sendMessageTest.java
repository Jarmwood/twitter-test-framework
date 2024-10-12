package testCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.explorePage;
import Pages.loginPage;
import Pages.messagesPage;
import Pages.profilePage;
import utilities.PropertiesConfigurations;

import static utilities.testEngine.excel;
import static utilities.testEngine.startBrowser;

public class sendMessageTest{
	WebDriver driver;
@Test(dataProvider = "getTestData")
	public void SendMessageTestCase(String UserName,String Password,String NewTweet,String UpdateProfile,String SendMessage,String MessageRecipient) {

	startBrowser("https://www.Twitter.com");
    PropertiesConfigurations.Config.getProperty("implicit.wait");
    loginPage login = new loginPage(driver);

    login.UserNameField.sendKeys(UserName);
    log.info("username successfully entered into text box");
    login.PasswordField.sendKeys(Password);
    log.info("password successfully entered into text box");
    login.UserLoginButton.click();
    PropertiesConfigurations.Config.getProperty("explicit.wait");

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
    return excel.readExcelData("TwitterData");
}}