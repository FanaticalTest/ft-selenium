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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasePageTests {

    private Property p = new Property("./src/main/resources/application.properties");
    private final Logger logger = LoggerFactory.getLogger(BasePageTests.class);
    private DesiredCapabilities capabilities;
    private String remoteDriverUrl = p.read("selenium.hubUrl");
    private String googleUrl = p.read("google.urlHome");
    private String googleSearchBoxById = p.read("google.searchBoxById");
    private String googleSearchTerm = p.read("google.searchTerm");
    private long timeoutInSecond = Long.parseLong(p.read("selenium.timeout"), 10);


    @Test
    public void checkEnum()
    {
        assertThat(BasePage.browserNameOS.CHROME_PC.toString(),containsString("CHROME_PC"));
        assertThat(BasePage.browserNameOS.FIREFOX_PC.toString(),containsString("FIREFOX_PC"));
        assertThat(BasePage.browserNameOS.CHROME_MAC.toString(),containsString("CHROME_MAC"));
        assertThat(BasePage.browserNameOS.FIREFOX_MAC.toString(),containsString("FIREFOX_MAC"));
        assertThat(BasePage.browserNameOS.FIREFOX_LINUX.toString(),containsString("FIREFOX_LINUX"));
        assertThat(BasePage.browserNameOS.IEXPLORER_PC.toString(),containsString("IEXPLORER_PC"));
        assertThat(BasePage.browserNameOS.CHROME_LINUX.toString(),containsString("CHROME_LINUX"));
    }

    @Test
    public void checkLoadPage() throws MalformedURLException {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver,timeoutInSecond );
        logger.info(bp.loadPage(googleUrl));
        driver.quit();
    }

    @Test
    public void checkFillFieldBy()throws MalformedURLException
    {
        capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        BasePage bp = new BasePage(driver,timeoutInSecond );
        logger.info(bp.loadPage(googleUrl));
        logger.info(bp.fillFieldBy(googleSearchTerm,By.id(googleSearchBoxById)));
        driver.quit();
    }


}