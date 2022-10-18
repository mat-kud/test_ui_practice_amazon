package org.example.pageobjects;

import org.example.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteBtn;

    @FindBy(xpath = "//div[@class=\"a-row sc-cart-header\"]//h1")
    private WebElement cartIsEmptyConfirmation;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']/span")
    private WebElement totalPrice;

    @FindBy(xpath = "//span[contains(@class,'sw-atc-text')]")
    private WebElement addToCartConfirmation;

    @FindBy(id = "nav-cart-count")
    private WebElement numberOfItemsInTheCart;

    protected ShoppingCartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getAddedtoCartConfirmation(){
        return addToCartConfirmation.getText();
    }

    public String getNumberOfItemsInTheCart(){
        return numberOfItemsInTheCart.getText();
    }

    public ShoppingCartPage deleteProduct(){
        deleteBtn.click();

        return this;
    }

    public ShoppingCartPage goToShoppingCart(){
        numberOfItemsInTheCart.click();

        return this;
    }

    public String getCartIsEmptyConfirmation(){
        waitForElementVisibility(cartIsEmptyConfirmation);

        return cartIsEmptyConfirmation.getText();
    }

    public String getTotalPrice(){
        return totalPrice.getText();
    }

}
