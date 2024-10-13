package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import Pages.explorePage;
import Pages.loginPage;
import Pages.messagesPage;
import Pages.profilePage;
import utilities.PropertiesConfigurations;

public class sendMessageTest{
	WebDriver driver;

    @Test(dataProvider = "getTestData")
	public void SendMessageTestCase(String UserName,String Password,String NewTweet,String UpdateProfile,String SendMessage,String MessageRecipient) {

    PropertiesConfigurations.Config.getProperty("implicit.wait");
    loginPage login = new loginPage();

    login.UserNameField.sendKeys(UserName);
    login.PasswordField.sendKeys(Password);
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
    message.messageToRecipient.sendKeys("");
		
		
	}
}