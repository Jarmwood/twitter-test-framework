package Components;

import Enums.browserTypes;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import utilities.testEngine;
import utilities.PropertiesConfigurations;
import java.util.*;
import java.io.IOException;
import java.util.Locale;

public class DriverManager {
    private Logger log;
    Properties config;
    public static WebDriver driver;
    public static WebDriverWait wait;

    public DriverManager() {
        this(false);
    }

    /**
     * Method to create the browser instance based
     * on the various browser options stated in the prop file
     **/
    public DriverManager(boolean headless) {
        config = testEngine.getConfig();
        browserTypes driverType=browserTypes.valueOf(config.getProperty("browser").toUpperCase(Locale.ENGLISH));
        String headlessLog = "";

        if(headless)
            headlessLog=" ***** In Headless Mode *****";


        if (driver != null) {
            switch (driverType) {
                case FIREFOX:
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    log.info("MS Edge successfully launched {}", headlessLog);
                    break;
                case SAFARI:
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    log.info("Safari successfully launched {}", headlessLog);
                    break;
                default:
                    ChromeOptions options = getChromeOptions(headless);
                    driver = new ChromeDriver(options);
                    driver.manage().deleteAllCookies();
                    driver.manage().window().maximize();
                    log.info("chrome successfully launched {}",  headlessLog);
                    break;
            }
        }
    }

    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");
        if (headless)
            options.addArguments(
                    "--start-maximized",
                    "--headless",
                    "--window-size=2560,1440",
                    "--ignore-certificate-errors",
                    "--disable-extentions",
                    "--disable-dev-shm-usage");
        else
            options.addArguments("--ignore-certificate-errors");
        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterSuite // must run after all test class files have executed
    public static void tearDown() throws IOException {
        driver.quit();
        PropertiesConfigurations.fis.close();
    }


}
