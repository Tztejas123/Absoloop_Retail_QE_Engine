package com.absoloop.testBase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.absoloop.core.DriverFactory;
import com.absoloop.core.DriverManager;
import com.absoloop.core.ConfigManager;

public class BaseClass {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "execution"})
    public void setup(String browser, String execution) {

        WebDriver driver = DriverFactory.createDriver(browser, execution);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(ConfigManager.get("appURL2"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}