package org.example.jbehave;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cucumber.StepDefinitions;
import org.example.pages.LoginPage;
import org.example.pages.MailPage;
import org.example.utils.ConfProperties;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class steps {
    public static LoginPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);
    @Given("browser is opened")
    public void givenBrowserIsOpened(){
        //setting driver
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        //show window on fullscreen
        driver.manage().window().maximize();
        //10 sec delay on test running to wait till element will be shown
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Given("user is on login page")
    public void givenLoginPage(){
        //get an URL for the test page from config file
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials(){
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
    }

    @When("clicks on login button")
    public void clicksOnLoginButton(){
        loginPage.clickLoginBtn();
    }
    @Then("user is navigated to the input mail page")
    public void userIsLoggedIn(){
        //get login that is shown
        String user = mailPage.getUserName();
        //compare login that is gotten  with other one from config file
        Assert.assertEquals(ConfProperties.getProperty("fullLogin"), user);

        //example of using logger. In this case lets log our user mail that is logged in and when
        logger.info("logged in user is:" + user);
    }
}
