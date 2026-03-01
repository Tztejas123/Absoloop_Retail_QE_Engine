package com.absoloop.core;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver createDriver(String browser, String execution) {

        WebDriver driver;

        if (execution.equalsIgnoreCase("remote")) {
            try {
                URL gridUrl = new URL(ConfigManager.get("gridURL"));

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                driver = new RemoteWebDriver(gridUrl, options);

            } catch (Exception e) {
                throw new RuntimeException("Invalid Grid URL", e);
            }
        } else {

            switch (browser.toLowerCase()) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }

        // Global Timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));

        driver.manage().deleteAllCookies();

        return driver;
    }
}