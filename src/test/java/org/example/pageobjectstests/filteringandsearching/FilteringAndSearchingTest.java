package org.example.pageobjectstests.filteringandsearching;

import org.example.dataproviders.FilteringAndSearchingDataProvider;
import org.example.pageobjectstests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FilteringAndSearchingTest extends BaseTest {

    @Test(dataProvider = "brandNames", dataProviderClass = FilteringAndSearchingDataProvider.class)
    public void verifyTitlesContainChosenBrand(String brandName) {
        boolean everyTitleContainsInputWord = mainPage
                .openMainPage()
                .openGamingKeyboardsPage()
                .selectBrand(brandName)
                .verifyEveryTitleContainsBrandName(brandName);

        Assert.assertTrue(everyTitleContainsInputWord, "Not every title contains chosen brand name");
    }

    @Test(dataProvider = "brandNamesAndPriceRanges", dataProviderClass = FilteringAndSearchingDataProvider.class)
    public void verifyProductsPricesAreInDefinedRange(String brandName, float minPrice, float maxPrice) {
        boolean arePricesInChosenRange = mainPage
                .openMainPage()
                .openGamingKeyboardsPage()
                .selectBrand(brandName)
                .setPriceRange(minPrice, maxPrice)
                .verifyPricesAreInChosenRange(minPrice, maxPrice);

        Assert.assertTrue(arePricesInChosenRange, "Prices are not within specified range");
    }

    @Test(dataProvider = "brandNames", dataProviderClass = FilteringAndSearchingDataProvider.class)
    public void verifyProductsPricesAreSortedAscendingly(String brandName) {
        boolean arePricesInAscendingOrder = mainPage
                .openMainPage()
                .openGamingKeyboardsPage()
                .selectBrand(brandName)
                .sortProductsByPriceLowToHigh()
                .verifyPricesAreInAscendingOrder();

        Assert.assertTrue(arePricesInAscendingOrder, "Prices are not in ascending order");
    }
}
