package org.example.pageobjects.modules;

import org.example.pageobjects.BasePage;
import org.example.pageobjects.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DeliverToLocationPopUpModule extends BasePage {

    @FindBy(id = "GLUXZipUpdateInput")
    private WebElement zipCodeInput;

    @FindBy(id = "GLUXZipUpdate")
    private WebElement zipCodeApplyBtn;

    @FindBy(xpath = "(//input[@id='GLUXConfirmClose'])[2]")
    private WebElement continueBtn;

    @FindBy(name = "glowDoneButton")
    private WebElement doneBtn;

    @FindBy(id = "glow-ingress-line2")
    private WebElement cityAndZipCode;

    @FindBy(id ="GLUXCountryListDropdown")
    private WebElement countriesDropDownMenu;

    @FindBy(id= "GLUXCountryList")
    private WebElement listOfCountries;

    public DeliverToLocationPopUpModule(WebDriver webDriver) {
        super(webDriver);
    }

    public DeliverToLocationPopUpModule setZipCode(String zipCode){
        customActions.waitForElementVisibility(zipCodeInput);
        zipCodeInput.sendKeys(zipCode);
        customActions.waitForElementVisibility(zipCodeApplyBtn);
        zipCodeApplyBtn.click();

        return this;
    }


    public List<String> getListOfDeliverableCountries(){
        customActions.waitForElementVisibility(listOfCountries);
        Select countries = new Select(listOfCountries);

        return countries.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public DeliverToLocationPopUpModule selectCountry(String country){
        customActions.waitForElementVisibility(listOfCountries);
        Select countries = new Select(listOfCountries);
        countries.selectByVisibleText(country);

        return this;
    }

    public MainPage clickContinueBtn(){
        customActions.waitForElementVisibility(continueBtn);
        continueBtn.click();
        //sleep thread to avoid StaleElementReferenceException
        customActions.sleepThread(500);

        return new MainPage(webDriver);
    }


    public MainPage clickDoneBtn(){
        customActions.waitForElementVisibility(doneBtn);
        doneBtn.click();
        //sleep thread to avoid StaleElementReferenceException
        customActions.sleepThread(500);

        return new MainPage(webDriver);
    }
}
