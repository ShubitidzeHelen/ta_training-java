package com.epam.training.elene_shubitidze.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class for creating WebDriver instances
 * Uses WebDriverManager for automatic driver management
 * Supports Chrome and Firefox browsers with configured options
 */

public class BrowserFactory {

    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);

    /**
     * Create WebDriver instance based on a browser type
     * @param browserType type of browser to create
     * @return configured WebDriver instance
     * @throws IllegalArgumentException if a browser type is not supported
     */
    public static WebDriver createDriver(BrowserType browserType) {
        logger.info("Creating driver for browser: {}", browserType);

        WebDriver driver;

        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-infobars");
                driver = new ChromeDriver(chromeOptions);
                logger.debug("ChromeDriver created with options");
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-notifications");
                driver = new FirefoxDriver(firefoxOptions);
                logger.debug("FirefoxDriver created with options");
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        logger.info("Driver created successfully");
        return driver;
    }
}

