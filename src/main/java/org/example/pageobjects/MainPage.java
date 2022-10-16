package org.example.pageobjects;

import org.example.pageobjects.modules.DeliverToLocationPopUpModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage {
    private final String categoryPath = "/s?k=gaming+keyboard&pd_rd_r=da8afc49-fa94-41c3-9d45-7e811ac33b10&pd_rd_w=gSHhP&pd_rd_wg"
            + "=fx882&pf_rd_p=12129333-2117-4490-9c17-6d31baf0582a&pf_rd_r=XYWA244WM0H05HEYD0RE&ref=pd_gw_unk";

    @FindBy(id = "glow-ingress-block")
    private WebElement deliverToModule;

    @FindBy(id = "glow-ingress-line2")
    private WebElement cityAndZipCode;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MainPage openMainPage() {
        webDriver.get("https://www.amazon.com/ref=nav_bb_logo");
        return this;
    }

    public GamingKeyboardsResultsPage openGamingKeyboardsPage() {
        webDriver.get("https://www.amazon.com" + categoryPath);
        return new GamingKeyboardsResultsPage(webDriver);
    }

    public DeliverToLocationPopUpModule openDeliverToModule(){
        waitForElementToBeClickable(deliverToModule);
        deliverToModule.click();
        return new DeliverToLocationPopUpModule(webDriver);
    }

    public String getZipCodeFromMainPage(){
        waitForElementVisibility(cityAndZipCode);

        return cityAndZipCode.getText();
    }
}
