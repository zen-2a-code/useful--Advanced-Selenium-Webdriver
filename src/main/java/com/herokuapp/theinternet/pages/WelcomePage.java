package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {

    private String pageUrl = "https://the-internet.herokuapp.com/";
    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");
    private By dropdownLinkLocator = By.linkText("Dropdown");
    private By javascriptAlertsLinkLocator = By.linkText("JavaScript Alerts");
    private By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
    private By wysiwygEditorLocator = By.linkText("WYSIWYG Editor");
    private By dragAndDropLocator = By.linkText("Drag and Drop");
    private By hoversLocator = By.linkText("Hovers");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public LoginPage clickFormAuthenticationLink() {
        log.info("Clicking Form Authentication link on the Welcome Page");
        click(formAuthenticationLinkLocator);
        return new LoginPage(driver, log);

    }


    public CheckboxesPage clickCheckboxesLink() {
        log.info("Clicking Checkboxes link on Welcome Page");
        click(checkboxesLinkLocator);
        return new CheckboxesPage(driver, log);
    }

    public DropdownPage clickDropdownLink() {
        log.info("Clicking Dropdown link on Welcome page!");
        click(dropdownLinkLocator);
        return new DropdownPage(driver, log);
    }

    public JavaSriptAlertsPage clickJavaScriptAlertsLink() {
        log.info("Clicking JavaScript Alerts link on the Welcome page!");
        click(javascriptAlertsLinkLocator);
        return new JavaSriptAlertsPage(driver, log);
    }

    public OpeningNewWindowPage clickMultipleWindowsLink() {
        log.info("Clicking Multiple Windows link on the Welcome page!");
        click(multipleWindowsLinkLocator);
        return new OpeningNewWindowPage(driver, log);
    }

    public EditorPage clickWYSIWYGEditorLink() {
        log.info("Clicking WYSIWYG Editor link on the Welcome page!");
        click(wysiwygEditorLocator);
        return new EditorPage(driver, log);
    }

    public DragAndDropPage cdragAndDropLink() {
        log.info("Clicking dragAndDrop link on the Welcome page!");
        click(dragAndDropLocator);
        return new DragAndDropPage(driver, log);
    }

    public HoverPage clickHoversLink(){
        log.info("Clicking hovers link on the Welcome page!");
        click(hoversLocator);
        return new HoverPage(driver, log);
    }
}
