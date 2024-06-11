import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static base.testBase.driver;
import static base.testBase.wait;

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

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println(STR."Element is not attached to the page document \{e.getStackTrace()}");
        } catch (NoSuchElementException e) {
            System.out.println(STR."Element was not found in DOM \{e.getStackTrace()}");
        } catch (Exception e) {
            System.out.println(STR."Unable to click on element \{e.getStackTrace()}");
        }
    }


    /**
     * method to wait for all elements on a web page to load
     */
    public static void waitUntilVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements());
    }


    /**
     * method to wait for a particular web element to load
     *
     * @param ele
     */
    public static void waitUntil(WebElement ele) {
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
        driver.getTitle();
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

public void main() {
}


