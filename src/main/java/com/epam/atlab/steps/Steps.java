package com.epam.atlab.steps;

import com.epam.atlab.pages.CalendarPage;
import com.epam.atlab.pages.LoginPage;
import com.epam.atlab.pages.MainPage;
import com.epam.atlab.pages.SettingsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Elizaveta_Gnezdilova on 6/2/2015.
 */
public class Steps {

    private final String SPAM_URL = "https://mail.google.com/mail/#spam";
    private final String TRASH_URL = "https://mail.google.com/mail/#trash";
    private WebDriver driver;

    public void initBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void closeDriver() {
        driver.quit();
    }

    public void reloadBrowser() {
        closeDriver();
        initBrowser();
    }

    public void loginGmail(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public void sendMessage(String recipient, String subject, String text) {
        MainPage page = new MainPage(driver);
        page.sendMessage(recipient, subject, text);
    }

    public void sendMessage(String recipient, String subject, String text, String fileNameToAttach) {
        MainPage page = new MainPage(driver);
        page.sendMessage(recipient, subject, text, fileNameToAttach);

    }

    public void sendMessage(String recipient, String subject) {
        MainPage page = new MainPage(driver);
        page.sendMessage(recipient, subject);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveMessageToSpam(String username) {
        MainPage page = new MainPage(driver);
        page.moveMessageToSpam(username);
    }


    public void deleteAllFromInboxAndSpam() {
        MainPage page = new MainPage(driver);
        page.openPageByURL(SPAM_URL);
        page.moveMessagesFromSpamToInbox();
        page.openPage();
        page.deleteAllMessages();

    }

    public void addForwardingAddress(String username) {
        MainPage page = new MainPage(driver);
        page.goToSettingsPage();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.addForwardingAddress(username);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void confirmForward() {
        MainPage page = new MainPage(driver);
        page.findMessageBySubject("Forwarding").click();
        page.clickLinkToConfirmForward();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setUpFilter(String filterMessagesFromUser) {
        MainPage page = new MainPage(driver);
        page.goToSettingsPage();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setForwardCopyOfIncomingMail();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.goToSettingsPage();
        settingsPage.setFilter(filterMessagesFromUser);

    }

    public void attachFileToMessageAndCloseWarningMessage(String fileName) {
        MainPage page = new MainPage(driver);
        page.attachFileToMessageAndCloseWarningMessage(fileName);

    }

    public boolean isMessageWithSubject(String folder, String subject) {
        MainPage page = new MainPage(driver);
        page.openPageByURL(folder);
        page.findMessageBySubject(subject);
        return true;
    }

    public boolean isMessageWithSubjectAndEmoticonInInbox(String subject, int numberOfEmoticon) {
        MainPage page = new MainPage(driver);
        page.findMessageBySubject(subject).click();
        return page.numberOfEmoticonInMessage() == numberOfEmoticon;


    }

    public void deleteMessagesFromInboxAndTrashAndDeleteForward() {
        deleteAllMessagesFromInbox();
        MainPage page = new MainPage(driver);
        page.openPageByURL(TRASH_URL);
        page.deleteAllFromTrash();
        page.openPage();
        SettingsPage settingsPage = page.goToSettingsPage();
        settingsPage.deleteForward();
    }

    public void deleteAllMessagesFromInbox() {
        MainPage page = new MainPage(driver);
        page.openPage();
        page.deleteAllMessages();
    }

    public boolean isNotSetBackgroundImage(String fileName) {
        MainPage page = new MainPage(driver);
        SettingsPage settingsPage = page.goToThemesPage();
        return settingsPage.isNotSetBackgroundImage(fileName);

    }


    public void deleteOpenMessage() {
        MainPage page = new MainPage(driver);
        page.deleteOpenMessage();
    }

    public boolean setUpThemeBeach() {
        MainPage page = new MainPage(driver);
        SettingsPage settingsPage = page.goToThemesPage();
        return settingsPage.isSetBackgroundImage();


    }

    public void setUpDefaultTheme() {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setUpDefaultTheme();
    }

    public void createNewCalendarEvent(
            String eventTitle, String eventLocation, String eventDescription, String guestName) {
        MainPage page = new MainPage(driver);
        CalendarPage calendarPage = page.goToCalendarPage();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        calendarPage = new CalendarPage(driver);
        calendarPage.createNewEvent(eventTitle, eventLocation, eventDescription, guestName);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteCalendarEvent(String eventTitle) {
        MainPage page = new MainPage(driver);
        CalendarPage calendarPage = page.goToCalendarPage();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        calendarPage = new CalendarPage(driver);
        calendarPage.deleteEventByTitle(eventTitle);
    }

    public void createInsertedShortcut(String shortcutName) {
        MainPage page = new MainPage(driver);
        page.createNewInsertedShortcut(shortcutName);
    }

    public boolean isShortcutWasCreated(String shortcutName) {
        MainPage page = new MainPage(driver);
        return page.isShortcutWasCreated(shortcutName);
    }

    public void deleteShortcut(String shortcutName) {
        MainPage page = new MainPage(driver);
        page.deleteShortcut(shortcutName);
    }

    public boolean isShortcutsWereDeleted() {
        MainPage page = new MainPage(driver);
        return page.isShortcutsWereDeleted();
    }

    public void moveMessageToInboxFromSpam(String userName) {
        MainPage page = new MainPage(driver);
        page.openPageByURL(SPAM_URL);
        page.moveMessagesFromSpamToInbox();
        page.openPage();
    }

    public boolean createAndCheckSignature(String signature) {
        MainPage page = new MainPage(driver);
        SettingsPage settingsPage = page.goToSettingsPage();
        settingsPage.createSignature(signature);
        return page.isNewMessageHasSignature(signature);
    }

    public void deleteSignature() {
        MainPage page = new MainPage(driver);
        SettingsPage settingsPage = page.goToSettingsPage();
        settingsPage.deleteSignature();
    }

    public void markMessageAsStarred(String userName) {
        MainPage page = new MainPage(driver);
        page.markMessageWithStar(userName);
    }


    public void changeShortcutColor(String defaultColorOrNot) {
        MainPage page = new MainPage(driver);
        page.changeLabelColor(defaultColorOrNot);
    }

    public boolean isChangedShortcutLabelColor() {
        MainPage page = new MainPage(driver);
        return page.isChangedBothLabelColor();

    }

    public void setVacationResponder(String subject, String message) {
        MainPage page = new MainPage(driver);
        SettingsPage settingsPage = page.goToSettingsPage();
        settingsPage.setVacationResponder(subject, message);

    }

    public void resetVacationResponder() {
        MainPage page = new MainPage(driver);
        SettingsPage settingsPage = page.goToSettingsPage();
        settingsPage.resetVacationResponder();
    }
}
