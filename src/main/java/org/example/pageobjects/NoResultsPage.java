package org.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NoResultsPage extends BasePage {

    @FindBy(xpath = "//div[@class='a-row']")
    private WebElement invalidInputMsg;


    public NoResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getInvalidInputText() {
        return invalidInputMsg.getText();
    }


}
