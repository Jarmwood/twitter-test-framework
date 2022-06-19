package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testCases.baseTestClass;

/*
 * This class page is important because it allows a user to add messages 
 * to their message board and if they can't then there's a issue 
 * and the 'Home Page' acts as a central hub easily allowing users to navigate between pages.
 * this class file variables and methods will help test new tweet test case
 */


public class homePage extends  baseTestClass{
	//declare driver
	WebDriver driver;
	
	//set your path to your webelements 
	@FindBy(xpath = "//div[contains(@class, 'public-DraftStyleDefault')]")
	public WebElement newTweet;
	
	@FindBy(xpath="//span[contains(text(), 'Tweet')][1]")
	public WebElement Tweet_center;
	
	@FindBy(xpath="//a[@data-testid ='AppTabBar_Home_Link']")
	public WebElement homeProfile;

	//create page constructor to init webelements and driver
	public homePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//create methods to be used in your testcases preferably use method chaining for more readability
	public homePage grabPageHeader() {
		driver.getTitle();
		return new homePage(driver);
	}
	
}
