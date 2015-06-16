package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class SendMailWithInviteTest extends GmailTests {
    @Test(description = "GM#1.7")
    public void SendMailWithInviteTest() {
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.createNewCalendarEvent("this is meeting", "Minsk, K1/2, 104", "this is description", USER_3);
        steps.reloadBrowser();
        steps.loginGmail(USER_3, PASSWORD_USER3);
        Assert.assertTrue(steps.isMessageWithSubject(INBOX_URL, "Invitation"));
    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteAllMessagesFromInbox();
        steps.reloadBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.deleteCalendarEvent("this is meeting");
        super.stopBrowser();
    }
}
