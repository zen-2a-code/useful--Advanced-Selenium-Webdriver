package com.herokuapp.theinternet.editorTest;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.pages.EditorPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class EditorTest extends TestUtilities {

    @Test
    public void defaultEditorValueTest() {
        log.info("Starting defaultEditorValueTest");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Scroll to the bottom of the page
        welcomePage.scrollToBottom();

        // Click on WYSIWYG Editor link
        EditorPage editorPage = welcomePage.clickWYSIWYGEditorLink();

        // Get default editor text
        String editorText = editorPage.getEditorText();

        // Verification that new page contains expected text in source
        Assert.assertTrue(editorText.equals("Your content goes here."),
                "Editor default text is not expected. It is: " + editorText);
    }
}