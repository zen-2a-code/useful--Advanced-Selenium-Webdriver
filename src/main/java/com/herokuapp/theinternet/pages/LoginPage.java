package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {


    // locators
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.name("password");
    private By logInButtonLocatorLocator = By.tagName("button");
    private By errorMessageLocator = By.id("flash");


    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void negativeLogIn(String username, String password) {
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(logInButtonLocatorLocator);
    }

    public void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLocator, 5);
    }

    public String getErrorMessageText() {
        return find(errorMessageLocator).getText();
    }

    public SecureAreaPage logIn(String username, String password) {
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(logInButtonLocatorLocator);
        return new SecureAreaPage(driver, log);
    }
}
