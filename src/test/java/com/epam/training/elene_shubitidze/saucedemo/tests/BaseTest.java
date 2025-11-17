package com.epam.training.elene_shubitidze.saucedemo.tests;

import com.epam.training.elene_shubitidze.saucedemo.utils.BrowserType;
import com.epam.training.elene_shubitidze.saucedemo.utils.DriverManager;
import com.epam.training.elene_shubitidze.saucedemo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.time.Duration;
import org.testng.ITestResult;
import com.epam.training.elene_shubitidze.saucedemo.utils.ScreenshotUtil;


public class BaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected ConfigReader config;

    /**
     * Initialize configuration before test class execution
     */
    @BeforeClass
    public void beforeClass() {
        config = ConfigReader.getInstance();
        logger.info("Test class initialization started");
    }

    /**
     * Set up WebDriver before each test method
     *
     * @param browser browser name from TestNG parameters (chrome or firefox)
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Setting up test with browser: {}", browser);

        // Create driver using BrowserType enum
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        DriverManager.setDriver(browserType);

        WebDriver driver = DriverManager.getDriver();

        // Configure page load timeouts
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(config.getPageLoadTimeout())
        );

        // Navigate to base URL
        driver.get(config.getBaseUrl());
        logger.info("Navigated to: {}", config.getBaseUrl());
    }

    /**
     * Clean up WebDriver after each test method
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver currentDriver = DriverManager.getDriver();

        if (currentDriver != null) {
            // Take a screenshot on failure
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.error("Test failed: {}", result.getName());
                String screenshotPath = ScreenshotUtil.takeScreenshot(currentDriver, result.getName());
                if (screenshotPath != null) {
                    logger.info("Screenshot saved at: {}", screenshotPath);
                }
            }

            logger.info("Closing browser for thread: {}", Thread.currentThread().threadId());
            DriverManager.quitDriver();
        }
    }
}
