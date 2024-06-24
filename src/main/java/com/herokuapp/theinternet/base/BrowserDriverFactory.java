package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * BrowserDriverFactory is responsible for creating WebDriver instances based on the specified browser type.
 * It uses ThreadLocal to ensure each thread has its own instance of WebDriver, providing thread safety in parallel execution.
 */
public class BrowserDriverFactory {

    // ThreadLocal variable to hold the WebDriver instance
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // The browser type (e.g., "chrome" or "firefox")
    private String browser;

    // Logger instance for logging information
    private Logger log;

    /**
     * Constructor for BrowserDriverFactory.
     *
     * @param browser The browser type (e.g., "chrome" or "firefox").
     * @param log The logger instance for logging information.
     */
    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    /**
     * Creates a WebDriver instance based on the specified browser type.
     *
     * @return The WebDriver instance.
     */
    public WebDriver createDriver() {
        // Log the creation of the driver
        log.info("Create driver: " + browser);

        // Create and set the WebDriver instance based on the browser type
        switch (browser) {
            case "chrome":
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                driver.set(new FirefoxDriver());
                break;

            default:
                // If the browser type is unknown, log a message and start Chrome as the default
                log.info("Do not know how to start: " + browser + ", starting chrome.");
                driver.set(new ChromeDriver());
                break;
        }

        // Return the WebDriver instance
        return driver.get();
    }
}
