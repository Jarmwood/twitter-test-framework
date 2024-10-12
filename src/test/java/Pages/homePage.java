package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This class page is important because it allows a user to add messages 
 * to their message board and if they can't then there's an issue
 * and the 'Home Page' acts as a central hub easily allowing users to navigate between pages.
 * this class file variables and methods will help test new tweet test case
 */


public class homePage {
	WebDriver driver;
	
	//set your path to your elements
	@FindBy(xpath = "//div[contains(@class, 'public-DraftStyleDefault')]")
	public WebElement newTweet;
	
	@FindBy(xpath="//span[contains(text(), 'Tweet')][1]")
	public WebElement Tweet_center;
	
	@FindBy(xpath="//a[@data-testid ='AppTabBar_Home_Link']")
	public WebElement homeProfile;


	public homePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public homePage grabPageHeader() {
		driver.getTitle();
		return new homePage(driver);
	}
	
}
