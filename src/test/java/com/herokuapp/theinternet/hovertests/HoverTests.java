package com.herokuapp.theinternet.hovertests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HoverPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoverTests extends TestUtilities {

    @Test
    public void user2ProfileTest() {
        log.info("Starting user2ProfileTest");

        // Open HoversPage
        WelcomePage wp = new WelcomePage(driver, log);
        wp.openPage();
        HoverPage hoversPage = wp.clickHoversLink();

        // Open User 2 profile
        hoversPage.openUserProfile(2);
//        sleep(3);

        // Verify correct user profile opened
        Assert.assertTrue(hoversPage.getCurrentUrl().contains("/users/2"),
                "Url of opened page is not expected User 2 page url");
    }
}