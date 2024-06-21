package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject {
    private By dropdownLocator = By.id("dropdown");

    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectOption(int n) {
        log.info("Selecting option " + n + " from dropdown");
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdown = new Select(dropdownElement);

        // There are three ways to use select instance

        //#1
        // dropdown.selectByIndex(n);

        //#2
        dropdown.selectByValue("" + n); // we cast the integer to String

        //#3
        // dropdown.selectByVisibleText("Option " + n);


    }

    public String getSelectedOption() {
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        log.info(selectedOption + " is selected in dropdown");
        return selectedOption;
    }
}
