package com.herokuapp.theinternet.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest {

    /**
     * Sleep for a specified number of seconds.
     *
     * @param seconds The number of seconds to sleep.
     */
    protected void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Data provider for test files.
     *
     * @return A 2D array of test file data.
     */
    @DataProvider(name = "files")
    protected Object[][] files() {
        return new Object[][]{
                {1, "index.html"}, {2, "logo.png"}, {3, "text.txt"}
        };
    }

    /**
     * Take a screenshot and save it with the specified file name.
     *
     * @param fileName The name of the screenshot file.
     */
    protected void takeScreenshot(String fileName) {
        // Capture the screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Construct the path to save the screenshot
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodaysDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";

        // Save the screenshot to the constructed path
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get today's date in ddMMyyyy format.
     *
     * @return The current date as a string.
     */
    private static String getTodaysDate() {
        return (new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }

    /**
     * Get the current system time in HHmmssSSS format.
     *
     * @return The current system time as a string.
     */
    private String getSystemTime() {
        return (new SimpleDateFormat("HH.mm.ss.SSS").format(new Date()));
    }
}