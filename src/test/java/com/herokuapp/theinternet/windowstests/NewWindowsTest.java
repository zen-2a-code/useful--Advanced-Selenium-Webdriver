package com.herokuapp.theinternet.windowstests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.OpeningNewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewWindowsTest extends BaseTest {

    @Test
    public void OpenNewPage() {
        WelcomePage wp = new WelcomePage(driver, log);
        // Open page
        wp.openPage();

        // Click on new windows page
        OpeningNewWindowPage openingNewWindowsPage = wp.clickMultipleWindowsLink();

        // Click on click here
        openingNewWindowsPage.clickOnClickHere();

        // Switch to the new tap and creating a new NewWindow Instance

        NewWindowPage newWindowPage = openingNewWindowsPage.switchToNewWindowPage();

        // Verified that a new window is opened.
        String expectedText = "New Window";
        Assert.assertEquals(newWindowPage.getNewWindowsPageText(), expectedText);
    }
}
