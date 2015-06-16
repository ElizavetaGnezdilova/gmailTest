package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/16/2015.
 */
public class CheckVacationTest extends GmailTests {

    private final String SUBJECT_VACATION_RESPONDER = "Vacation Responder";
    private final String MESSAGE_VACATION_RESPONDER = "Message";
    private final String SUBJECT = "Vacation Responder";
    private final String MESSAGE = "Message";

    @Test(description = "GM#1.14")
    public void checkVacationTest() {
        steps.loginGmail(USER_2, PASSWORD_USER2);
        steps.setVacationResponder(SUBJECT_VACATION_RESPONDER, MESSAGE_VACATION_RESPONDER);
        steps.reloadBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.sendMessage(USER_2, SUBJECT, MESSAGE);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(steps.isMessageWithSubject(INBOX_URL, SUBJECT_VACATION_RESPONDER));

    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteAllMessagesFromInbox();
        steps.reloadBrowser();
        steps.loginGmail(USER_2, PASSWORD_USER2);
        steps.deleteAllMessagesFromInbox();
        steps.resetVacationResponder();
        super.stopBrowser();
    }
}
