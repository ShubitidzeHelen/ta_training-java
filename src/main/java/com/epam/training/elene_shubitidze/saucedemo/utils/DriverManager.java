package com.epam.training.elene_shubitidze.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton WebDriver manager using ThreadLocal for parallel execution
 * Ensures thread-safe driver instances for concurrent test execution
 */

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Private constructor to support Singleton pattern
     */
    private DriverManager() {}

    /**
     * Get WebDriver instance for current thread
     * @return WebDriver instance or null if not set
     */
    public static WebDriver getDriver() {
        return driver.get();
    }


    /**
     * Set WebDriver instance for current thread
     * @param browserType type of browser to create
     */
    public static void setDriver(BrowserType browserType) {
        logger.info("Setting up driver for thread: {}", Thread.currentThread().threadId());

        if (driver.get() != null) {
            logger.warn("Driver already exists for this thread, quitting existing driver");
            quitDriver();
        }

        WebDriver webDriver = BrowserFactory.createDriver(browserType);
        driver.set(webDriver);
        logger.info("Driver set successfully for thread: {}", Thread.currentThread().threadId());
    }

    /**
     * Set WebDriver instance using browser name string
     * @param browserName name of browser (chrome, firefox)
     */
    public static void setDriver(String browserName) {
        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());
        setDriver(browserType);
    }

    /**
     * Quit driver and remove from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            logger.info("Quitting driver for thread: {}", Thread.currentThread().getId());
            webDriver.quit();
            driver.remove();
            logger.info("Driver quit successfully");
        } else {
            logger.warn("No driver to quit for thread: {}", Thread.currentThread().getId());
        }
    }

}
