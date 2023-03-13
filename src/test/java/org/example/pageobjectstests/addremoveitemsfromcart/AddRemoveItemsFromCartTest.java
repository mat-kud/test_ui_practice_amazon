package org.example.pageobjectstests.addremoveitemsfromcart;

import org.example.pageobjects.ShoppingCartPage;
import org.example.pageobjectstests.BaseTest;
import org.testng.annotations.Test;

public class AddRemoveItemsFromCartTest extends BaseTest {

    @Test
    public void  verifyItemWasSuccessfullyAddedToCart() {
        ShoppingCartPage shoppingCart = mainPage
                .openMainPage()
                .openHeadsetsPage()
                .clickRandomProduct()
                .addProductToCart();

        softAssert.assertEquals(shoppingCart.getAddedtoCartConfirmation(),
                "Added to Cart", "Added to cart message is not correct");
        softAssert.assertEquals(shoppingCart.getNumberOfItemsInTheCart(),
                "1", "Number of items is not correct");
        softAssert.assertAll();
    }

    @Test
    public void verifyItemWasSuccessfullyRemovedFromCart() {
        ShoppingCartPage shoppingCart = mainPage
                .openMainPage()
                .openHeadsetsPage()
                .clickRandomProduct()
                .addProductToCart()
                .goToShoppingCart()
                .deleteProduct();

        softAssert.assertEquals(shoppingCart.getCartIsEmptyConfirmation(), "Your Amazon Cart is empty.",
                "Cart is empty confirmation message is not correct");
        softAssert.assertEquals(shoppingCart.getTotalPrice(), "$0.00", "Total price should be $0.00");
        softAssert.assertAll();
    }
}
