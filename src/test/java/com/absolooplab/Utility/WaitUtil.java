package com.absolooplab.Utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.absoloop.core.DriverManager;

public class WaitUtil {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}