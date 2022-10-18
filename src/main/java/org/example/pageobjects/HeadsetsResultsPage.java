package org.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class HeadsetsResultsPage extends BasePage {
    private final String titleXpath = "//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]";

    @FindBy(xpath = titleXpath)
    List<WebElement> titlesList;

    public HeadsetsResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductPage clickRandomProduct(){
        Random random = new Random();
        waitForElementPresence(titleXpath);
        titlesList.get(random.nextInt(titlesList.size())).click();

        return new ProductPage(webDriver);
    }


}
