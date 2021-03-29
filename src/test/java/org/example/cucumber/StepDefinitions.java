package org.example.cucumber;

import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.LoginPage;
import org.example.pages.MailPage;
import org.example.utils.ConfProperties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class StepDefinitions implements En {
    public static LoginPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

    public StepDefinitions() {
        Given("^browser is opened$", () -> {
            //setting driver
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
            driver = new ChromeDriver();
            loginPage = new LoginPage(driver);
            mailPage = new MailPage(driver);
            //show window on fullscreen
            driver.manage().window().maximize();
            //10 sec delay on test running to wait till element will be shown
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        });

        Given("^user is on login page$", () -> {
            //get an URL for the test page from config file
            driver.get(ConfProperties.getProperty("loginpage"));
        });

        When("^user enters valid credentials$", () -> {
            loginPage.inputLogin(ConfProperties.getProperty("login"));
            loginPage.inputPasswd(ConfProperties.getProperty("password"));
        });

        When("^clicks on login button$", () -> {
            loginPage.clickLoginBtn();
        });

        Then("^user is navigated to the input mail page$", () -> {
            //get login that is shown
            String user = mailPage.getUserName();
            //compare login that is gotten  with other one from config file
            Assert.assertEquals(ConfProperties.getProperty("fullLogin"), user);

            //example of using logger. In this case lets log our user mail that is logged in and when
            logger.info("logged in user is:" + user);
        });
    }


    /*@Given("user is on login page")
    public void user_is_on_login_page() {
        //setting driver
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        //show window on fullscreen
        driver.manage().window().maximize();
        //10 sec delay on test running to wait till element will be shown
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //get an URL for the test page from config file
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @When("^user enters username and password$")
    public void user_enters_username_and_password() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
    }

    @And("^clicks on login button$")
    public void clicks_on_login_button() {
        loginPage.clickLoginBtn();
    }

    @Then("^user is navigated to the input mail page$")
    public void user_is_navigated_to_the_input_mail_page() {
        //get login that is shown
        String user = mailPage.getUserName();
        //compare login that is gotten  with other one from config file
        Assert.assertEquals(ConfProperties.getProperty("fullLogin"), user);

        //example of using logger. In this case lets log our user mail that is logged in and when
        logger1.info("logged in user is:" + user);
    }*/

}


