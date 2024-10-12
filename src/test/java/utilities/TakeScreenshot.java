package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TakeScreenshot extends testEngine {
	

	
	public static String takeScreenshot(String testName) throws IOException {
		Date myDate = new Date();
		
		String fileName = myDate.toString().replace(":","_").replace(" ", "_");
		
		String day = fileName.substring(0,10);
		
		String time = fileName.substring(11, 19);
		
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + day + "/"
		+ time + "_" + testName +".jpg";
		
		/*
		 * 1st: type casting driver to type TakesScreenshot and then calling the method "getScreenshotAs"
		 * 2nd: setting the output type as file, so we get the screenshot as a file
		 * 3rd: storing the screenshot in "screenshot" variable type file
		 */
		
		//capture a screenshot and returns a file 
		File screenshot = ((TakesScreenshot) testEngine.driver).getScreenshotAs(OutputType.FILE);
		
		/*
		 * copy the file to a specific location 
		 *must import the file utils class from the commons package --> apache (maven dependency)
		 */
		FileUtils.copyFile(screenshot, new File(screenshotPath));
		
		
		return screenshotPath;
	}
	
}
