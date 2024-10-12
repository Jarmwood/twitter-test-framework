package utilities.DriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import Enums.browserTypes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.PropertiesConfigurations;
import utilities.ReadingExcel;

public class driverManager {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ReadingExcel excel = new ReadingExcel(STR."\{System.getProperty("user.dir")}/src/test/resources/excelTestData/TwitterData.xlsx");

    /**
     * Method to create the browser instance based
     * on the various browser options stated in the prop file
     * @param URL String: input for desired url address
     * @return driver: browser instance
     */
    public static WebDriver startBrowser(String URL) {
        String coreURL;
        browserTypes browser;
        try {
            PropertiesConfigurations.Config.load(Files.newInputStream(
                    Paths.get(STR."\{System.getProperty("user.dir")}/src/test/resources/Properties/Config.properties")));
        } catch (IOException ignored) {}

        coreURL = PropertiesConfigurations.Config.getProperty("QA_URL");
        if (driver != null) {
            browser = browserTypes.valueOf(PropertiesConfigurations.Config.getProperty("browserConfig").toUpperCase());
            switch (browser) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().clearDriverCache().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                case EDGE:
                    WebDriverManager.edgedriver().clearDriverCache().setup();
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                case SAFARI:
                    WebDriverManager.safaridriver().clearDriverCache().setup();
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                default:
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
            }
            driver.get(coreURL+URL);
        }
        return driver;
    }


    @AfterSuite // must run after all test class files have executed
    public static void tearDown() throws IOException {
        driver.quit();
        PropertiesConfigurations.fis.close();
    }


}
