package org.example.tests;

import org.example.pages.LoginPage;
import org.example.pages.MailPage;
import org.example.utils.ConfProperties;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class LoginTest_2Invalid {
    public static LoginPage loginPage;
    public static WebDriver driver;



    @BeforeClass
    public static void setup()  {
        //setting driver
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        //show window on fullscreen
        driver.manage().window().maximize();
        //10 sec delay on test running to wait till element will be shown
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //get an URL for the test page from config file
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    /**
     * Test on authentication
     */
    @Test
    public void loginTest() {
        //login and password are taken from config file
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("incorrectPassword"));

        //click on login button
        loginPage.clickLoginBtn();
        //loginPage.reCaptcha_click();

        String currentURL = driver.getCurrentUrl();
        //compare current page URL whether we are on the same page and as a result we are not logged in
        Assert.assertEquals(ConfProperties.getProperty("loginpage"), currentURL); }
    /**
     * close the browser
     */
    @AfterClass
    public static void tearDown() {
        driver.quit(); }
}

