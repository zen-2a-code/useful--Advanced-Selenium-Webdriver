package com.herokuapp.theinternet.DropdownTest;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.DropdownPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownTest extends BaseTest {

    @Test
    public void optionTwoTest() {
        log.info("Starting optionTwoTest");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click dropdown link
        DropdownPage dropdownPage = welcomePage.clickDropdownLink();

        // Select option 2
        dropdownPage.selectOption(2);

        // Verify Option 2 is selected
        String selectedOption = dropdownPage.getSelectedOption();
        Assert.assertTrue(selectedOption.equals("Option 2"), "Option 2 is not selected Instead selected - " + selectedOption);
    }
}
