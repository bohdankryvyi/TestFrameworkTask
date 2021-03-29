package org.example.pages;

import org.example.utils.Transliteration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {
    //constructor for  fields initialization
    public WebDriver driver;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //locator for username that is logged in
    @FindBy(xpath = "//*[@id=\"content\"]/header/div[1]/div/div[2]/div[1]/p")
    private WebElement userTitle;


    // locator for logging out
    @FindBy(xpath = "//*[@id=\"login__logout\"]/b")
    private WebElement logoutBtn;

    // locator for "написати листа"
    @FindBy(xpath = "//*[@id=\"content\"]/aside/button")
    private WebElement writeMail;

    // locator for "to"
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[2]/section[1]/div[1]/div[4]/input[2]")
    private WebElement sendTo;

    // locator for "Theme"
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[2]/section[1]/div[4]/div[2]/input")
    private WebElement theme;

    // locator for send button
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[2]/div/button[1]")
    private WebElement sendButton;

    // locator for sent mails button
    @FindBy(xpath = "//*[@id=\"10001\"]/span[4]")
    private WebElement sentMails;

    // locator for getting name of button after sending the mail
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[3]/div[1]/div[2]/button[1]")
    private WebElement endButton;


    //method for getting user name
    public String getUserName() {
        //сторінка довго грузить, щоб тест не падав
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/header/div[1]/div/div[2]/div[1]/p")));
        String userName = userTitle.getText();
        return userName;
    }

    //method for getting ukrainian button name
    public String getButtonName() {
        //сторінка довго грузить, щоб тест не падав
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"screens\"]/div/div[3]/div[1]/div[2]/button[1]")));
        String buttonName = endButton.getText();
        // transform cyrillic to latin
        Transliteration.transliterate(buttonName);
        return Transliteration.transliterate(buttonName);
    }

    public void setWriteMail() {
        writeMail.click();
    }

    public void inputSendTo(String send) {
        sendTo.sendKeys(send);
    }

    public void inputTheme(String mailtheme) {
        theme.sendKeys(mailtheme);
    }

    public void sendMail() {
        sendButton.click();
    }

    public void sentMails() {
        sentMails.click();
    }

    //method for clicking on user title
    public void entryMenu() {
        userTitle.click();
    }

    //method for clicking log out button
    public void userLogout() {
        logoutBtn.click();
    }
}

/**
 * MailPage should be updated according to all existing web elements on it
 * Currently only some elements are described used in the test
 */
