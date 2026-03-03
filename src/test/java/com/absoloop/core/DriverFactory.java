package com.absoloop.core;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver createDriver(String browser, String execution, boolean headless) {

        WebDriver driver;

        try {

            if (execution.equalsIgnoreCase("remote")) {

                URL gridUrl = new URL(ConfigManager.get("gridURL"));

                switch (browser.toLowerCase()) {

                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if (headless) chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--start-maximized");
                        driver = new RemoteWebDriver(gridUrl, chromeOptions);
                        break;

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        if (headless) edgeOptions.addArguments("--headless=new");
                        driver = new RemoteWebDriver(gridUrl, edgeOptions);
                        break;

                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        if (headless) firefoxOptions.addArguments("-headless");
                        driver = new RemoteWebDriver(gridUrl, firefoxOptions);
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid browser: " + browser);
                }

            } else {

                switch (browser.toLowerCase()) {

                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if (headless) chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--start-maximized");
                        driver = new ChromeDriver(chromeOptions);
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        if (headless) edgeOptions.addArguments("--headless=new");
                        driver = new EdgeDriver(edgeOptions);
                        driver.manage().window().maximize();
                        break;

                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        if (headless) firefoxOptions.addArguments("-headless");
                        driver = new FirefoxDriver(firefoxOptions);
                        driver.manage().window().maximize();
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid browser: " + browser);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Driver creation failed", e);
        }

        // Global timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));

        driver.manage().deleteAllCookies();

        return driver;
    }
}