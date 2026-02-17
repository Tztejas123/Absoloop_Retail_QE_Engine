package com.absoloop.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    // ThreadLocal ensures each thread (browser) has its own isolated WebDriver instance
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public ResourceBundle rb;
    public Logger logger;

    @BeforeClass(groups = { "Master", "Sanity", "Regression" })
    @Parameters({"os", "browser"}) // Catching both parameters from your XML
    public void setup(String os, String br) {
        
        rb = ResourceBundle.getBundle("config"); // Loads config.properties
        logger = LogManager.getLogger(this.getClass());

        WebDriver driver = null;

        // EXPERT STRATEGY: Cross-Browser Setup
        switch (br.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                logger.error("Invalid browser name provided in XML");
                return;
        }

        threadLocalDriver.set(driver);

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        // Use a generic key "appURL" for better maintainability
        getDriver().get(rb.getString("appURL2")); 
    }

    @AfterClass(groups = { "Master", "Sanity", "Regression" })
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove(); // Prevents memory leaks in Jenkins/CI
        }
    }

    // Global access for Page Objects and Listeners
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    // Helper Methods for Data Generation
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        // RUTHLESS FIX: Cross-platform file separator (works on Windows & Linux)
        String destination = System.getProperty("user.dir") + File.separator + "screenshots" 
                             + File.separator + tname + "_" + timeStamp + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            logger.error("Screenshot failed: " + e.getMessage());
        }
        return destination;
    }
}