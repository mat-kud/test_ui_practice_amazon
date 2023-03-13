package org.example.pageobjectstests.searchfield;

import org.example.dataproviders.SearchFieldDataProvider;
import org.example.pageobjectstests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFieldTest extends BaseTest {

    @Test(dataProvider = "invalidInput", dataProviderClass = SearchFieldDataProvider.class)
    public void verifyMessageAfterEnteringInvalidInputInSearchBox(String invalidInput) {
        String message = mainPage
                .openMainPage()
                .sendInvalidInput(invalidInput)
                .getInvalidInputText();

        Assert.assertEquals(message, "No results for " + invalidInput + ".",
                "Message is not present or incorrect");
    }

    @Test(dataProvider = "validInput", dataProviderClass = SearchFieldDataProvider.class)
    public void verifyInputIsPresentOnResultsPage(String input){
        String displayedInput = mainPage
                .openMainPage()
                .sendValidInput(input)
                .getInput();

        Assert.assertEquals(displayedInput, input,
                "Input is not present in result info bar");
    }

    @Test(dataProvider = "validInput", dataProviderClass = SearchFieldDataProvider.class)
    public void verifyAnyTitleContainsInput(String input) {
        boolean isAnyTitleContainingProductName = mainPage
                .openMainPage()
                .sendValidInput(input)
                .verifyAnyTitleContainsInput(input);

        Assert.assertTrue(isAnyTitleContainingProductName,
                "None title contains input");
    }
}
