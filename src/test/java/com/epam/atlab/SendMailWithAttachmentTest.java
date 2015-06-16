package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class SendMailWithAttachmentTest extends GmailTests {
    @Test(description = "GM#1.5")
    public void sendMailWithAttachment() {
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.sendMessage(USER_3, "Message with emoticons");
        steps.reloadBrowser();
        steps.loginGmail(USER_3, PASSWORD_USER3);
        Assert.assertTrue(steps.isMessageWithSubjectAndEmoticonInInbox("Message with emoticons", 2));
    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteOpenMessage();
        super.stopBrowser();
    }
}
