package com.epam.atlab.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;


/**
 * Created by Elizaveta_Gnezdilova on 6/1/2015.
 */
public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://mail.google.com/mail/";

    @FindBy(xpath = "//div[@gh='cm']")
    private WebElement buttonCreateNewMessage;

    @FindBy(xpath = "//textarea[@aria-label='To']")
    private WebElement messageTo;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement messageSubject;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement messageContent;

    @FindBy(xpath = "//div[contains(text(),'Send')][@role='button']")
    private WebElement buttonSend;

    @FindBy(xpath = "//div[@role='main']//div[@class='Cp']//table")
    private WebElement tableOfMessages;

    @FindBy(xpath = "//div[@aria-label='Report spam']")
    private WebElement reportSpam;

    @FindBy(xpath = "//div[@gh='tm']//span[@role='checkbox']")
    private WebElement buttonSelectAll;

    @FindBy(xpath = "//div[@role='button'][contains(text(),'Not spam')]")
    private WebElement reportNotSpam;

    @FindBy(xpath = "//div[@aria-label='Delete']")
    private WebElement buttonDelete;

    @FindBy(xpath = "//div[@gh='mtb']//div[@act='10']")
    private WebElement buttonDeleteOpen;

    @FindBy(xpath = "//span[@class='x2']")
    private WebElement buttonDeleteAllFromTrash;

    @FindBy(xpath = "//div[@class='Kj-JD'][last()]//button[@name='ok']")
    private WebElement buttonConfirmDeleteAllFromTrash;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3']")
    private WebElement buttonSettings;

    @FindBy(xpath = "//div[@role='menu'][last()]//div[contains(text(),'Settings')]")
    private WebElement menuItemSettings;

    @FindBy(xpath = "//div[@role='menu'][last()]//div[contains(text(),'Themes')]")
    private WebElement menuItemThemes;

    @FindBy(xpath = "//a[contains(text(),'isolated.mail.google')]")
    private WebElement linkToConfirm;

    @FindBy(xpath = "//div[@aria-label='Attach files']")
    private WebElement buttonAttachFiles;

    @FindBy(xpath = "//div[@role='alertdialog']//button[@name='cancel']")
    private WebElement buttonCancelAlertDialog;

    @FindBy(xpath = "//div[@command='+emoticon']")
    private WebElement buttonAddEmoticon;

    @FindBy(xpath = "//div[@goomoji='B68']")
    private WebElement emoticon1;

    @FindBy(xpath = "//div[@goomoji='1E3']")
    private WebElement emoticon2;

    @FindBy(xpath = "//div[@role='dialog']//div[@class='T-I J-J5-Ji T-I-atl L3']")
    private WebElement buttonInsertEmoticons;

    @FindBy(xpath = "//div[@class='a3s']//img")
    private List<WebElement> contentsOfMessage;

    @FindBy(xpath = "//div[@id='gbwa']//a")
    private WebElement buttonToGoogleApps;

    @FindBy(id = "gb24")
    private WebElement buttonToGoogleAppsCalendar;

    @FindBy(xpath = "//a[@title='My shortcut']/../..")
    private WebElement labelMyShortcut;

    @FindBy(xpath = "//div[@gh='cl']//div[@class='p8']")
    private WebElement showMyShortcutAvailableActions;

    @FindBy(xpath = "//div[@role='menuitem']/div[@class='J-N-Jz']/span/..")
    private WebElement myShortcutAvailableAction_LabelColor;


    @FindBy(xpath = "//div[contains(text(),'Remove color')]")
    private WebElement labelColor_Default;

    @FindBy(xpath = "//div[@class='Kj-JD-Jz']/div[last()]/input")
    private WebElement myShortcutAvailableAction_LabelColor_SetColorForLabelAndSublabel;

    @FindBy(xpath = "//button[@name='sc']")
    private WebElement buttonSetLabelColor;

    @FindBy(xpath = "//div[@style='background-color: #2da2bb']")
    private List<WebElement> listOfSameShortcutLabelColor;

    @FindBy(xpath = "//div[contains(text(),'Add sublabel')]")
    private WebElement buttonAddInsertedShortcut;

    @FindBy(xpath = "//div[@class='Kj-JD-Jz']/input")
    private WebElement inputNameNewShortcut;

    @FindBy(xpath = "//div[@class='Kj-JD'][last()]//button[@name='ok']")
    private WebElement buttonConfirmCreateInsertedShortcut;

    @FindBy(xpath = "//div[@class='TH aii J-J5-Ji']")
    private WebElement buttonShowInsertedMyShortcut;

    @FindBy(xpath = "//div[@role='menuitem']//div[contains(text(),'Remove label')]")
    private WebElement buttonRemoveShortcut;

    @FindBy(xpath = "//button[@name='ok']")
    private WebElement buttonConfirmDeleteInsertedShortcut;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCancelAlertDialod() {
        buttonCancelAlertDialog.click();
    }

    public void clickOnCreateNewMessage() {
        buttonCreateNewMessage.click();
    }

    public void sendMessage(String recipient, String subject, String text) {
        clickOnCreateNewMessage();
        messageTo.click();
        messageTo.sendKeys(recipient);
        messageSubject.sendKeys(subject);
        messageContent.sendKeys(text);
        buttonSend.click();

    }


    public void sendMessage(String recipient, String subject, String text, String fileNameToAttach) {
        clickOnCreateNewMessage();
        messageTo.click();
        messageTo.sendKeys(recipient);
        messageSubject.sendKeys(subject);
        messageContent.sendKeys(text);
        attachFileToMessage(fileNameToAttach);
        buttonSend.click();
    }

    public void sendMessage(String recipient, String subject) {
        clickOnCreateNewMessage();
        messageTo.sendKeys(recipient);
        messageSubject.sendKeys(subject);
        buttonAddEmoticon.click();
        new Actions(driver).keyDown(Keys.SHIFT).click(emoticon1).click(emoticon2).keyUp(Keys.SHIFT).perform();
        buttonInsertEmoticons.click();
        buttonSend.click();

    }


    public void attachFileToMessageAndCloseWarningMessage(String fileName) {
        clickOnCreateNewMessage();
        attachFileToMessage(fileName);
        clickOnCancelAlertDialod();
    }

    public void moveMessageToSpam(String spamEmail) {

        tableOfMessages.findElement(
                By.xpath(".//span[@email='" + spamEmail + "']/../../../td[2]")).click();

        reportSpam.click();

    }

    public void moveMessagesFromSpamToInbox() {
        buttonSelectAll.click();
        reportNotSpam.click();
    }


    public WebElement findMessageBySubject(String subject) {
        return driver.findElement(By.xpath("//tr[contains(.,'" + subject + "')]"));
    }

    public void deleteAllMessages() {
        buttonSelectAll.click();
        buttonDelete.click();
    }


    public void deleteAllFromTrash() {
        buttonDeleteAllFromTrash.click();
        buttonConfirmDeleteAllFromTrash.click();
    }

    public SettingsPage goToSettingsPage() {
        buttonSettings.click();
        menuItemSettings.click();
        return new SettingsPage(driver);
    }

    public SettingsPage goToThemesPage() {
        buttonSettings.click();
        menuItemThemes.click();
        return new SettingsPage(driver);
    }

    public CalendarPage goToCalendarPage() {
        buttonToGoogleApps.click();
        buttonToGoogleAppsCalendar.click();
        return new CalendarPage(driver);
    }

    public void openPageByURL(String url) {
        driver.navigate().to(url);
    }


    public void clickLinkToConfirmForward() {
        linkToConfirm.click();
    }

    public void attachFileToMessage(String fileName) {
        buttonAttachFiles.click();
        try {
            StringSelection filePathSelection = new StringSelection(fileName);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
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
    }

    public int numberOfEmoticonInMessage() {
        List<WebElement> content = contentsOfMessage;
        return content.size();
    }

    public void deleteOpenMessage() {
        buttonDeleteOpen.click();
    }

    public void createNewInsertedShortcut(String nameShortcut) {
        new Actions(driver).moveToElement(labelMyShortcut).build().perform();
        showMyShortcutAvailableActions.click();
        buttonAddInsertedShortcut.click();
        inputNameNewShortcut.sendKeys(nameShortcut);
        buttonConfirmCreateInsertedShortcut.click();
    }

    public boolean isShortcutWasCreated(String nameShortcut) {
        return driver.findElement(By.xpath("//a[@title='" + nameShortcut + "']")).isDisplayed();
    }

    public void deleteShortcut(String nameShortcut) {
        new Actions(driver).moveToElement(
                driver.findElement(By.xpath(
                                "//a[@title='" + nameShortcut + "']/../.."
                        )
                )
        ).build().perform();
        driver.findElement(By.xpath(
                "//a[@title='" + nameShortcut + "']/../../../div[last()]//div[@class='p8']")).click();

        buttonRemoveShortcut.click();
        buttonConfirmDeleteInsertedShortcut.click();
    }

    public boolean isNewMessageHasSignature(String signature) {
        clickOnCreateNewMessage();
        return messageContent.getText().equals(signature);
    }


    public void markMessageWithStar(String username) {
        tableOfMessages.findElement(
                By.xpath(".//span[@email='" + username + "']/../../../td[3]")).click();
    }

    public void changeLabelColor(String labelColor) {
        new Actions(driver).moveToElement(labelMyShortcut).build().perform();
        showMyShortcutAvailableActions.click();
        new Actions(driver).moveToElement(myShortcutAvailableAction_LabelColor).build().perform();
        if (labelColor.equals("Default")) {
            labelColor_Default.click();
        } else {
            driver.findElement(By.xpath("//div[@title='" + labelColor + "']")).click();

        }
        myShortcutAvailableAction_LabelColor_SetColorForLabelAndSublabel.click();
        buttonSetLabelColor.click();
    }

    public boolean isChangedBothLabelColor() {

        return listOfSameShortcutLabelColor.size() == 2;

    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }


    public boolean isShortcutsWereDeleted() {
        return driver.findElement(By.xpath("//div[contains(text(),'2 labels were removed.')]")).isDisplayed();
    }
}
