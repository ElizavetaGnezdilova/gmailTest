package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/15/2015.
 */
public class ChangeShortcutTest extends GmailTests {
    private final String DEFAULT_COLOR = "Default";
    private final String NOT_DEFAULT_COLOR = "RGB (45, 162, 187)";
    private final String SHORTCUT_NAME = "Inserted Shortcut";

    @BeforeMethod
    public void initBrowser() {
        super.initBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.createInsertedShortcut(SHORTCUT_NAME);
    }

    @Test(description = "GM#1.9")
    public void changeShortcutTest() {
        steps.changeShortcutColor(NOT_DEFAULT_COLOR);
        Assert.assertTrue(steps.isChangedShortcutLabelColor());

    }

    @AfterMethod
    public void stopBrowser() {
        steps.changeShortcutColor(DEFAULT_COLOR);
        steps.reloadBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.deleteShortcut(SHORTCUT_NAME);
        super.stopBrowser();
    }
}
