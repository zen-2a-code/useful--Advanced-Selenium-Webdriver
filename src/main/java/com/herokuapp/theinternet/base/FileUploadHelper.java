package com.herokuapp.theinternet.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadHelper {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public FileUploadHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method handles the file upload when triggered by clicking a link element.
     *
     * @param linkLocator CSS locator for the link element that triggers the file upload dialog.
     * @param filePath The absolute path of the file to be uploaded.
     * @throws AWTException If the Robot class cannot be instantiated.
     */
    public void uploadFileViaLink(String linkLocator, String filePath) throws AWTException {
        // Find the link element and click it to open the file dialog
        WebElement uploadLink = driver.findElement(By.cssSelector(linkLocator));
        uploadLink.click();
        // Handle the file upload dialog using Robot class
        handleFileUpload(filePath);
    }

    /**
     * This method handles the file upload when triggered by clicking a button element.
     *
     * @param buttonLocator CSS locator for the button element that triggers the file upload dialog.
     * @param filePath The absolute path of the file to be uploaded.
     * @throws AWTException If the Robot class cannot be instantiated.
     */
    public void uploadFileViaButton(String buttonLocator, String filePath) throws AWTException {
        // Find the button element and click it to open the file dialog
        WebElement uploadButton = driver.findElement(By.cssSelector(buttonLocator));
        uploadButton.click();
        // Handle the file upload dialog using Robot class
        handleFileUpload(filePath);
    }

    /**
     * This method uses the Robot class to interact with the native file upload dialog.
     *
     * @param filePath The absolute path of the file to be uploaded.
     * @throws AWTException If the Robot class cannot be instantiated.
     */
    private void handleFileUpload(String filePath) throws AWTException {
        // Create a Robot instance to simulate keyboard actions
        Robot robot = new Robot();
        // Set a delay to allow the file dialog to open
        robot.setAutoDelay(2000);

        // Copy the file path to the system clipboard
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // Simulate pressing Ctrl+V (or Cmd+V on macOS) to paste the file path
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Simulate pressing Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}