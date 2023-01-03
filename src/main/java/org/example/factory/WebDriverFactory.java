package org.example.factory;

import org.example.properties.PropertyHolder;
import org.example.properties.converters.SupportedBrowserConverter;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if(webDriver == null) {
            webDriver = SupportedBrowserConverter.valueOfWebBrowser(
                    new PropertyHolder().readProperty("browser")
            ).getWebDriver();
        }
        return webDriver;
    }
}
