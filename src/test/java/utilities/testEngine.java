package utilities;

import Components.DriverManager;
import java.util.logging.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class testEngine {
	public static final boolean isDemo = false;
	public static final boolean headlessMode = false;
	public static Logger log = (Logger) LogManager.getLogManager();
	private static Properties config;
	private static String coreUrl = "";
	private static WebDriver driver;
	private static String driverType = "";
	private static int explicitWait;
	private static WebDriverWait wait;

	/**
	 * Initializes all static values and gets them ready for use.
	 * Starts the browser of choice and Navigates to the provided url to start testing
	 * @param Url
	 */
	private static void startBrowser(String Url) {
		if (driver == null || driverType.isEmpty() || driverType.equalsIgnoreCase(getConfig().getProperty("browser"))) {
			driverType = getConfig().getProperty("browser").toUpperCase();
			driver = new DriverManager(headlessMode).getDriver();
		}
		try {
			driver.get(getCoreUrl() + Url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getConfigInt("implicit.wait")));
		log.info("************************************************");
		log.info("********* TEST ENVIRONMENT IS READY ************");
		log.info("************************************************");
	}

	/**
	 * Loads the config file properties and then returns the reference
	 * @return Properties: loaded config file
	 */
	public static Properties getConfig(){
		if(null!=config)
			return config;
		Properties configTmp = new Properties();
		try{
			FileInputStream fis = new FileInputStream(System.getProperty(("user.dir") + "src/test/resources/Properties/Config.properties"));
			configTmp.load(fis);
			return configTmp;
		}catch (IOException e){
			e.printStackTrace();
			return configTmp;
		}
	}

	private static int getConfigInt(String name) {
		int configInt;
		try {
			configInt = Integer.parseInt(getConfig().getProperty(name));
			if (configInt == 0) {
				configInt = 5;
                //log.warn("{} has not been set in the configs. setting to default of 5", name);
			}
		} catch (Exception Ignore) {
			configInt = 5;
			//log.warn("{} has been set to a illegal variable. Please make sure it is set to a number", name);
		}
		return configInt;
	}

	public static String getCoreUrl(){
		if(coreUrl.isEmpty()){
			if (getConfig().getProperty("environment").equalsIgnoreCase("dev"))
				coreUrl = getConfig().getProperty("devUrl");
			else
				coreUrl = getConfig().getProperty("qaURL");
			//log.info("successfully set the browser environment to: {}", coreUrl);
		}
		return coreUrl;
	}

	/**
	 * Getter for the driver. Will start the browser if the driver has not been initialized.
	 * @param URL
	 * @return WebDriver
	 */
	public static WebDriver getDriver(String URL){
		if(null==driver||driver.toString().contains("null")){
			log.info("Starting Browser...");
			startBrowser(URL);
		}
		return driver;
	}

	public static WebDriver getDriver(){
		return getDriver("");
	}

	public static int getExplicitWait(){
		if(explicitWait==0)
			explicitWait = getConfigInt("explicit.wait");
		return explicitWait;
	}

	public static WebDriverWait getDriverWait(){
		if(null == wait)
			wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getConfigInt("wait")));
		return wait;
    }
}