package com.herokuapp.theinternet.checkboxespagetest;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends BaseTest {

    @Test
    public void selectingTwoChecboxesTest() {
        log.info("Starting selectingTwoCheckboxesTest");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on Checkbox link
        CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();

        // Select All checkboxes
        checkboxesPage.selectAllCheckboxes();

        // Verify all checkboxes are checked
        Assert.assertTrue(checkboxesPage.areAllCheckboxesChecked(), "Not all checkboxes are selected!");

    }

}
