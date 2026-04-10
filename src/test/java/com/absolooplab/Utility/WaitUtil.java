package com.absolooplab.Utility;

import java.time.Duration;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.absoloop.core.DriverManager;

public class WaitUtil {

    private static final Logger log = LogManager.getLogger(WaitUtil.class);

    // ── Timeout constants — change in ONE place, applies everywhere ──
    private static final Duration DEFAULT_TIMEOUT    = Duration.ofSeconds(10);
    private static final Duration LONG_TIMEOUT       = Duration.ofSeconds(20);
    private static final Duration SHORT_TIMEOUT      = Duration.ofSeconds(5);
    private static final Duration FLUENT_TIMEOUT     = Duration.ofSeconds(15);
    private static final Duration FLUENT_POLL        = Duration.ofMillis(500);

    // ── Helper to get driver ──────────────────────────────────────────
    private static WebDriver driver() {
        return DriverManager.getDriver();
    }

    private static WebDriverWait wait(Duration timeout) {
        return new WebDriverWait(driver(), timeout);
    }

    // ════════════════════════════════════════════════════════════════
    //  1. EXPLICIT WAITS — Standard conditions
    // ════════════════════════════════════════════════════════════════

    /**
     * Wait until element is VISIBLE on screen
     * Use before: reading text, checking display status
     */
    public static WebElement waitForVisibility(WebElement element) {
        log.debug("Waiting for visibility of element");
        return wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait until element is CLICKABLE (visible + enabled)
     * Use before: every click(), every button interaction
     */
    public static WebElement waitForClickable(WebElement element) {
        log.debug("Waiting for element to be clickable");
        return wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait until element is PRESENT in DOM (may not be visible)
     * Use for: hidden inputs, background elements
     */
    public static WebElement waitForPresence(By locator) {
        log.debug("Waiting for element presence: {}", locator);
        return wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait until element is INVISIBLE (gone from view)
     * Use for: loading spinners, overlay disappearance
     */
    public static void waitForInvisibility(WebElement element) {
        log.debug("Waiting for element to disappear");
        wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Wait for URL to contain a specific part
     * Use for: page navigation confirmation
     */
    public static void waitForUrlContains(String partialUrl) {
        log.debug("Waiting for URL to contain: {}", partialUrl);
        wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.urlContains(partialUrl));
    }

    /**
     * Wait for page title to contain text
     */
    public static void waitForTitleContains(String title) {
        log.debug("Waiting for title: {}", title);
        wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.titleContains(title));
    }

    /**
     * Wait for element TEXT to be non-empty
     * Use for: confirmation messages, dynamic text loading
     */
    public static void waitForTextPresent(WebElement element, String text) {
        log.debug("Waiting for text '{}' in element", text);
        wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Long wait — for slow-loading pages (checkout, payment, search results)
     */
    public static WebElement waitForVisibilityLong(WebElement element) {
        log.debug("Long wait for visibility");
        return wait(LONG_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Short wait — for fast interactions (dropdown appearing after click)
     */
    public static WebElement waitForVisibilityShort(WebElement element) {
        log.debug("Short wait for visibility");
        return wait(SHORT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    // ════════════════════════════════════════════════════════════════
    //  2. FLUENT WAIT — For AJAX / Dynamic elements
    //  Polls every 500ms, ignores NoSuchElement & StaleElement
    // ════════════════════════════════════════════════════════════════

    /**
     * Fluent wait for clickable — best for React/Angular dynamic elements
     * Retries every 500ms, ignores element not yet in DOM
     */
    public static WebElement fluentWaitForClickable(WebElement element) {
        log.debug("Fluent wait for clickable element");
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver())
                .withTimeout(FLUENT_TIMEOUT)
                .pollingEvery(FLUENT_POLL)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Fluent wait for visibility
     */
    public static WebElement fluentWaitForVisibility(WebElement element) {
        log.debug("Fluent wait for visible element");
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver())
                .withTimeout(FLUENT_TIMEOUT)
                .pollingEvery(FLUENT_POLL)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    // ════════════════════════════════════════════════════════════════
    //  3. PAGE LOAD WAIT — Wait for full page to finish loading
    // ════════════════════════════════════════════════════════════════

    /**
     * Wait for browser document.readyState = 'complete'
     * Use after: navigation, form submit, page reload
     */
    public static void waitForPageLoad() {
        log.debug("Waiting for page to fully load");
        wait(LONG_TIMEOUT).until((ExpectedCondition<Boolean>) driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
        log.debug("Page fully loaded");
    }

    // ════════════════════════════════════════════════════════════════
    //  4. HARD SLEEP — Use ONLY when absolutely no other option
    //  Add a comment explaining WHY you had to use it
    // ════════════════════════════════════════════════════════════════

    /**
     * Hard sleep — last resort only.
     * Always add a comment in calling code explaining why.
     */
    public static void hardSleep(long millis) {
        log.warn("Hard sleep used: {}ms — consider replacing with explicit wait", millis);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}