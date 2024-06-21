package com.herokuapp.theinternet.draganddroptests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.DragAndDropPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTests extends BaseTest {

    @Test
    public void dragLeftToRightTest() {
        log.info("Starting dragLeftToRightTest");

        // Open Welcome page
        WelcomePage wp = new WelcomePage(driver, log);
        wp.openPage();
        // Clicking on drag and drop link
        DragAndDropPage dragAndDropPage = wp.cdragAndDropLink();

        // Drag left box to right box
        dragAndDropPage.dragLefttoRight();



        // wait for column to change text, we only need to wait for one of them
//        dragAndDropPage.waitForColumnTextToChange("left", "B");

        // Verify correct headers in correct boxes - After the drag B should be in Left column and A in the right.
        String leftColumnText = dragAndDropPage.gettingLeftColumnText();
        String rightColumnText = dragAndDropPage.gettingRightColumnText();

        Assert.assertEquals(leftColumnText, "B", "Left Column should have a header with text B, " +
                "but instead the header has the text " + leftColumnText);
        Assert.assertEquals(rightColumnText, "A", "Right Column should have a header with text A, " +
                "but instead the header has the text " + rightColumnText);

    }
}
