package org.example.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LaptopsResultsPage extends BasePage {

    private final String titleXpath = "//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]";

    @FindBy(xpath = titleXpath)
    List<WebElement> titlesList;

    @FindBy(xpath = "//*[contains(@class,'s-pagination-next')]")
    private WebElement paginationNextBtn;


    @FindBy(xpath = "//span[@data-component-type='s-result-info-bar']//span[3]")
    private WebElement inputFromResultInfoBar;

    public LaptopsResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getInput() {
        return inputFromResultInfoBar.getText().replace("\"", "");
    }

    public boolean verifyAnyTitleContainsInput(String input) {
        boolean anyTitleContainsInput;

        while (true) {
            waitForElementPresence(titleXpath);

            anyTitleContainsInput = titlesList
                    .stream()
                    .map(WebElement::getText)
                    .map(String::toLowerCase)
                    .anyMatch(e -> e.contains(input.toLowerCase()));

            if (stopGoingThroughPages(anyTitleContainsInput)) {
                break;
            }

            paginationNextBtn.click();
        }
        return anyTitleContainsInput;
    }

    private boolean stopGoingThroughPages(boolean condition) {
        if (condition) {
            return true;
        }

        try {
            waitForElementVisibility(paginationNextBtn);
        } catch (NoSuchElementException e) {
            System.err.println("Pagination next button not found");
            return true;
        }

        return !(paginationNextBtn.getAttribute("aria-disabled") == null);
    }
}
