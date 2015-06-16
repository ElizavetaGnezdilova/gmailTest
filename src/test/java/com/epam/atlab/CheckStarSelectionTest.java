package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/15/2015.
 */
public class CheckStarSelectionTest extends GmailTests {
    private final String MESSAGE_SUBJECT = "star";
    private final String MESSAGE_BODY = "body";

    @BeforeMethod
    public void initBrowser() {
        super.initBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.sendMessage(USER_2, MESSAGE_SUBJECT, MESSAGE_BODY);
        steps.reloadBrowser();
        steps.loginGmail(USER_2, PASSWORD_USER2);
    }

    @Test(description = "GM#1.13")
    public void checkStarSelectionTest() {
        steps.markMessageAsStarred(USER_1);
        Assert.assertTrue(steps.isMessageWithSubject(STARRED_URL, MESSAGE_SUBJECT));
    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteAllMessagesFromInbox();
        super.stopBrowser();
    }
}
