package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class LoginPage {

    public WebDriver driver;

    /**
     * constructor for  fields initialization
     */
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * locator for login input field
     */
    @FindBy(xpath = "/html/body/div/div/main/div[1]/form/div[1]/div/label/input")
    private WebElement loginField;

    /**
     * locator for password input field
     */
    @FindBy(xpath = "/html/body/div/div/main/div[1]/form/div[2]/div/label/input")
    private WebElement passwdField;

    /**
     * locator for logging button
     */
    @FindBy(xpath = "/html/body/div/div/main/div[1]/form/button")
    private WebElement loginBtn;

    /**
     * locator for unsuccessfull message
     */
    @FindBy(xpath = "/html/body/div/div/main/form/p")
    private WebElement incData;

    /**
     * locator for recaptcha checkbox
     */
    @FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]/div[1]")
    private WebElement recaptcha;

    /**
     * method for setting the login
     */
    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    /**
     * method for setting the password
     */
    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    /**
     * method for getting an error text
     * @return
     */
    public String errorText() {
        String label = incData.getText();
            return label;
    }

    /**
     * method for getting an error text
     */
    public void reCaptcha_click() {
        recaptcha.click();
    }



    /**
     * method for clicking the login button
     */
    public void clickLoginBtn() {
        loginBtn.click();
    }
}
