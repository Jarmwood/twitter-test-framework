package mavenTestcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import listeners.TestListeners;
//@Listeners(TestListeners.class)
public class chromeTest {
	
	
	@Test
	public static void validateGoogleTitle() {
		WebDriver driver1;
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverExecutables\\chromedriver.exe");
		driver1 = new ChromeDriver();
		SoftAssert softassert = new SoftAssert();
		driver1.get("http://www.google.com");
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualTitle = driver1.getTitle();
		String expectedTitle = "Google";
		softassert.assertEquals(actualTitle,expectedTitle,"Google.com title was not as expected");
		System.out.println("the google title test completed");
		driver1.quit();
		softassert.assertAll();
	}
	@Test
	public static void validateYahooTitle() {
		WebDriver driver2;
		driver2 = new ChromeDriver();
		SoftAssert softassert = new SoftAssert();
		driver2.get("http://www.Yahoo.com");
		driver2.manage().window().maximize();
		driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualTitle = driver2.getTitle();
		String expectedTitle = "Yahoo";
		softassert.assertEquals(actualTitle,expectedTitle,"Yahoo.com title was not as expected");
		System.out.println("yahoo title test completed");
		driver2.quit();
		softassert.assertAll();
	}
	
	public static void main(String[] args) {
		

	}

}
