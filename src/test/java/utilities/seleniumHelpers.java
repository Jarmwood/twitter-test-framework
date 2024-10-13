import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.testEngine;

import java.time.Duration;
import java.util.Date;

import static utilities.testEngine.*;

public class seleniumHelpers {
    /**
     * Method to select an element from a dropdown
     *
     * @param ele
     * @param option
     */
    public void selectDropdown(WebElement ele, String option) {
        Select options = new Select(ele);
        options.selectByVisibleText(option);
    }
}

    /**
     * Method to enter text in a text field
     *
     * @param ele
     * @param value
     */
    public static void enterText(WebElement ele, String value) {
        ele.clear();
        ele.sendKeys(value);
    }

    /**
     * method to click on an element
     *
     * @param ele
     */
    public static void click(WebElement ele) {
        ele.click();
    }

    /**
     * javaScriptExecutor method to click on an element
     *
     * @param element
     * @throws Exception
     */
    public static void safeJavaScriptClick(WebElement element) throws Exception {
        try {

            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");

                WebDriver driver = testEngine.getDriver();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (Exception e) {
            System.out.println("Unable to click on element "+ e.getStackTrace());
        }
    }


    /**
     * method to wait for all elements on a web page to load
     */
    public static void waitUntilVisible() {
        WebDriverWait wait;
        wait = new WebDriverWait(testEngine.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements());
    }


    /**
     * method to wait for a particular web element to load
     *
     * @param ele
     */
    public static void waitUntil(WebElement ele) {
        WebDriverWait wait;
        wait = new WebDriverWait(testEngine.getDriver(), Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ele));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * method to capture a page title
     */
    public static void capturePageTitle() {
        testEngine.getDriver().getTitle();
    }

    /**
     * method to capture text from a page
     *
     * @param ele
     * @return
     */
    public static String captureTextValue(WebElement ele) {

        String textValue = ele.getText();

        return textValue;

    }

/**
 * Method to return the current Time as
 * Day_Mon_Date @ hh_mm 12-hour format
 */
public String getCurrentTime() {
    Date myDate = new Date();
    String fileName = myDate.toString().replace(":", "_").replace(" ", "_");
    String day = fileName.substring(0, 10);
    String time = fileName.substring(11, 19);
    String [] x = time.split("_");
    String AMPM = "";
    int hour = Integer.parseInt(x[0]);
    if(hour >= 12) {
        AMPM = "PM";
        hour = hour-12;
    }else {
        AMPM = "AM";
    }
    return day+ hour+ x[1] + AMPM;
}

public void main() {
}




