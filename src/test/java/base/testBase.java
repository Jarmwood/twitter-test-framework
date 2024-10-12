package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import utilities.ReadingExcel;

public class testBase {

	public static void main(String[] args) {
		// super class for all our test cases --> all test cases classes will extend
		// this class
		
	}

	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties ObjRepo = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static Logger log = Logger.getLogger(testBase.class);
	public static ReadingExcel excel = new ReadingExcel(System.getProperty("user.dir")+"/src/test/resources/excelTestData/TwitterData.xlsx");



	//selecting the element from a dropdown
	public static void select(String key, String optionType, String option) {
		try {
		if (key.endsWith("_XPATH")) {
			WebElement dropdownElement = driver.findElement(By.xpath(ObjRepo.getProperty(key)));
			Select options = new Select(dropdownElement);
			if(optionType.equals("value")) {
				options.selectByValue(option);
			}else if (optionType.equals("text")) {
				options.selectByVisibleText(option);
			}
		}else if (key.endsWith("_CSS")) {
			WebElement dropdownElement = driver.findElement(By.cssSelector(ObjRepo.getProperty(key)));
			Select options = new Select(dropdownElement);
			if(optionType.equals("value")) {
				options.selectByValue(option);
			}else if (optionType.equals("text")) {
				options.selectByVisibleText(option);
			}}
		log.info(option + " was Selected from" +key+ " drop down");
		}catch(Throwable t) {
			//log.error("Error while Selecting "+ option + "from "+ key + " drop down" );
			//log.info(t.getMessage());
		}
	}
	
	public static WebDriver startBrowser(String browser, String URL) {
		// initialize the browser
		if (driver != null) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\Web Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Firefox was launched successfully");
				log.info(browser + " Navigated to " + URL);
				driver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\johnt\\eclipse-workspace\\PageObjectModel\\src\\test\\resources\\driverExecutables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome was launched successfully");
				driver.get(URL);
				log.info(browser + " Navigated to " + URL);
				driver.manage().window().maximize();
			}
		}
		return driver;
	}

	public static void enterText(String key, String value) {
		try {
		if(key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(ObjRepo.getProperty(key))).clear();
			driver.findElement(By.xpath(ObjRepo.getProperty(key))).sendKeys(value);
		}else if(key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(ObjRepo.getProperty(key))).clear();
			driver.findElement(By.cssSelector(ObjRepo.getProperty(key))).sendKeys(value);
		}
		log.info(value + " was entered in " +key+ "input element");
		}catch(Throwable t) {
			//log.error("Error While Entering "+ value + " in "+ key + " input box");
		//	log.info(t.getMessage());
			}
		}
	//method to click on an element
	public static void click(String key) {
		try {
		if(key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(ObjRepo.getProperty(key))).click();
		}else if(key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(ObjRepo.getProperty(key))).click();
		}
		log.info(key+" element was clicked");
		}catch(Throwable t) {
			//log.error("Error while Clicking on Key");
			//log.info(t.getMessage());
		}
	}
	//javaScriptExecutor method to click on an element
	public static void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}
	
	
	//method to wait for all elements on a web page to load
	public static void waitUntilVisible() { 
		 wait = new WebDriverWait(driver, Integer.valueOf(Config.getProperty("wait.VisibleForAll")));
		wait.until(ExpectedConditions.visibilityOfAllElements());
		log.info("The 'wait until all elements is visible' is complete");
	}
	
	
	//method to wait for a particular web element to load
	public static void waitUntil(String key) {
		try {
		 	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjRepo.getProperty(key))));
		 	log.info("The 'wait until elementIsClickable' was successful");
		}catch(NoSuchElementException e) {
			log.info("Element was not found");
		
		}catch(Exception e) {
			log.error("Element is not Clickable");
		}
		}
		
		
	
	
	//method to capture a page title
	public static void capturePageTitle() {
		driver.getTitle();
	}
	
	//method to capture text from a page
	public static String captureTextValue(String key) {
		String textValue = "";
				try{
					if(key.endsWith("_XPATH")) {
				
					textValue = driver.findElement(By.xpath(ObjRepo.getProperty(key))).getText();
					}
				else if (key.endsWith("_CSS")) {
					textValue = driver.findElement(By.cssSelector(ObjRepo.getProperty(key))).getText();
				}
	
	}catch(Throwable t) {
		log.error("Error while trying to capture text: " + key);
		t.printStackTrace();
		System.exit(1);
	}
				return textValue;
				
}
	
	// method to format 2 strings 

	@AfterSuite // must run after all test class files have executed
	public static void tearDown() throws IOException {

		driver.quit();
		log.info("Closing the browser");
		fis.close();

	}


}
