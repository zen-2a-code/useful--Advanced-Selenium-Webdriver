package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewWindowPage extends BasePageObject {
    private By newWindowTextLocator = By.tagName("h3");

    public NewWindowPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getNewWindowsPageText() {
        return find(newWindowTextLocator).getText();
    }
}
