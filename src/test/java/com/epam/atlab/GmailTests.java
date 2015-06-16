package com.epam.atlab;


import com.epam.atlab.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GmailTests {
    protected Steps steps = new Steps();

    protected final String USER_1 = "test.1.user.test@gmail.com";
    protected final String PASSWORD_USER1 = "passworduser1";
    protected final String USER_2 = "test.2.user.test@gmail.com";
    protected final String PASSWORD_USER2 = "passworduser2";
    protected final String USER_3 = "test.3.user.test@gmail.com";
    protected final String PASSWORD_USER3 = "passworduser3";
    protected final String INBOX_URL = "https://mail.google.com/mail/";
    protected final String TRASH_URL = "https://mail.google.com/mail/#trash";
    protected final String STARRED_URL = "https://mail.google.com/mail/#starred";
    protected final String SPAM_URL = "https://mail.google.com/mail/#spam";

    @BeforeMethod
    public void initBrowser() {
        steps.initBrowser();
    }


    @AfterMethod
    public void stopBrowser() {
        steps.closeDriver();
    }

}
