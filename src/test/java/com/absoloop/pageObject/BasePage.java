package com.absoloop.pageObject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.absoloop.testBase.BaseClass; // Import your BaseClass

public class BasePage {
    
    public WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        // Automatically grab the driver from ThreadLocal
        this.driver = BaseClass.getDriver();
        
        // Initialize elements
        PageFactory.initElements(driver, this);
        
        // Initialize a global wait for this specific thread
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Professional Wrapper Methods
    public void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    // Click with Wait
    public void clickAfterWait(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }
}