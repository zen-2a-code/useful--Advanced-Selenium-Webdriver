package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePageObject {
    private By leftColumnLocator = By.id("column-a");
    private By rightColumnLocator = By.id("column-b");

    public DragAndDropPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void dragLefttoRight() {
        log.info("Drag Left box on right box");
        dragAndDrop(leftColumnLocator, rightColumnLocator);
    }

    public void dragRighttoLeft() {
        log.info("Drag Right box on Left box");
        dragAndDrop(rightColumnLocator, leftColumnLocator);
    }

    /**
     *
     * @param column The column that we need to wait the text to change. Enter either "left" or "right"
     * @param text the text that should be displayed in the column.
     */
    public void waitForColumnTextToChange(String column, String text) {
        By selectedColumn;
        switch (column.toLowerCase()) {
            case "left":
                selectedColumn = leftColumnLocator;
                break;
            case "right":
                selectedColumn = rightColumnLocator;
                break;
        }
        waitForElementTextChange(leftColumnLocator, text, 10);
    }

    public String gettingLeftColumnText() {
        return find(leftColumnLocator).getText();
    }

    public String gettingRightColumnText() {
        return find(rightColumnLocator).getText();
    }


}
