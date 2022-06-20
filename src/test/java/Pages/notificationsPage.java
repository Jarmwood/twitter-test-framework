package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.testBase;
/*
 * this page will be utilized to verify the testing of the "send message test"
 
 * communication is an important aspect of the application and can be terrible if issues arise
 * and user also receives updates in the notifications hub 
 * such as profile security updates
 * 
 */
public class notificationsPage {
	
	@FindBy(xpath="//span[contains(text(),'Notifications')]")
	WebElement showNotifications;
	
		
	}


