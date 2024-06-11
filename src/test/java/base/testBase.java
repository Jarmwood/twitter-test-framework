package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import Enums.browserTypes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.ReadingExcel;

public class testBase {

	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties ObjRepo = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static Logger log = Logger.getLogger(testBase.class);
	public static ReadingExcel excel = new ReadingExcel(System.getProperty("user.dir")+"/src/test/resources/excelTestData/TwitterData.xlsx");

	
	public static WebDriver startBrowser(String URL) {
		String coreURL;
		browserTypes browser;
		try {
			Config.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/test/resources/Properties/Config.properties")));
		} catch (IOException ignored) {
		}
		coreURL = Config.getProperty("QA_URL");
		// initialize the browser
		if (driver != null) {
			browser = browserTypes.valueOf(Config.getProperty("browserConfig").toUpperCase());
			switch (browser) {
				case FIREFOX:
					WebDriverManager.firefoxdriver().clearDriverCache().setup();
					driver = new FirefoxDriver();
					log.info("Firefox was launched successfully");
					log.info(browser + " Navigated to " + URL);
					driver.manage().window().maximize();
				case EDGE:
					WebDriverManager.edgedriver().clearDriverCache().setup();
					driver = new EdgeDriver();
					log.info("EdgeDriver was launched successfully");
					log.info(browser + " Navigated to " + URL);
					driver.manage().window().maximize();
				case SAFARI:
					WebDriverManager.safaridriver().clearDriverCache().setup();
					driver = new SafariDriver();
					log.info("safaridriver was launched successfully");
					log.info(browser + " Navigated to " + URL);
					driver.manage().window().maximize();
				default:
					WebDriverManager.chromedriver().clearDriverCache().setup();
					driver = new ChromeDriver();
					log.info("chromedriver was launched successfully");
					log.info(browser + " Navigated to " + URL);
					driver.manage().window().maximize();
			}
			driver.get(coreURL+URL);
		}
        return driver;
    }

	

	@AfterSuite // must run after all test class files have executed
	public static void tearDown() throws IOException {

		driver.quit();
		log.info("Closing the browser");
		fis.close();

	}


}
