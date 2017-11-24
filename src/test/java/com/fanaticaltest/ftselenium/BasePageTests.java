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

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasePageTests {

    private DesiredCapabilities capabilities;
    private String remoteDriverUrl = "http://docker.sak:4444/wd/hub";
    private final Logger logger = LoggerFactory.getLogger(BasePageTests.class);
    //private static String googleUrl = "http://www.google.com";
    private Property p = new Property("./src/main/resources/application.properties");
    private String googleUrl = p.read("google.urlHome");


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
        BasePage bp = new BasePage();
        logger.info(bp.loadPage(driver,  googleUrl));
        driver.quit();
    }

}