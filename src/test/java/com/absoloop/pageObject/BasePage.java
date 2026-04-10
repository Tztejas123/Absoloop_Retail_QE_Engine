package com.absoloop.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.absoloop.core.DriverManager;
import com.absolooplab.Utility.WaitUtil;

public abstract class BasePage {

	protected WebDriver driver;
	protected static final Logger log = LogManager.getLogger(BasePage.class);

	public BasePage() {
		this.driver = DriverManager.getDriver();
		// ✅ Wait for page to load BEFORE initializing elements
		WaitUtil.waitForPageLoad();
		PageFactory.initElements(driver, this);
		log.info("Initialized Page: {}", this.getClass().getSimpleName());
	}

	// ── click ─────────────────────────────────────────────────────
	protected void click(WebElement element, String name) {
		// ✅ Always wait for clickable before clicking
		WaitUtil.waitForClickable(element);
		log.info("▶ CLICK  | Page: {} | Element: {}", this.getClass().getSimpleName(), name);
		element.click();
	}

	// ── type ──────────────────────────────────────────────────────
	protected void type(WebElement element, String value, String name) {
		// ✅ Wait for visibility before typing
		WaitUtil.waitForVisibility(element);
		log.info("✎ TYPE   | Page: {} | Field: {} | Value: {}", this.getClass().getSimpleName(), name, value);
		element.clear();
		element.sendKeys(value);
	}

	// ── getText ───────────────────────────────────────────────────
	protected String getText(WebElement element, String name) {
		// ✅ Wait for visibility to ensure text is rendered
		WaitUtil.waitForVisibility(element);
		String text = element.getText();
		log.info("📄 TEXT   | Page: {} | Element: {} | Value: {}", this.getClass().getSimpleName(), name, text);
		return text;
	}

	// ── isDisplayed ───────────────────────────────────────────────
	protected boolean isDisplayed(WebElement element, String name) {
		try {
			// ✅ Short wait — we want quick pass/fail, not long timeout
			WaitUtil.waitForVisibilityShort(element);
			log.info("👁 CHECK  | Page: {} | Element: {} | Visible: true", this.getClass().getSimpleName(), name);
			return true;
		} catch (Exception e) {
			log.warn("⚠ NOT FOUND | Page: {} | Element: {}", this.getClass().getSimpleName(), name);
			return false;
		}
	}

	// ── jsClick — when normal click fails (overlays, hidden buttons) ──
	protected void jsClick(WebElement element, String name) {
		// ✅ Wait for presence in DOM even if not visible
		log.info("⚡ JS CLICK | Page: {} | Element: {}", this.getClass().getSimpleName(), name);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	// ── selectDropdown ────────────────────────────────────────────
	protected void selectDropdown(WebElement element, String visibleText, String name) {
		WaitUtil.waitForVisibility(element);
		log.info("📋 SELECT | Page: {} | Dropdown: {} | Option: {}", this.getClass().getSimpleName(), name,
				visibleText);
		new org.openqa.selenium.support.ui.Select(element).selectByVisibleText(visibleText);
	}

	// ── clearAndType ──────────────────────────────────────────────
	protected void clearAndType(WebElement element, String value, String name) {
		WaitUtil.waitForVisibility(element);
		log.info("🗑 CLEAR+TYPE | Page: {} | Field: {}", this.getClass().getSimpleName(), name);
		element.clear();
		// ✅ Wait after clear — some fields re-populate (search suggestions etc.)
		WaitUtil.waitForVisibility(element);
		element.sendKeys(value);
	}
}