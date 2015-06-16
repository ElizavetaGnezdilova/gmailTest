package com.epam.atlab;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class ThemeTest extends GmailTests {
    @Test(description = "GM#1.6")
    public void themes() {
        steps.loginGmail(USER_2, PASSWORD_USER2);
        Assert.assertTrue(steps.setUpThemeBeach());
    }

    @AfterMethod
    public void stopBrowser() {
        steps.setUpDefaultTheme();
        super.stopBrowser();
    }
}
