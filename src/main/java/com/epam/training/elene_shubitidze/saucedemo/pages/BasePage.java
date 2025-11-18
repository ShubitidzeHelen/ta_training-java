package com.epam.training.elene_shubitidze.saucedemo.pages;

import com.epam.training.elene_shubitidze.saucedemo.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Locale;

/**
 * Base Page Object class with common functionality for all pages
 * Provides reusable methods for waiting, typing, clearing, and clicking
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigReader config;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    /**
     * Constructor initializes WebDriver, ConfigReader and WebDriverWait
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.config = ConfigReader.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
        logger.debug("Initialized page: {}", this.getClass().getSimpleName());
    }

    /**
     * Navigate to a specific URL
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        logger.info("Navigating to: {}", url);
        driver.get(url);
    }

    /**
     * Get current page title
     * @return page title as String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current page URL
     * @return current URL as String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Wait for an element to be visible
     * @param locator By
     * @return WebElement once visible
     */
    protected WebElement waitForVisibility(By locator) {
        logger.debug("Waiting for element to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for an element to be clickable
     * @param locator By
     * @return WebElement once clickable
     */
    protected WebElement waitForClickability(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Click WebElement
     * @param locator By
     */
    protected void click(By locator) {
        logger.debug("Clicking element: {}", locator);
        waitForClickability(locator).click();
    }

    /**
     * Type text into an element using By locator
     * @param locator By
     * @param text text to type
     */
    protected void type(By locator, String text) {
        logger.debug("Typing text into element: {}", locator);
        waitForVisibility(locator).sendKeys(text);
    }

    /**
     * Clear an element using By locator, keys selected based on os
     * @param locator By
     * */
    protected void clear(By locator) {
        logger.debug("Clearing element: {}", locator);
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        Keys selectAll = os.contains("mac") ? Keys.COMMAND : Keys.CONTROL;

        WebElement element = waitForVisibility(locator);
        element.sendKeys(Keys.chord(selectAll, "a"));
        element.sendKeys(Keys.DELETE);
    }

    /**
     * Check if an element is displayed
     * @param locator By
     * @return true if displayed, false otherwise
     */
    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", e.getMessage());
            return false;
        }
    }
}