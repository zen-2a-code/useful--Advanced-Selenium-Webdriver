package com.herokuapp.theinternet.base;

import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest {

    protected void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @DataProvider(name="files")
    protected Object[][] files() {
        return new Object[][]{
                {1, "index.html"}, {2, "logo.png"}, {3, "text.txt"}
        };
    }
}
