package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.ReadingExcel;



public class baseTestClass {
	private WebDriver driver;
	
	public static Properties Config = new Properties();
	public static Properties ObjRepo = new Properties();
	private static FileInputStream fis;
	public static Logger log = Logger.getLogger(baseTestClass.class);
	public static ReadingExcel excel = new ReadingExcel(System.getProperty("user.dir")+"/src/test/resources/excelTestData/TwitterData.xlsx");
	// Adding Extent Report classes
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	//Method to return the current Time as Day_Mon_Date @ hh_mm 12-hour format
		public String getCurrentTime() {
		Date myDate = new Date();
		String fileName = myDate.toString().replace(":", "_").replace(" ", "_");
		String day = fileName.substring(0, 10);
		String time = fileName.substring(11, 19);
		String [] x = time.split("_");
		String AMPM = "";
		int hour = Integer.valueOf(x[0]);
		if(hour >= 12) {
			AMPM = "PM";
			hour = hour-12;
		}else {
			AMPM = "AM";
		}
		String finalDateTime = day + " @ " + hour + "_" + x[1] + " " + AMPM;
//			System.out.println(finalDateTime);
		return finalDateTime;
		}
		
		
	@BeforeSuite
	public void setUp() {
		
		//Configure logging
		configureLogging();

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
		
		//Configure Extent Reports
		sparkReporter = new ExtentSparkReporter("./ExtentReports/"+getCurrentTime()+".html");
		sparkReporter.config().setEncoding("utf-8"); //required
		sparkReporter.config().setDocumentTitle("Bootcamp 2020 - Test Automation Report");
		sparkReporter.config().setReportName("John'Tay Abbey - Twitter POM w/Page Factory Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("EEEE - MM/dd/yy, hh:mm a '('zzz')'");
		
		//Instantiate the ExtentReport class
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); //attaching the sparkReporter configurations 
		//to the extent report object
		
		//Add more details to the report
		extent.setSystemInfo("Automation Engineer", "John'Tay Abbey");
		extent.setSystemInfo("Organization", "PCS Consulting");
		extent.setSystemInfo("Product Team", "Lions");
		extent.setSystemInfo("Build #", "PCS-4.6.9");
	}
	
public static void startBrowser(String key, String URL) {
	
WebDriver driver;
	// initialize the browser
	if (key.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver","C:\\Web Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("Firefox was launched successfully");
		driver.get(URL);
		log.info(key + " Navigated to "+ URL);
		driver.manage().window().maximize();
		
	} else if (key.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver","C:\\Web Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		log.info("Chrome was launched successfully");
		driver.get(URL);
		log.info(key + " Navigated to "+ URL);
		driver.manage().window().maximize();
		
	}
	
}


	public static void logInfo(String message) {
		testReport.get().log(Status.INFO, message);
	}
	
	

	@AfterSuite
	public void tearDown() throws IOException {
		fis.close();
		extent.flush();
		log.info("---- Test Execution Completed ----");
	}


	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir")+
				"\\src\\test\\resources\\Properties\\log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}
}

