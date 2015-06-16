package com.epam.atlab;

import com.epam.atlab.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.rmi.CORBA.Util;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class ForwardTest extends GmailTests {
    private final String ATTACHMENT_FILE = "AttachmentFile.txt";
    Utils util;
    private String filePath;

    @BeforeMethod
    public void initBrowser() {
        util = new Utils();
        filePath = util.createFile(ATTACHMENT_FILE, (long) 280);
        super.initBrowser();

    }

    @Test(description = "GM#1.2")
    public void forwardTest() {
        steps.loginGmail(USER_2, PASSWORD_USER2);
        steps.addForwardingAddress(USER_3);

        steps.reloadBrowser();

        steps.loginGmail(USER_3, PASSWORD_USER3);
        steps.confirmForward();

        steps.reloadBrowser();

        steps.loginGmail(USER_2, PASSWORD_USER2);
        steps.setUpFilter(USER_1);

        steps.reloadBrowser();

        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.sendMessage(USER_2, "message with a attached file", "message", filePath);
        steps.sendMessage(USER_2, "message without a attached file", "message");

        steps.reloadBrowser();

        steps.loginGmail(USER_2, PASSWORD_USER2);
        Assert.assertTrue(steps.isMessageWithSubject(INBOX_URL, "message without a attached file"));
        Assert.assertTrue(steps.isMessageWithSubject(TRASH_URL, "message with a attached file"));

        steps.reloadBrowser();

        steps.loginGmail(USER_3, PASSWORD_USER3);
        Assert.assertTrue(steps.isMessageWithSubject(TRASH_URL, "message without a attached file"));

    }

    @AfterMethod
    public void stopBrowser() {
        util.deleteFile(filePath);
        steps.deleteAllMessagesFromInbox();
        steps.reloadBrowser();
        steps.loginGmail(USER_2, PASSWORD_USER2);
        steps.deleteMessagesFromInboxAndTrashAndDeleteForward();
        super.stopBrowser();
    }
}
