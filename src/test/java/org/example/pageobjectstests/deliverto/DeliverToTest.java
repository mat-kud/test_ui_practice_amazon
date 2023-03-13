package org.example.pageobjectstests.deliverto;

import org.example.dataproviders.DeliverToDataProvider;
import org.example.pageobjectstests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;

public class DeliverToTest extends BaseTest {

    @Test(dataProvider = "zipCodes", dataProviderClass = DeliverToDataProvider.class)
    public void verifyAppliedZipCodeOnMainPage(String zipCode, String city){
        String codeAndCityFromMainPage = mainPage
                .openMainPage()
                .openDeliverToModule()
                .setZipCode(zipCode)
                .clickContinueBtn()
                .getZipCodeFromMainPage();

        softAssert.assertTrue(codeAndCityFromMainPage.contains(city), "incorrect city");
        softAssert.assertTrue(codeAndCityFromMainPage.contains(zipCode), "incorrect zip code");
        softAssert.assertAll();
    }

    @Test(dataProvider = "countries", dataProviderClass = DeliverToDataProvider.class)
    public void verifyCountryIsDeliverable(String country){
        List<String> countries = mainPage
                .openMainPage()
                .openDeliverToModule()
                .getListOfDeliverableCountries();

        Assert.assertTrue(countries.contains(country),
                "selected country is not present in deliverable countries list");
    }

    @Test(dataProvider = "countries", dataProviderClass = DeliverToDataProvider.class)
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
