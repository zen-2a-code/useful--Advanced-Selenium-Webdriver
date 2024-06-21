package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {
    private String expectedUrl = "https://the-internet.herokuapp.com/secure";

    private By logOutButtonLocator = By.xpath("//a[@class='button secondary radius']");
    private By successMessageLocator = By.id("flash");

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    public boolean isLogOutButtonVisible() {
        return find(logOutButtonLocator).isDisplayed();
    }

    public String getActualSuccessMessageText() {
        return find(successMessageLocator).getText();
    }
}
