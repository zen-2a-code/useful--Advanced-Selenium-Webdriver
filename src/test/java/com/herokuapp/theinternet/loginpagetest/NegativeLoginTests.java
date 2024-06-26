package com.herokuapp.theinternet.loginpagetest;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class NegativeLoginTests extends TestUtilities {

    @Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLoginTest(Map<String, String> testData) {
        log.info("Starting negativeTest");

        //Data extraction and definition in separate variables

        // always use the headers from CSV file to ensure correctness
        // No:, username, password, expectedMessage, description

        String numOfTest = testData.get("No:");
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        // Log
        log.info("Starting negativeLogInTest #:" + numOfTest + " for " + description);
        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        // execute negative login
        loginPage.negativeLogIn(username, password);

        // wait for error message
        loginPage.waitForErrorMessage();
        String message = loginPage.getErrorMessageText();

        // Verification
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
    }

}
