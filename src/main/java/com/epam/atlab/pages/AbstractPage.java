package com.epam.atlab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Elizaveta_Gnezdilova on 6/1/2015.
 */
public abstract class AbstractPage {

    protected WebDriver driver;

    public abstract void openPage();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
