package org.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.ConfProperties;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.example.pages.LoginPage;
import org.example.pages.MailPage;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest_1Valid {
    public static LoginPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;

    private static final Logger logger = LogManager.getLogger(LoginTest_1Valid.class);

    @BeforeClass
    public static void setup() {
        //setting driver
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
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
        loginPage.inputPasswd(ConfProperties.getProperty("password"));

        //click on login button
        loginPage.clickLoginBtn();
        //get login that is shown
        String user = mailPage.getUserName();
        //compare login that is gotten  with other one from config file
        Assert.assertEquals(ConfProperties.getProperty("fullLogin"), user);

        //example of using logger. In this case lets log our user mail that is logged in and when
        logger.info("logged in user is:"+ user);
    }

    @Test
    public void sendMailTest() {
        mailPage.setWriteMail();
        mailPage.inputSendTo(ConfProperties.getProperty("send"));
        mailPage.inputTheme(ConfProperties.getProperty("mailtheme"));
        mailPage.sendMail();

        String lastButton = mailPage.getButtonName();
        Assert.assertEquals(ConfProperties.getProperty("buttonAfterMailIsSent"), lastButton);
    }

    /**
     * close the browser
     */
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

