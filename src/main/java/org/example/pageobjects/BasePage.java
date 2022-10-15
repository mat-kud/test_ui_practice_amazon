package org.example.pageobjects;

import org.example.Action.CustomActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver webDriver;
    protected CustomActions customActions;


    protected BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.customActions = new CustomActions(webDriver);
        PageFactory.initElements(webDriver,this);
    }


}
