package org.example.dataproviders;

import org.testng.annotations.DataProvider;

public class FilteringAndSearchingDataProvider {
    @DataProvider(name = "brandNames")
    public static Object[][] brandNames() {
        return new Object[][]{
                {"Corsair"}
        };
    }

    @DataProvider(name = "brandNamesAndPriceRanges")
    public static Object[][] brandNamesAndPriceRanges() {
        return new Object[][]{
                {"Corsair", 50, 250}
        };
    }
}
