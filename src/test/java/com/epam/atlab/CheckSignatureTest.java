package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/15/2015.
 */
public class CheckSignatureTest extends GmailTests {
    private final String SIGNATURE = "Kind regards";

    @BeforeMethod
    public void initBrowser() {
        super.initBrowser();
        steps.loginGmail(USER_1, PASSWORD_USER1);
    }

    @Test(description = "GM#1.12")
    public void checkSignatureTest() {
        Assert.assertTrue(steps.createAndCheckSignature(SIGNATURE));
    }

    @AfterMethod
    public void stopBrowser() {
        steps.deleteSignature();
        super.stopBrowser();
    }
}
