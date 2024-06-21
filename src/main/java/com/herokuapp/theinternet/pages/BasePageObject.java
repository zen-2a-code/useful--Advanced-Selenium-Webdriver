package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page with given URL
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Find element using given locator
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Find all elements using given locator
     */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Click on element with given locator when its visible
     */
    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    /**
     * Type given text into element with given locator
     */
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).sendKeys(text);
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(condition);
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public void waitForElementTextChange(By elementLocator, String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBe(elementLocator, expectedText));
    }

    /**
     * Get URL of current page from browser
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get Tile of current page
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get source of current page
     */
    public String getCurrentPageSource() {
        return driver.getPageSource();
    }

    /**
     * Switches to the window with the specified title.
     *
     * @param expectedTitle the title of the window to switch to
     * @throws NoSuchWindowException if no window with the specified title is found
     */
    public void switchToWindowWithTitle(String expectedTitle) {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(expectedTitle)) {
                return;
            }
        }

        // If the window with the expected title is not found, switch back to the original window
        driver.switchTo().window(originalWindow);
        throw new NoSuchWindowException("No window with title: " + expectedTitle);
    }


    public Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }


    /**
     * Switch to iFrame using its locator with error handling.
     *
     * @param frameLocator The locator for the iFrame
     */
    protected void switchToFrame(By frameLocator) {
        try {
            WebElement frameElement = find(frameLocator);
            driver.switchTo().frame(frameElement);
            System.out.println("Switched to frame: " + frameLocator);
        } catch (NoSuchElementException e) {
            System.err.println("Frame not found: " + frameLocator);
        } catch (StaleElementReferenceException e) {
            System.err.println("Frame is not attached to the page document: " + frameLocator);
        } catch (Exception e) {
            System.err.println("An error occurred while switching to frame: " + frameLocator);
        }
    }

    /**
     * Press a key on a specified locator with error handling.
     *
     * @param locator The locator for the element
     * @param key     The key to be pressed
     */
    protected void pressKey(By locator, Keys key) {
        try {
            WebElement element = find(locator);
            element.sendKeys(key);
            log.info("Pressed key: " + key.name() + " on element: " + locator);
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + locator, e);
        } catch (Exception e) {
            log.error("An error occurred while pressing key: " + key.name() + " on element: " + locator, e);
        }
    }

    /**
     * Press a key using the Actions class with error handling.
     *
     * @param key The key to be pressed
     */
    public void pressKeyWithActions(Keys key) {
        try {
            log.info("Pressing " + key.name() + " using Actions class");
            Actions action = new Actions(driver);
            action.sendKeys(key).build().perform();
            log.info("Successfully pressed key: " + key.name() + " using Actions class");
        } catch (Exception e) {
            log.error("An error occurred while pressing key: " + key.name() + " using Actions class", e);
        }
    }

    public void scrollToBottom() {
        log.info("Scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void dragAndDrop(By fromLocator, By toTargetLocator) {
        // Method that works only Chrome
//        WebElement sourceElement = find(fromLocator);
//        WebElement targetElement = find(toTargetLocator);
//
//        Actions actions = new Actions(driver);
//        actions.clickAndHold(sourceElement)
//                .moveToElement(targetElement)
//                .release(targetElement)
//                .build()
//                .perform();


        // method that works on all browsers including Firefox
        WebElement sourceElement = driver.findElement(fromLocator);
        WebElement targetElement = driver.findElement(toTargetLocator);

        String script = "function createEvent(typeOfEvent) {" +
                "var event = document.createEvent(\"CustomEvent\");" +
                "event.initCustomEvent(typeOfEvent, true, true, null);" +
                "event.dataTransfer = {" +
                "data: {}," +
                "setData: function (key, value) {" +
                "this.data[key] = value;" +
                "}," +
                "getData: function (key) {" +
                "return this.data[key];" +
                "}" +
                "};" +
                "return event;" +
                "}" +
                "function dispatchEvent(element, event, transferData) {" +
                "if (transferData !== undefined) {" +
                "event.dataTransfer = transferData;" +
                "}" +
                "if (element.dispatchEvent) {" +
                "element.dispatchEvent(event);" +
                "} else if (element.fireEvent) {" +
                "element.fireEvent(\"on\" + event.type, event);" +
                "}" +
                "}" +
                "function simulateHTML5DragAndDrop(element, target) {" +
                "var dragStartEvent = createEvent('dragstart');" +
                "dispatchEvent(element, dragStartEvent);" +
                "var dropEvent = createEvent('drop');" +
                "dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);" +
                "var dragEndEvent = createEvent('dragend');" +
                "dispatchEvent(element, dragEndEvent, dragStartEvent.dataTransfer);" +
                "}" +
                "simulateHTML5DragAndDrop(arguments[0], arguments[1]);";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, sourceElement, targetElement);
    }

    public void hoverOverElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

}
