package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.testBase;

public class messagesPage  {
	private WebDriver ldriver;

	public messagesPage(WebDriver driver) {           
		this.ldriver = driver; 
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//*[name()='path' and contains(@d,'M11.55 12.')]")
	public WebElement messagePageButton;
	
	@FindBy(xpath ="//a[@class='css-4rbku5 css-18t94o4 css-1dbjc4n r-1niwhzg r-42olwf r-sdzlij r-1phboty r-rs99b7 r-1loqt21 r-1w2pmg r-1vuscfd r-53xb7h"
			+ " r-1ny4l3l r-mk0yit r-o7ynqc r-6416eg r-lrvibr']//div[@class='css-901oao r-1awozwy "
			+ "r-13gxpu9 r-6koalj r-18u37iz r-16y2uox r-1qd0xha r-a023e6 r-vw2c0b r-1777fci r-eljoum r-dnmrzs r-bcqeeo"
			+ " r-q4m81j r-qvutc0']//*[local-name()='svg']")
	
	public WebElement newMessage ;
	
	@FindBy(xpath ="//input[@placeholder='Search people']")
	public WebElement messageToRecipient;
	
}
