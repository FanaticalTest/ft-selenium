package com.fanaticaltest.ftselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@Component
public class BasePage {

    private long timeoutInSecond;
    private RemoteWebDriver driver;

    public void setTimeoutInSecond(long timeoutInSecond) {
        this.timeoutInSecond = timeoutInSecond;
    }

    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
        this.timeoutInSecond = 20L;
    }

    public BasePage(RemoteWebDriver driver, long timeoutInSecond) {
        this.timeoutInSecond = timeoutInSecond;
        this.driver = driver;
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.timeoutInSecond = 20L;
    }

    public WebElement waitUntilVisible(final By by) {
        return new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement findElement(By by) {
        return waitUntilVisible(by);
    }

    public void waitForUrlRedirect(String url) {
        (new WebDriverWait(driver, timeoutInSecond)).until(ExpectedConditions.urlContains(url));
    }

    public boolean waitForText(By by, String value)
    {
        return new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.textToBePresentInElementLocated(by, value));
    }

    public String loadPage(String urlWebsite) {
        driver.get(urlWebsite);
        return ("Page url requested : " + urlWebsite);
    }

    public String fillFieldBy(String value, By by) {
        WebElement field = findElement(by);
        field.clear();
        field.sendKeys(value);
        return ("Filled field with : " + value);
    }

    public String clickElementBy(By by) {
        WebElement elem = findElement(by);
        elem.click();
        return("Clicked on element : "+ by.toString());
    }

    public String assertPageTitle(String title, String url) {
        waitForUrlRedirect(url);
        assertThat(driver.getTitle(), containsString(title));
        return ("Assert page url :" + url + " contains " + title);
    }

    public String assertTextInElementBy(String value, By by) {
        WebElement elem = findElement(by);
        assertThat(elem.getText(), containsString(value));
        return ("Assert text in element " + by + " the value : " + value);
    }

    public String waitAndAssertTextInElementBy(String value, By by)
    {
        WebElement elem = findElement(by);
        if (waitForText(by,value))
            assertThat(elem.getText(), containsString(value));
        return ("Wait element " + by + " and assert in the field the value : " + value);
    }

    public String assertAttributeInElementBy(String attributeName, String value, By by) {
        WebElement elem = findElement(by);
        assertThat(elem.getAttribute(attributeName), containsString(value));
        return ("Assert Attribute name " + attributeName + " in element " + by + " the value " + value);
    }

    public WebElement waitUntilActive(By by) {
        return new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public String getScreenshot(String screenshotPath) throws Exception {
        SimpleDateFormat sdfScreenshot = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String pngFileName = sdfScreenshot.format(timestamp) + ".png";

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotPath + pngFileName));
        return ("Screenshot taken " + screenshotPath + pngFileName);
    }
}
