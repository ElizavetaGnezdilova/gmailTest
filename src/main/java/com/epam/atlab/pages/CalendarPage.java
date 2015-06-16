package com.epam.atlab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class CalendarPage extends AbstractPage {
    private final String BASE_URL = " https://www.google.com/calendar/";


    @FindBy(xpath = "//div[@id='sidebar']//div[@role='button'][1]")
    private WebElement createEvent;

    @FindBy(xpath = "//input[@title='Event title']")
    private WebElement inputEventTitle;

    @FindBy(xpath = "//span[@class='group']/input[@type='checkbox']")
    private WebElement checkboxAllDay;

    @FindBy(xpath = "//div[@class='ui-sch']/input")
    private WebElement inputEventWhere;

    @FindBy(xpath = "//div[@class='ui-sch']/textarea")
    private WebElement inputEventDescription;

    @FindBy(xpath = "//div[@class='ep-gs']//input")
    private WebElement fieldAddGuests;

    @FindBy(xpath = "//div[@class='ep-gs']//div[@role='button']")
    private WebElement buttonAddGuests;

    @FindBy(xpath = "//div[@class='action-btn-wrapper ep-ea-btn-wrapper']/div")
    private WebElement buttonSaveEvent;

    @FindBy(xpath = "//button[@name='yes']")
    private WebElement buttonSendInvitation;

    @FindBy(xpath = "//.[contains(text(),'Delete')]")
    private WebElement buttonDeleteEvent;

    @FindBy(xpath = "//div[@class='cal-dialog-buttons']/button[@name='no']")
    private WebElement buttonConfirmDeleteEvent;


    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    public void createNewEvent(String eventTitle, String eventLocation, String eventDescription, String guestName) {
        createEvent.click();
        inputEventTitle.sendKeys(eventTitle);
        checkboxAllDay.click();
        inputEventWhere.sendKeys(eventLocation);
        inputEventDescription.sendKeys(eventDescription);
        fieldAddGuests.sendKeys(guestName);
        buttonAddGuests.click();
        buttonSaveEvent.click();
        buttonSendInvitation.click();
    }


    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void deleteEventByTitle(String eventTitle) {
        driver.findElement(By.xpath("//span[contains(text(),'" + eventTitle + "')]")).click();
        buttonDeleteEvent.click();
        buttonConfirmDeleteEvent.click();
    }
}
