package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HoverPage extends BasePageObject {
    private By avatarsLocator = By.xpath("//div[@class='figure']");
    private By viewProfileLocator = By.xpath(".//a[text()='View profile']");
//    private WebElement selectedAvatar;

    public HoverPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openUserProfile(int avatarNum) {
        List<WebElement> avatars = findAll(this.avatarsLocator);
        WebElement selectedAvatar = avatars.get(avatarNum - 1); // as index starts from 0
        hoverOverElement(selectedAvatar);
        selectedAvatar.findElement(viewProfileLocator).click();
    }

    // This is a good practice if we plan to hover on the avatar and do different task, but in this case we are always
    // going to click on View profile that is why we are commenting them and using a single method - even though for 2
    // Actions. Also, commenting selectAvatar variable

    /*
    public void hoverOverAvatar(int avatarNum){
        List<WebElement> avatars = findAll(avatarsLocator);
        this.selectedAvatar = avatars.get(avatarNum -1); // as index starts from 0
        hoverOverElement(this.selectedAvatar);
    }

    public void clickViewProfile(){
        this.selectedAvatar.findElement(this.viewProfileLocator).click();
    }
     */
}
