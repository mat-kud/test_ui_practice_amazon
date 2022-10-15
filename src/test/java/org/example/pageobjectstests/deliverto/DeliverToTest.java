package org.example.pageobjectstests.deliverto;

import org.example.pageobjectstests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;

public class DeliverToTest extends BaseTest {

    @DataProvider(name = "zipCodes")
    public Object[][] zipCodes() {
        return new Object[][]{
                {"85032", "Phoenix"}

        };
    }

    @DataProvider(name = "countries")
    public Object[][] countries() {
        return new Object[][]{
                {"Poland"}

        };
    }

    @Test(dataProvider = "zipCodes")
    public void verifyAppliedZipCodeOnMainPage(String zipCode, String city){
        String codeAndCityFromMainPage = mainPage
                .openMainPage()
                .openDeliverToModule()
                .setZipCode(zipCode)
                .clickContinueBtn()
                .getZipCodeFromMainPage();

        softAssert.assertTrue(codeAndCityFromMainPage.contains(city), "incorrect city");
        Assert.assertTrue(codeAndCityFromMainPage.contains(zipCode), "incorrect zip code");
    }

    @Test(dataProvider = "countries")
    public void verifyCountryIsDeliverable(String country){
        List<String> countries = mainPage
                .openMainPage()
                .openDeliverToModule()
                .getListOfDeliverableCountries();

        Assert.assertTrue(countries.contains(country),
                "selected country is not present in deliverable countries list");
    }

    @Test(dataProvider = "countries")
    public void verifySelectedCountryAppearsInProductPage(String country){
        String deliveryCountry = mainPage
                .openMainPage()
                .openDeliverToModule()
                .selectCountry(country)
                .clickDoneBtn()
                .openGamingKeyboardsPage()
                .clickRandomProduct()
                .getDeliveryCountry();

        Assert.assertEquals(country, deliveryCountry,
                "selected country is not present in product page");
    }


}
