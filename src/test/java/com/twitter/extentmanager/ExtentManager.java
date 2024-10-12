package com.twitter.extentmanager;

import java.io.File;
import java.nio.file.FileSystems;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	public static ExtentSparkReporter sparkReporter;
	private static ExtentReports extent;
    private static final String reportFileName = "Test-Automaton-Report"+".html";
    private static final String fileSeparator = FileSystems.getDefault().getSeparator();
    private static final String reportFilepath = STR."\{System.getProperty("user.dir")}\{fileSeparator}TestReport";
    private static final String reportFileLocation =  reportFilepath +fileSeparator+ reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    /**
     * Method to create an extent report instance
     */
    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);

		sparkReporter = new ExtentSparkReporter("./ExtentReports/extent.html");
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setDocumentTitle("Test Automation Report");
		sparkReporter.config().setReportName("Twitter Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Automation Engineer", "John'Tay");
		extent.setSystemInfo("Organization","Mock Twitter");
		extent.setSystemInfo("Product Team","Wolves");
		extent.setSystemInfo("Build #","v5.46.9");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
 
        return extent;
    }

    /**
     * Method to create the Extent report Directory where file is stored
     */
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println(STR."Directory: \{path} is created!");
                return reportFileLocation;
            } else {
                System.out.println(STR."Failed to create directory: \{path}");
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println(STR."Directory already exists: \{path}");
        }
		return reportFileLocation;
    }
 
}


