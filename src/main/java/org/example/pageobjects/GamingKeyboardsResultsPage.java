package org.example.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GamingKeyboardsResultsPage extends BasePage {

    private final String sponsoredSpanXpath = ".//span[@class='s-label-popover-default']/span";

    private final String biggerPriceTextXpath = ".//div//span[@class='a-price']//span[@class='a-offscreen']";

    private final String titleXpath = "//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]";

    private final String itemContainersXpath = "//div[@data-component-type='s-search-result']//div[contains(@class,'s-card-container')]";

    @FindBy(xpath = itemContainersXpath)
    List<WebElement> itemContainers;

    @FindBy(xpath = titleXpath)
    List<WebElement> titlesList;

    @FindBy(xpath = "(//a[contains(@class,\"a-expander-header\")])[2]")
    private WebElement brandsExpander;

    @FindBy(id = "low-price")
    private WebElement minPriceInput;

    @FindBy(id = "high-price")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//span[contains(@class,'a-button')]/span/input")
    private WebElement submitPriceRangeBtn;

    @FindBy(id = "a-autoid-0-announce")
    private WebElement sortingDropdownList;

    @FindBy(id = "s-result-sort-select_1")
    private WebElement lowToHighDropdownSelection;

    @FindBy(xpath = "//*[contains(@class,'s-pagination-next')]")
    private WebElement paginationNextBtn;

    public GamingKeyboardsResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public GamingKeyboardsResultsPage selectBrand(String brandName) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", brandsExpander);

        WebElement brandNameCheckbox = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '" + brandName + "']")));
        brandNameCheckbox.click();
        return this;
    }

    public GamingKeyboardsResultsPage setPriceRange(float minPrice, float maxPrice) {
        waitForElementVisibility(minPriceInput);
        minPriceInput.sendKeys(String.valueOf(minPrice));
        maxPriceInput.sendKeys(String.valueOf(maxPrice));
        waitForElementVisibility(submitPriceRangeBtn);
        submitPriceRangeBtn.click();
        return this;
    }

    public GamingKeyboardsResultsPage sortProductsByPriceLowToHigh() {
        waitForElementVisibility(sortingDropdownList);
        sortingDropdownList.click();
        lowToHighDropdownSelection.click();
        return this;
    }

    public ProductPage clickRandomProduct(){
        Random random = new Random();
        waitForElementPresence(titleXpath);
        titlesList.get(random.nextInt(titlesList.size())).click();

        return new ProductPage(webDriver);
    }

    public boolean verifyEveryTitleContainsBrandName(String brandName) {
        boolean everyTitleContainsInputWord;

        while (true) {
            waitForElementPresence(titleXpath);

            everyTitleContainsInputWord = titlesList
                    .stream()
                    .map(WebElement::getText)
                    .map(String::toLowerCase)
                    .allMatch(e -> e.contains(brandName.toLowerCase()));

            if(stopGoingThroughPages(everyTitleContainsInputWord)){
                break;
            }

            paginationNextBtn.click();
        }
        return everyTitleContainsInputWord;
    }

    public boolean verifyPricesAreInChosenRange(float minPrice, float maxPrice) {
        boolean arePricesInChosenRange;

        while (true) {
            waitForElementPresence(itemContainersXpath);

            arePricesInChosenRange = itemContainers
                    .stream()
                    .filter(e -> e.findElements(By.xpath(sponsoredSpanXpath)).isEmpty()
                            && !e.findElements(By.xpath(biggerPriceTextXpath)).isEmpty())
                    .map(e -> Float.parseFloat(e.findElement(By.xpath(biggerPriceTextXpath))
                            .getAttribute("textContent").replace("$", "")))
                    .allMatch(price -> price >= minPrice && price <= maxPrice);

            if(stopGoingThroughPages(arePricesInChosenRange)){
                break;
            }

            paginationNextBtn.click();
        }

        return arePricesInChosenRange;
    }

    public boolean verifyPricesAreInAscendingOrder() {
        boolean arePricesInAscendingOrder;
        List<Float> prices;

        while (true) {
            waitForElementPresence(itemContainersXpath);

            prices = itemContainers
                    .stream()
                    .filter(e -> e.findElements(By.xpath(sponsoredSpanXpath)).isEmpty()
                            && !e.findElements(By.xpath(biggerPriceTextXpath)).isEmpty())
                    .map(e -> Float.parseFloat(e.findElement(By.xpath(biggerPriceTextXpath))
                            .getAttribute("textContent").replace("$", "")))
                    .collect(Collectors.toList());

            arePricesInAscendingOrder = verifyPricesInAscendingOrder(prices);

            if(stopGoingThroughPages(arePricesInAscendingOrder)){
                break;
            }

            paginationNextBtn.click();
        }

        return arePricesInAscendingOrder;
    }

    private boolean stopGoingThroughPages(boolean condition){
        if (!condition) {
            return true;
        }

        try{
            waitForElementVisibility(paginationNextBtn);
        } catch(NoSuchElementException e){
            System.err.println("Pagination next button not found");
            return true;
        }

        return !(paginationNextBtn.getAttribute("aria-disabled") == null);
    }

    private boolean verifyPricesInAscendingOrder(List<Float> prices) {
        if(prices.size() == 1){
            return true;
        }

        for (int i = 0; i < prices.size() -1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }
}