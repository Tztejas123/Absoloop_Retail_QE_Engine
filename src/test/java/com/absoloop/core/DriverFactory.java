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

    public static WebDriver createDriver(String browser, String execution) {

        WebDriver driver;

        if (execution.equalsIgnoreCase("remote")) {
            try {
                URL gridUrl = new URL(ConfigManager.get("gridUrl"));
                driver = new RemoteWebDriver(gridUrl, new ChromeOptions());
            } catch (Exception e) {
                throw new RuntimeException("Invalid Grid URL", e);
            }
        } else {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeoptions=new EdgeOptions();
                    edgeoptions.addArguments("--start-maximized");
                    driver = new EdgeDriver(edgeoptions);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions=new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized");
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().deleteAllCookies();
        return driver;
    }
}
