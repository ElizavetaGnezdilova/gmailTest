package com.epam.atlab;

import com.epam.atlab.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class ThemesTest extends GmailTests {
    private final String ATTACHMENT_FILE_NOT_IMAGE = "AttachmentFile.txt";
    private String filePath;
    Utils util;

    @BeforeMethod
    public void initBrowser() {
        util = new Utils();
        filePath = util.createFile(ATTACHMENT_FILE_NOT_IMAGE, (long) 280);
        super.initBrowser();

    }

    @Test(description = "GM#1.4")
    public void themes() {
        steps.loginGmail(USER_1, PASSWORD_USER1);
        Assert.assertTrue(steps.isNotSetBackgroundImage(filePath));
    }

    @AfterMethod
    public void stopBrowser() {
        util.deleteFile(filePath);
        super.stopBrowser();
    }

}
