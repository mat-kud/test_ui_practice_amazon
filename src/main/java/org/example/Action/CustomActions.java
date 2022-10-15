package org.example.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomActions extends Actions {
    WebDriverWait webDriverWait;

    public CustomActions(WebDriver webDriver) {
        super(webDriver);
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(6));
    }

    public void waitForElementVisibility(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementPresence(String elementXpath){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }

    public void sleepThread(long miliseconds){
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
