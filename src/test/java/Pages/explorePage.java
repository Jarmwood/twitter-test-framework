package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This page class will help me test the sendMessage test class
 *  by implementing these elements to find other Twitter users.
 *  This functionality is vital because it allows members to be social
 *  
 */

public class explorePage {


	private WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Search Twitter']")
	public WebElement ExploreSearch;

	@FindBy(xpath = "//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']\"));\r\n")
	public WebElement SearchedUserList;


	public explorePage(WebDriver driver) {           
		this. driver = driver; 
		PageFactory.initElements(driver, this);
		}
	


}

	

