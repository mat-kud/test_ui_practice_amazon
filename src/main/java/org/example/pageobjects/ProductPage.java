package org.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]")
    private WebElement deliveryCountry;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartBtn;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getDeliveryCountry(){
        return deliveryCountry.getText();
    }

    public ShoppingCartPage addProductToCart(){
        addToCartBtn.click();

        return new ShoppingCartPage(webDriver);
    }
}
