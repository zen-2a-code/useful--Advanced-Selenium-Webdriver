package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CheckboxesPage extends BasePageObject {
    private By checkboxLocator = By.xpath("//input[@type='checkbox']");

    public CheckboxesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Get list of all checkboxes and select then if unchecked
     */
    public void selectAllCheckboxes() {
        log.info("Checking all unchecked checkboxes");
        List<WebElement> checkboxesList = findAll(checkboxLocator);

        for (WebElement checkbox : checkboxesList) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public boolean areAllCheckboxesChecked() {
        log.info("Verifying that all checkboxes are checked");
        List<WebElement> checkboxes = findAll(checkboxLocator);

        for (WebElement checkbox : checkboxes) {
            // if checkbox is not selected return false
            if (!checkbox.isSelected()) {
                return false;
            }
        }
        // else return true if the whole loop pass.
        return true;
    }
}

