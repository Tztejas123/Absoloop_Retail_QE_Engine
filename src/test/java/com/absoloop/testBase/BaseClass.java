package com.absoloop.testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.absoloop.core.*;
import com.absolooplab.Utility.WaitUtil;

public class BaseClass {

	private static final Logger log = LogManager.getLogger(BaseClass.class);

	// ✅ Store start time per thread for duration calculation
	private static final ThreadLocal<Long> startTime = new ThreadLocal<>();

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "os", "browser", "url", "execution" })
	public void setup(@Optional("Windows") String os, @Optional("chrome") String br,
			@Optional("https://tutorialsninja.com/demo/") String url, @Optional("local") String execution) {

		startTime.set(System.currentTimeMillis()); // ✅ start timer

		log.info("╔══════════════════════════════════════════════════╗");
		log.info("  TEST STARTING | OS: {} | Browser: {} | Thread: {}", os, br, Thread.currentThread().getName());
		log.info("╚══════════════════════════════════════════════════╝");

		WebDriver driver = DriverFactory.createDriver(br, execution);
		DriverManager.setDriver(driver);

		log.info("Driver created: {} | Session: {}", driver.getClass().getSimpleName(),
				((org.openqa.selenium.remote.RemoteWebDriver) driver).getSessionId());

		DriverManager.getDriver().get(ConfigManager.get("appURL2"));
		WaitUtil.waitForPageLoad();
		log.info("Navigated to URL: {}", ConfigManager.get("appURL2"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		long duration = System.currentTimeMillis() - startTime.get();

		// ✅ PASS in green, FAIL in red — clearly visible
		if (result.getStatus() == ITestResult.SUCCESS) {
			log.info("✅ TEST PASSED  | {} | Duration: {}ms | Browser: {}", result.getMethod().getMethodName(), duration,
					Thread.currentThread().getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			log.error("❌ TEST FAILED  | {} | Duration: {}ms | Reason: {}", result.getMethod().getMethodName(), duration,
					result.getThrowable().getMessage());
		} else {
			log.warn("⚠️ TEST SKIPPED | {} | Duration: {}ms", result.getMethod().getMethodName(), duration);
		}

		log.info("Driver quitting | Thread: {}", Thread.currentThread().getName());
		DriverManager.quitDriver();
		startTime.remove();
	}
}