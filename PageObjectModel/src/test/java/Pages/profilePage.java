package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.testBase;
/*
 * This page is important because it allows the user to update and adjust 
 * specific information like:
 * bio
 * profile picture
 * status updates
 * and keep track of your personal feed
 * this class file variables and methods will help test functionalities to update the users profile
 * 
 */
public class profilePage{

	private WebDriver ldriver;

	public profilePage(WebDriver driver) {           
		this.ldriver = driver; 
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//span[text()='Follow']")
	public
	WebElement FollowButton;
	@FindBy(xpath="//span[text() = 'Set up profile']")
	WebElement updateProfile;
	@FindBy(xpath="//*[name()='path' and contains(@d,'M12.225 12')]")
	WebElement moveToProfile;
	
	
}
