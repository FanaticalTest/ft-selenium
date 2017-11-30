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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

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

    private String getScreenshot (String screenshotPath, String fileName, boolean noPrefix, boolean noTimestamp) throws Exception {
        SimpleDateFormat sdfScreenshot = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String pngFileName;

        if (noPrefix == true) {
            pngFileName = sdfScreenshot.format(timestamp) + ".png";
        }
        else if (noTimestamp == true)
        {
            pngFileName = fileName + ".png";
        }
        else {
            pngFileName = fileName + "-" + sdfScreenshot.format(timestamp) + ".png";
        }

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotPath + pngFileName));
        return ("Screenshot taken " + screenshotPath + pngFileName);
    }

    public String getScreenshot(String screenshotPath, String fileNamePrefix) throws Exception {
        return (getScreenshot(screenshotPath, fileNamePrefix, false, false));
    }

    public String getScreenshot(String screenshotPath) throws Exception {
        return (getScreenshot(screenshotPath, "empty", true, false));
    }

    public String getScreenshot(String screenshotPath, String fileNamePrefix, boolean noTimestamp) throws Exception {
        return (getScreenshot(screenshotPath, fileNamePrefix, false, noTimestamp));
    }


    public String selectDropDownByValue(By by, String value){
        Select select = new Select(findElement(by));
        select.selectByValue(value);
        return ("Value selected is " + value);
    }

    public String selectDropDownByVisibleText(By by, String visibleText){
        Select select = new Select(findElement(by));
        select.selectByVisibleText(visibleText);
        return ("Visible text selected is " + visibleText);
    }

    public String selectDropDownByIndex(By by, int index){
        Select select = new Select(findElement(by));
        select.selectByIndex(index);
        return ("Index selected is " + index);
    }

    public String getDropDownSelectedAttribute(By by, String attribute){
        Select select = new Select(findElement(by));
        WebElement option = select.getFirstSelectedOption();
        return option.getAttribute(attribute);
    }

    public String getDropDownSelectedValue(By by){
        return getDropDownSelectedAttribute(by,"value");
    }

    public String getInnerHtmlValue(By by){
        WebElement elem = findElement(by);
        return elem.getAttribute("innerHTML");
    }

    public void mouseOverOneHop(By firstElem, By secondElem)
    {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(firstElem)).moveToElement(driver.findElement(secondElem)).click().build().perform();
    }

    public String freezeProcess(long timeInSecond)
    {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ("Freezing process for "+timeInSecond+" seconds");
    }
}
