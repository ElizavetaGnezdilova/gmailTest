package com.epam.atlab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.lang.model.element.Name;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by Elizaveta_Gnezdilova on 6/4/2015.
 */
public class SettingsPage extends AbstractPage {
    private final String BASE_URL = "https://mail.google.com/mail/#settings";

    @FindBy(xpath = "//a[contains(text(),'Forwarding and POP/IMAP')]")
    private WebElement buttonForwarding;

    @FindBy(xpath = " //input[@value='Add a forwarding address']")
    private WebElement addForwardingAddress;

    @FindBy(xpath = "//div[@role='alertdialog']//input[@type='text']")
    private WebElement forwardingAddress;

    @FindBy(xpath = "//button[@name='next']")
    private WebElement buttonNext;

    @FindBy(xpath = "//div[@role='alertdialog']//iframe")
    private WebElement iframeForwarding;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement iframeForwardingSubmit;

    @FindBy(xpath = "//input[@name='sx_em'][@value='1']")
    private WebElement rbuttonForwardCopyOfIncomingMail;

    @FindBy(xpath = "//option[@value='2']")
    private WebElement deleteForwardCopyOfIncomingMail;

    @FindBy(xpath = "//div[@class='Kj-JD'][last()]//button[@name='ok']")
    private WebElement buttonConfirmDeleteForward;

    @FindBy(xpath = "//button[@guidedhelpid='save_changes_button']")
    private WebElement buttonSaveChanges;

    @FindBy(xpath = "//span[@act='filter']")
    private WebElement buttonCreatingFilter;

    @FindBy(xpath = "//label[contains(text(),'From')]/../../span[last()]")
    private WebElement inputFilterFrom;

    @FindBy(xpath = "//label[contains(text(),'Has attachment')]/../input")
    private WebElement checkboxFilterHasAttachment;

    @FindBy(xpath = "//div[contains(text(),'Create filter with this search')]")
    private WebElement buttonCreatingFilterWithParameters;

    @FindBy(xpath = "//label[contains(text(),'Delete it')]/../input")
    private WebElement checkboxFilterDeleteIt;

    @FindBy(xpath = "//label[contains(text(),'Always mark it as important')]/../input")
    private WebElement checkboxMarkItAsImportant;

    @FindBy(xpath = "//div[contains(text(),'Create filter')]")
    private WebElement buttonCreateFilter;

    @FindBy(xpath = "//input[@name='customlight']/..")
    private WebElement selectYourBackgroundImage;

    @FindBy(xpath = "//iframe[@class='KA-JQ']")
    private WebElement iframeSelectImage;

    @FindBy(xpath = "//div[contains(text(),'Upload a photo')]")
    private WebElement uploadPhoto;

    @FindBy(xpath = "//div[@class='Xf-dn-wn']//div[@role='button']")
    private WebElement uploadPhotoFromComputer;

    @FindBy(xpath = "//div[@role='status']")
    private WebElement statusOfUploading;

    @FindBy(xpath = "//span[contains(text(),'Beach')]")
    private WebElement themeBeach;

    @FindBy(xpath = "//div[contains(text(),'Your preferences have been saved.')]")
    private WebElement statusOfChangeTheme;

    @FindBy(xpath = "//span[contains(text(),'Light')]")
    private WebElement themeDefault;

    @FindBy(xpath = "//div[@aria-label='Signature']")
    private WebElement signatureText;

    @FindBy(xpath = "//label[contains(text(),'No signature')]")
    private WebElement buttonNoSignature;

    @FindBy(xpath = "//label[contains(text(),'Vacation responder on')]")
    private WebElement buttonOnVacationResponder;

    @FindBy(xpath = "//label[contains(text(),'Vacation responder off')]")
    private WebElement buttonOffVacationResponder;

    @FindBy(xpath = "//input[@aria-label='Subject']")
    private WebElement inputVacationResponderSubject;

    @FindBy(xpath = "//div[@aria-label='Vacation responder']")
    private WebElement inputVacationResponderMessage;


    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public void addForwardingAddress(String email) {

        buttonForwarding.click();
        addForwardingAddress.click();
        forwardingAddress.sendKeys(email);
        buttonNext.click();
        driver.switchTo().frame(iframeForwarding);
        iframeForwardingSubmit.click();

    }

    public void setForwardCopyOfIncomingMail() {
        buttonForwarding.click();
        rbuttonForwardCopyOfIncomingMail.click();
        buttonSaveChanges.click();
    }

    public void setFilter(String username) {
        buttonForwarding.click();
        buttonCreatingFilter.click();
        inputFilterFrom.sendKeys(username);
        checkboxFilterHasAttachment.click();
        buttonCreatingFilterWithParameters.click();
        checkboxMarkItAsImportant.click();
        checkboxFilterDeleteIt.click();
        buttonCreateFilter.click();
    }

    public void deleteForward() {
        buttonForwarding.click();
        deleteForwardCopyOfIncomingMail.click();
        buttonConfirmDeleteForward.click();
    }

    public boolean isNotSetBackgroundImage(String imageName) {
        selectYourBackgroundImage.click();
        driver.switchTo().frame(iframeSelectImage);
        uploadPhoto.click();
        uploadPhotoFromComputer.click();
        try {
            StringSelection imagePathSelection = new StringSelection(imageName);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imagePathSelection, null);
            Robot robot = new Robot();
            robot.setAutoDelay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.setAutoDelay(250);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver.findElement(By.xpath("//div[contains(text(),'not supported for upload')]")).isDisplayed();


    }

    public void createSignature(String signature) {
        signatureText.click();
        signatureText.sendKeys(signature);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttonSaveChanges.click();
    }

    public void deleteSignature() {
        signatureText.clear();
        buttonNoSignature.click();
        buttonSaveChanges.click();
    }


    public boolean isSetBackgroundImage() {
        themeBeach.click();
        return statusOfChangeTheme.isDisplayed();
    }

    public void setVacationResponder(String subject, String message) {
        buttonOnVacationResponder.click();
        inputVacationResponderSubject.sendKeys(subject);

        inputVacationResponderMessage.sendKeys(message);
        buttonSaveChanges.click();

    }

    public void resetVacationResponder() {
        inputVacationResponderSubject.clear();
        inputVacationResponderMessage.clear();
        buttonOffVacationResponder.click();
        buttonSaveChanges.click();
    }

    public void setUpDefaultTheme() {
        themeDefault.click();
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

}
