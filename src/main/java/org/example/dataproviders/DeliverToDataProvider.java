package org.example.dataproviders;

import org.testng.annotations.DataProvider;

public class DeliverToDataProvider {
    @DataProvider(name = "zipCodes")
    public static Object[][] zipCodes() {
        return new Object[][]{
                {"85032", "Phoenix"}
        };
    }

    @DataProvider(name = "countries")
    public static Object[][] countries() {
        return new Object[][]{
                {"Poland"}
        };
    }
}
