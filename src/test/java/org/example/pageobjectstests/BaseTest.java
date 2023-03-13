package org.example.pageobjectstests;

import org.example.factory.WebDriverFactory;
import org.example.pageobjects.MainPage;
import org.example.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({TestListener.class})
public class BaseTest {
    protected final WebDriver webDriver = new WebDriverFactory().getWebDriver();
    protected final MainPage mainPage = new MainPage(webDriver);
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeTest()
    protected void setUpDriver() {
        webDriver.manage().window().maximize();
    }


    @AfterTest
    public void tearDown(){
        webDriver.quit();
    }
}
