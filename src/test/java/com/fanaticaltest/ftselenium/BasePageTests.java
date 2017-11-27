package com.fanaticaltest.ftselenium;

import com.fanaticaltest.ftconfig.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.openqa.selenium.By;
import java.net.MalformedURLException;
import java.net.URL;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BasePageTests {

    private Property p = new Property("./src/main/resources/application.properties");
    private final Logger logger = LoggerFactory.getLogger(BasePageTests.class);
    private DesiredCapabilities capabilities;
    private String remoteDriverUrl = p.read("selenium.hubUrl");
    private long timeoutInSecond = Long.parseLong(p.read("selenium.timeout"), 10);
    private String ftdemoUrl = p.read("ftdemo.urlHome");
    private String ftdemoHomeTitle = p.read("ftdemo.homeTitle");
    private String linkLogin= p.read("ftdemo.linkLogin");
    private String usernameValue = p.read("ftdemo.usernameValue");
    private String passwordValue = p.read("ftdemo.passwordValue");
    private String usernameField = p.read("ftdemo.usernameField");
    private String passwordField = p.read("ftdemo.passwordField");
    private String submitLogin = p.read("ftdemo.submitLogin");
    private String loginValidatorField = p.read("ftdemo.loginValidatorField");
    private String valueWrongCredential = p.read("ftdemo.valueWrongCredential");

    @Test
    public void checkLoadPage() throws MalformedURLException {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver,timeoutInSecond );
        logger.info(bp.loadPage(ftdemoUrl));
        driver.quit();
    }

    @Test
    public void checkFillFieldBy()throws MalformedURLException
    {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver);
        logger.info(bp.loadPage(ftdemoUrl));
        logger.info(bp.clickElementBy(By.id(linkLogin)));
        logger.info(bp.fillFieldBy(usernameValue,By.id(usernameField)));
        logger.info(bp.fillFieldBy(passwordValue,By.id(passwordField)));
        logger.info(bp.clickElementBy(By.id(submitLogin)));
        driver.quit();
    }

    @Test
    public void checkClickElementBy()throws MalformedURLException
    {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage();
        bp.setTimeoutInSecond(30L);
        bp.setDriver(driver);
        logger.info(bp.loadPage(ftdemoUrl));
        logger.info(bp.assertPageTitle(ftdemoHomeTitle,ftdemoUrl));
        driver.quit();
    }

    @Test
    public void checkTextInField()throws MalformedURLException
    {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver);
        logger.info(bp.loadPage(ftdemoUrl));
        logger.info(bp.clickElementBy(By.id(linkLogin)));
        logger.info(bp.clickElementBy(By.id(submitLogin)));
        logger.info(bp.assertTextInElementBy(valueWrongCredential,By.id(loginValidatorField)));
        driver.quit();
    }

    @Test
    public void checkWaitTextInField()throws MalformedURLException
    {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver);
        logger.info(bp.loadPage(ftdemoUrl));
        logger.info(bp.clickElementBy(By.id(linkLogin)));
        logger.info(bp.clickElementBy(By.id(submitLogin)));
        logger.info(bp.waitAndAssertTextInElementBy(valueWrongCredential,By.id(loginValidatorField)));
        driver.quit();
    }

    @Test
    public  void checkAssertAttributeInElementBy()throws MalformedURLException
    {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver);
        logger.info(bp.loadPage(ftdemoUrl));
        logger.info(bp.clickElementBy(By.id(linkLogin)));
        logger.info(bp.assertAttributeInElementBy("value","Login", By.id(submitLogin)));
        driver.quit();
    }
}