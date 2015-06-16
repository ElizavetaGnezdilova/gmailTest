package com.epam.atlab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Elizaveta_Gnezdilova on 6/1/2015.
 */
public class LoginPage extends AbstractPage {

    private final String BASE_URL = "https://mail.google.com/mail/";

    @FindBy(id = "Email")
    private WebElement inputLogin;

    @FindBy(id = "next")
    private WebElement inputPasswordShow;

    @FindBy(id = "Passwd")
    private WebElement inputPassword;

    @FindBy(id = "signIn")
    private WebElement buttonSubmit;


    public LoginPage(WebDriver driver) {
        super(driver);

    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);

    }

    public void login(String username, String password) {
        inputLogin.sendKeys(username);
        inputPasswordShow.click();
        inputPassword.sendKeys(password);
        buttonSubmit.click();

    }


}
