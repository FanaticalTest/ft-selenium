package com.fanaticaltest.ftselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class BasePage {

    private long timeout_in_second;
    private RemoteWebDriver driver;

    public BasePage() {}

    public BasePage(RemoteWebDriver driver, long timeout_in_second) {
        this.timeout_in_second = timeout_in_second;
        this.driver = driver;
    }

    public enum browserNameOS {CHROME_PC, FIREFOX_PC, CHROME_MAC, FIREFOX_MAC, CHROME_LINUX, FIREFOX_LINUX, IEXPLORER_PC}

    public WebElement waitUntilVisible(final By by, long timeoutInSeconds) {
        return new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement findElement(By by, long timeoutInSeconds) {
        return waitUntilVisible(by, timeoutInSeconds);
    }

    public String loadPage(String urlWebsite) {
        driver.get(urlWebsite);
        return ("Page url requested : " + urlWebsite);
    }

    public String fillFieldBy(String value, By by) {
        WebElement field = findElement(by, timeout_in_second);
        field.clear();
        field.sendKeys(value);
        return ("Filled field with : " + value);
    }
}
