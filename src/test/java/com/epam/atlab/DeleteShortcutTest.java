package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/15/2015.
 */
public class DeleteShortcutTest extends GmailTests {
    private final String SHORTCUT_NAME = "My inserted shortcut";

    @BeforeMethod
    public void initBrowser() {
        super.initBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.createInsertedShortcut(SHORTCUT_NAME);
    }

    @Test(description = "GM#1.10")
    public void deleteShortcutTest() {

        steps.deleteShortcut("My shortcut");
        Assert.assertTrue(steps.isShortcutsWereDeleted());
    }

}
