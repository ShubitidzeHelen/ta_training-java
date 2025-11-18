package com.epam.training.elene_shubitidze.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the Login page of the SauceDemo website.
 * Provides methods to interact with the login form and validate login errors.
 */
public class LoginPage extends BasePage {

    private final By usernameField = By.cssSelector("#user-name");
    private final By passwordField = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("#login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    /**
     * Constructor for LoginPage
     *
     * @param driver WebDriver instance used to interact with the page
     */
    public LoginPage (WebDriver driver) {
        super(driver);
    }

    /**
     * Types the given username into the username input field.
     *
     * @param username the username to enter
     */
    public void enterUsername(String username) {
        logger.debug("Entering username: {}", username);
        type(usernameField, username);
    }

    /**
     * Types the given password into the password input field.
     *
     * @param password the password to enter
     */
    public void enterPassword(String password) {
        logger.debug("Entering password: {}", password);
        type(passwordField, password);
    }


    /**
     * Clears the username input field.
     */
    public void clearUsername() {
        logger.debug("Clearing username");
        clear(usernameField);
    }


    /**
     * Clears the password input field.
     */
    public void clearPassword() {
        logger.debug("Clearing password");
        clear(passwordField);
    }


    /**
     * Clicks the login button.
     *
     * @return InventoryPage object representing the next page after login
     */
    public InventoryPage clickLogin () {
        click(loginButton);
        return new InventoryPage(driver);
    }

    /**
     * Checks whether the error message is displayed on the page.
     *
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorDisplayed () {
        logger.debug("Checking for error message");
        return isDisplayed(errorMessage);
    }

    /**
     * Retrieves the text of the error message displayed on the login page.
     *
     * @return the error message text
     */
    public String getErrorMessage () {
        logger.debug("Fetching error message");
        return waitForVisibility(errorMessage).getText();
    }

 }
