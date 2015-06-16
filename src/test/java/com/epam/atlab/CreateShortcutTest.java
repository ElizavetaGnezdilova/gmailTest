package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/15/2015.
 */
public class CreateShortcutTest extends GmailTests {

    private final String SHORTCUT_NAME = "My inserted shortcut";

    @BeforeMethod
    public void initBrowser() {
        super.initBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
    }

    @Test(description = "GM#1.8")
    public void createShortcutTest() {
        steps.createInsertedShortcut(SHORTCUT_NAME);
        Assert.assertTrue(steps.isShortcutWasCreated(SHORTCUT_NAME));


    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteShortcut(SHORTCUT_NAME);
        super.stopBrowser();

    }
}
