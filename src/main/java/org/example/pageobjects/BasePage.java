package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    protected BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(6));
        PageFactory.initElements(webDriver,this);
    }

    protected void waitForElementVisibility(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementPresence(String elementXpath){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void sleepThread(long miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
