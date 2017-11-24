package com.fanaticaltest.ftselenium;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

//@Component
public class BasePage {

    public BasePage() {}

    public enum browserNameOS {CHROME_PC, FIREFOX_PC, CHROME_MAC, FIREFOX_MAC, CHROME_LINUX, FIREFOX_LINUX, IEXPLORER_PC}

    public String loadPage(RemoteWebDriver driver, String urlWebsite) {
        driver.get(urlWebsite);
        return ("Page url requested : " + urlWebsite);
    }
}
