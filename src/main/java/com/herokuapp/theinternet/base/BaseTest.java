package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

/**
 * BaseTest serves as a base class for all test classes.
 * It handles the setup and teardown of the WebDriver instance, logging, and capturing test metadata.
 */
public class BaseTest {

    // WebDriver instance used by the test classes
    protected WebDriver driver;

    // Logger instance for logging test information
    protected Logger log;

    // Name of the current test suite
    protected String testSuiteName;

    // Name of the current test
    protected String testName;

    // Name of the current test method
    protected String testMethodName;

    /**
     * This method runs before each test method.
     * It sets up the WebDriver, logging, and captures test metadata.
     *
     * @param method The test method being executed.
     * @param browser The browser type specified in the testng.xml file. Defaults to "chrome".
     * @param ctx The test context, which provides information about the current test.
     */
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, ITestContext ctx) {
        // Get the current test name for logging
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        // Create the WebDriver instance using the BrowserDriverFactory
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        // Set the browser window position and size
        driver.manage().window().setPosition(new Point(-1000, 200));
        driver.manage().window().maximize();

        // Capture test suite, test, and test method names
        this.testSuiteName = ctx.getSuite().getName();
        this.testName = testName;
        this.testMethodName = method.getName();
    }

    /**
     * This method runs after each test method.
     * It closes the WebDriver and logs the teardown action.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Log the teardown action
        log.info("Close driver");

        // Close the browser
        driver.quit();
    }
}