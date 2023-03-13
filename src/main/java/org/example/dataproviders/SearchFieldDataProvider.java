package org.example.dataproviders;

import org.testng.annotations.DataProvider;

public class SearchFieldDataProvider {

    @DataProvider(name = "validInput")
    public static Object[][] validInput() {
        return new Object[][]{
                {"laptop"}
        };
    }

    @DataProvider(name = "invalidInput")
    public static Object[][] invalidInput() {
        return new Object[][]{
                {"asdtrhgfbjhguytu"}
        };
    }
}
