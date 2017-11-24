package com.fanaticaltest.ftselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class BasePage {

    private long timeoutInSecond;
    private RemoteWebDriver driver;

    public void setTimeoutInSecond(long timeoutInSecond) {
        this.timeoutInSecond = timeoutInSecond;
    }

    public BasePage() {}

    public BasePage(RemoteWebDriver driver, long timeoutInSecond) {
        this.timeoutInSecond = timeoutInSecond;
        this.driver = driver;
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.timeoutInSecond = 20L;
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
        WebElement field = findElement(by, timeoutInSecond);
        field.clear();
        field.sendKeys(value);
        return ("Filled field with : " + value);
    }

    public String clickElementBy(By by) {
        WebElement elem = findElement(by, timeoutInSecond);
        elem.click();
        return("Clicked on element : "+ by.toString());
    }
}
