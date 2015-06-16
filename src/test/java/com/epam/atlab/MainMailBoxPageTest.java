package com.epam.atlab;

import com.epam.atlab.utils.Utils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class MainMailBoxPageTest extends GmailTests {
    private final String ATTACHMENT_FILE26MB = "AttachmentFile26Mb.txt";
    private String filePath;
    Utils util;

    @BeforeMethod
    public void initBrowser() {
        util = new Utils();
        filePath = util.createFile(ATTACHMENT_FILE26MB, (long) 28000000);
        super.initBrowser();

    }

    @Test(description = "GM#1.3")
    public void mainMailBoxPage() {
        steps.loginGmail(USER_1, PASSWORD_USER1);
        steps.attachFileToMessageAndCloseWarningMessage(filePath);
    }

    @AfterMethod
    public void stopBrowser() {
        util.deleteFile(filePath);
        super.stopBrowser();
    }
}
