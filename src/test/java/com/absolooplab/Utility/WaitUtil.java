package com.absolooplab.Utility;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.absoloop.core.DriverManager;

public class WaitUtil {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void waitForVisibility(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForUrlContains(String partialUrl) {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.urlContains(partialUrl));
    }

    public static void waitForTitleContains(String title) {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.titleContains(title));
    }
}