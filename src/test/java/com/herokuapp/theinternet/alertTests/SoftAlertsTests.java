package com.herokuapp.theinternet.alertTests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.JavaSriptAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAlertsTests extends BaseTest {

    @Test
    public void jsAlertTest() {
        log.info("Starting jsAlertTest");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on JavaScript Alerts link
        JavaSriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink();

        // Click JS Alert button
        alertsPage.openJSAlert();

        // Get alert text
        String alertMessage = alertsPage.getAlertText() + " [FAIL intentionally]";

        // Click OK button
        alertsPage.acceptAlert();

        // Get Results text
        String result = alertsPage.getResultText() + " [FAIL intentionally]";

        // Verifications
        // Creating soft Assert object instance
        SoftAssert softAssert = new SoftAssert();

        // 1 - Alert text is expected
        softAssert.assertTrue(alertMessage.equals("I am a JS Alert"),
                "Alert message is not expected. \nShould be 'I am a JS Alert', but it is '" + alertMessage + "'");

        // 2 - Result text is expected
        softAssert.assertTrue(result.equals("You successfully clicked an alert"),
                "result is not expected. \nShould be 'You successfully clicked an alert', but it is '" + result + "'");

        softAssert.assertAll();

    }

    @Test(enabled = false)
    public void jsDismissTest() {
        log.info("Starting jsDismissTest");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on JavaScript Alerts link
        JavaSriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink();

        // Click JS Confirm button
        alertsPage.openJSConfirm();
        // Get alert text
        String alertMessage = alertsPage.getAlertText();

        // Click Cancel button
        alertsPage.dismissAlert();

        // Get Results text
        String result = alertsPage.getResultText();

        // Verifications
        // 1 - Alert text is expected
        Assert.assertTrue(alertMessage.equals("I am a JS Confirm"),
                "Alert message is not expected. \nShould be 'I am a JS Confirm', but it is '" + alertMessage + "'");

        // 2 - Result text is expected
        Assert.assertTrue(result.equals("You clicked: Cancel"),
                "result is not expected. \nShould be 'You clicked: Cancel', but it is '" + result + "'");
    }

    @Test(enabled = false)
    public void jsPromptTest() {
        log.info("Starting jsDismissTest");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on JavaScript Alerts link
        JavaSriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink();

        // Click JS Prompt button
        alertsPage.openJSPrompt();

        // Get alert text
        String alertMessage = alertsPage.getAlertText();

        // Type text into alert
        alertsPage.typeTextIntoAlert("Hello Alert, it's Dmitry here");

        // Get Results text
        String result = alertsPage.getResultText();

        // Verifications
        // 1 - Alert text is expected
        Assert.assertTrue(alertMessage.equals("I am a JS prompt"),
                "Alert message is not expected. \nShould be 'I am a JS prompt', but it is '" + alertMessage + "'");

        // 2 - Result text is expected
        Assert.assertTrue(result.equals("You entered: Hello Alert, it's Dmitry here"),
                "result is not expected. \nShould be 'You entered: Hello Alert, it's Dmitry here', but it is '" + result
                        + "'");
    }
}
