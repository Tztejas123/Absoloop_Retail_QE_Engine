package com.absoloop.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.absoloop.core.DriverManager;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}