package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

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
	static int x,sum;
	static int nextSum;
	static int avg;
	private static int Counter = 1;
	private static int nextCounter = 1;
	
	//Adding Extent Report classes
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	//Method to return the current time as Day_Mon_Date @ hh_mm (12-hour format)
		public String getCurrentTime() {
			Date myDate = new Date();
			
			String fileName = myDate.toString().replace(":", "_").replace(" ", "_");
			
			String day = fileName.substring(0, 10);
		
			String time = fileName.substring(11, 19);

			String[] x = time.split("_");

			String AMPM = "";

			int hour = Integer.valueOf(x[0]);
			if (hour >= 12) {
				AMPM = "PM";
				if (hour != 12) {
					hour = hour - 12;
				}
			} else {
				AMPM = "AM";
			}
			/*NOTE: Added the below condition in case the hour is 12:00am (midnight = 00:00 in 24hr format)*/
			if(hour == 0) {
				hour = 12;
			}
			String finalDateTime = day + " @ " + hour + "_" + x[1] + " " + AMPM;
			return finalDateTime;
		}
	
	@BeforeSuite //must run before any test class files
	public static void setUp() {
		// set up config, object repo files, set up logging, extent reports, invoke the
		// browser

		if (driver == null) {

			// Configure log4j properties file
			PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/Properties/log4j.properties");

			// finding the Config properties file
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/Properties/Config.properties");
				log.info("Config File Found!");
			} catch (FileNotFoundException e) {
				log.error("Config file Not Found, Check File Path");
				log.info(e.getMessage());

			}
			try { // loading the config properties file
				Config.load(fis);
				log.info("Config File loaded successfully");
			} catch (IOException e) {
				log.error("Error whole loading Config File");
				log.info(e.getMessage());
				;
			}

			// get ObjRepo properties file
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/Properties/ObjRepo.properties");
				log.info("Object Repository File Found!");
			} catch (FileNotFoundException e) {
				log.error("Object Repository File Not Found, Check File Path");
				log.info(e.getMessage());
			}
			try {
				ObjRepo.load(fis);
				log.info("Object Repository loaded successfully");
			} catch (IOException e) {
				log.error("Error while loading Object Repository file");
				log.info(e.getMessage());
			}

			// initialize the browser
			if (Config.getProperty("browser").equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver","C:\\Web Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Firefox was launched successfully");
				
				
			} else if (Config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverExecutables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome was launched successfully");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.valueOf(Config.getProperty("implicit.wait")),TimeUnit.SECONDS);

			 //set up explicit wait
			 wait = new WebDriverWait(driver, Integer.valueOf(Config.getProperty("explicit.wait")));
			 
			
			
			//Configure Extent Reports
			sparkReporter = new ExtentSparkReporter("./ExtentReports/extent.html");
			sparkReporter.config().setEncoding("utf-8");
			sparkReporter.config().setDocumentTitle("Bootcamp 2020 - Test Automation Report");
			sparkReporter.config().setReportName("John'Tay Abbey - Twitter POM w/Page Factory Test Report");
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			
			//instantiate the ExtentReport class
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);//attaching the sparkReporter configurations
			//to the extent report object
			
			//add more details to the report
			extent.setSystemInfo("Automation Engineer", "John'Tay Abbey");
			extent.setSystemInfo("Organization","PCS Consulting");
			extent.setSystemInfo("Product Team","Lions");
			extent.setSystemInfo("Build #","PCS-46.9");
			
			
			
			
			
		}
	}
	//selecting a element from a drop down
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
	
	public static void startBrowser(String key, String URL) {
		// initialize the browser
		if (key.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","C:\\Web Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox was launched successfully");
			driver.get(URL);
			log.info(key + " Navigated to "+ URL);
			driver.manage().window().maximize();
			
		} else if (key.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\johnt\\eclipse-workspace\\PageObjectModel\\src\\test\\resources\\driverExecutables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Chrome was launched successfully");
			driver.get(URL);
			log.info(key + " Navigated to "+ URL);
			driver.manage().window().maximize();
		}
	}
	
	//method to group all the prices of a product and get the sum and the average 
public static void calculateAveragePageProductPrice() {
		
		Config.getProperty("implicit.wait");
List<WebElement> priceAmount = driver.findElements(By.xpath("//div[@class ='payment-section']//span[1]"));
		Config.getProperty("explicit.wait");
		for (int i = 0; i < priceAmount.size(); i++) {
			Config.getProperty("explicit.wait");
			Integer List = Integer.valueOf(priceAmount.get(i).getText().replace("$", "").replace(",", ""));
		
			 ArrayList<Integer> total = new ArrayList<Integer>();
			 Config.getProperty("explicit.wait");
			total.add(List);
			 Config.getProperty("explicit.wait");
			System.out.println(total +" List of numbers");
			Config.getProperty("explicit.wait");
			  
			//get the total price of the numbers in an array
			 sum = 0;
			for(int x = 0; x < total.size(); x++) {
			Config.getProperty("explicit.wait");
				Counter = nextCounter;
				sum = nextSum;
			    nextSum += total.get(x);
			    avg = nextSum/nextCounter++;
			    
				Config.getProperty("explicit.wait");
			System.out.println("The total price is "+nextSum);
			System.out.println(avg+" is the Average price");
									 
		}}}
	//Method to enter text into a text box
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
		extent.flush();
	}

	@BeforeMethod
	public static void browserNavigation() {
		driver.navigate().to(Config.getProperty("QA_URL"));
		log.info("Navigated to: " + Config.getProperty("QA_URL"));
	}
}
