package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfigurations {
    public static Properties Config = new Properties();
    public static Properties ObjRepo = new Properties();
    public static FileInputStream fis;
    private static final Logger log = LogManager.getLogger(PropertiesConfigurations.class);

    /**
     * Method to configure the connection to the properties files, "Config" and "ObjRepo".
     */
    public static void setProps(){
        try {
            fis = new FileInputStream(
                    STR."\{System.getProperty("user.dir")}/src/test/resources/Properties/Config.properties");
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
                    STR."\{System.getProperty("user.dir")}/src/test/resources/Properties/ObjRepo.properties");
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
    }
}
