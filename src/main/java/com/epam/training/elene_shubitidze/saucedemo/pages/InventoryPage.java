package com.epam.training.elene_shubitidze.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object representing the Inventory page of the SauceDemo website.
 * Provides methods to interact with and verify elements on the inventory page.
 */
public class InventoryPage extends BasePage {

    private final By pageTitle = By.cssSelector(".title");
    private static final String INVENTORY_PAGE_TITLE = "Products";

    /**
     * Constructor for InventoryPage
     *
     * @param driver WebDriver instance used to interact with the page
     */
    public InventoryPage (WebDriver driver) {
        super(driver);
    }

    /**
     * Waits until the Inventory page is loaded.
     * Specifically waits for the page title to display the required text.
     * @return true if page loads
     */
    public boolean isInventoryPageLoaded() {
        try {
            logger.debug("Checking if inventory page is loaded");
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, INVENTORY_PAGE_TITLE));
        } catch (TimeoutException e) {
            logger.warn("Inventory page did not load in time");
            return false;
        }
    }
}
