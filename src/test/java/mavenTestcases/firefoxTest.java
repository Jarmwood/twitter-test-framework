package mavenTestcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import listeners.TestListeners;
@Listeners(TestListeners.class)
public class firefoxTest {
	 
	
	@Test
	public static void validateGoogleTitle_fireFox() {
		WebDriver driver1;
		driver1 = new FirefoxDriver();
		SoftAssert softassert = new SoftAssert();
		driver1.get("http://www.google.com");
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualTitle = driver1.getTitle();
		String expectedTitle = "Google";
		softassert.assertEquals(actualTitle,expectedTitle,"Google.com title was not as expected");
		System.out.println("google title firefox test complete");
		driver1.quit();
		softassert.assertAll();
	}
	@Test
	public static void validateYahooTitle_fireFox() {
		WebDriver driver2;
		driver2 = new FirefoxDriver();
		SoftAssert softassert = new SoftAssert();
		driver2.get("http://www.Yahoo.com");
		driver2.manage().window().maximize();
		driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualTitle = driver2.getTitle();
		String expectedTitle = "Yahoo!";
		softassert.assertEquals(actualTitle,expectedTitle,"Yahoo.com title was not as expected");
		System.out.println("yahoo title firefox test complete");
		driver2.quit();
		softassert.assertAll();
	}
	
	public static void main(String[] args) {
		

	}

}
