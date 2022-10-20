package org.example.pageobjectstests.searchfield;

import org.example.pageobjectstests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchFieldTest extends BaseTest {

    @DataProvider(name = "invalidInput")
    public Object[][] invalidInputs() {
        return new Object[][]{
                {"asdtrhgfbjhguytu"}
        };
    }

    @Test(dataProvider = "invalidInput")
    public void verifyMessageAfterEnteringInvalidInputInSearchBox(String invalidInput) {
        String message = mainPage
                .openMainPage()
                .sendInvalidInput(invalidInput)
                .getInvalidInputText();

        Assert.assertEquals(message, "No results for " + invalidInput + ".",
                "Message is not present or incorrect");
    }

    @DataProvider(name = "validInput")
    public Object[][] validInputs() {
        return new Object[][]{
                {"laptop"}
        };
    }

    @Test(dataProvider = "validInput")
    public void verifyInputIsPresentOnResultsPage(String input){
        String displayedInput = mainPage
                .openMainPage()
                .sendValidInput(input)
                .getInput();

        Assert.assertEquals(displayedInput, input,
                "Input is not present in result info bar");
    }

    @Test(dataProvider = "validInput")
    public void verifyAnyTitleContainsInput(String input) {
        boolean isAnyTitleContainingProductName = mainPage
                .openMainPage()
                .sendValidInput(input)
                .verifyAnyTitleContainsInput(input);

        Assert.assertTrue(isAnyTitleContainingProductName,
                "None title contains input");
    }
}
