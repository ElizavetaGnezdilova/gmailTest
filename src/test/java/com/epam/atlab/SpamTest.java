package com.epam.atlab;

import com.epam.atlab.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class SpamTest extends GmailTests {

    @BeforeMethod
    public void initBrowser() {

        steps.initBrowser();
    }

    @Test(description = "GM#1.1")
    public void spam() {

        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.sendMessage(USER_2, "subject", "body");

        steps.reloadBrowser();

        steps.loginGmail(USER_2, PASSWORD_USER2);
        steps.moveMessageToSpam(USER_1);

        steps.reloadBrowser();

        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.sendMessage(USER_2, "check subject", "body");

        steps.reloadBrowser();

        steps.loginGmail(USER_2, PASSWORD_USER2);
        Assert.assertTrue(steps.isMessageWithSubject(SPAM_URL, "check"));


    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteAllFromInboxAndSpam();
        super.stopBrowser();

    }

}
