package testCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.loginPage;
import Pages.profilePage;
import base.testBase;

public class updateProfileTestCase extends testBase {
	@Test(dataProvider = "getTestData")
	public void UpdateProfileTestCase(String UserName, String Password, String NewTweet, String UpdateProfile,String SendMessage, String MessageRecipient) {


//		loginPage login = new loginPage(driver);
//		Config.getProperty("explicit.wait");
//		login.getUserNameField().sendKeys(UserName);
//		log.info("username successfully entered into text box");
//		login.getPassWordField().sendKeys(Password);
//		log.info("password successfully entered into text box");
//		login.getUserlogInButton().click();
//		log.info("User has successfully logged in");
//		profilePage profile = new profilePage(driver);
//		//click on profile icon
//		profile.getMoveToProfile().click();
//		//click on update profile
//		click("updateProfile_XPATH");
//
//		//enter bio  into text box
//		enterText("bioTextBox_XPATH",UpdateProfile);
//		//click save button
//		click("saveBio_XPATH ");
	}

	@DataProvider
	public Object[][] getTestData() throws EncryptedDocumentException, IOException {

		Object[][] testData = excel.readExcelData("TwitterData");

		return testData;
	}
}
