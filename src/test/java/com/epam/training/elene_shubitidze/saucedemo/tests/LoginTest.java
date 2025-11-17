package com.epam.training.elene_shubitidze.saucedemo.tests;

import com.epam.training.elene_shubitidze.saucedemo.data.LoginDataProvider;
import com.epam.training.elene_shubitidze.saucedemo.data.TestDataConstants;
import com.epam.training.elene_shubitidze.saucedemo.pages.InventoryPage;
import com.epam.training.elene_shubitidze.saucedemo.pages.LoginPage;
import com.epam.training.elene_shubitidze.saucedemo.utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Login functionality tests for SauceDemo application
 * Tests UC-1, UC-2, and UC-3 scenarios with parametrized data
 */

public class LoginTest extends  BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    /**
     * UC-1: Test Login form with empty credentials
     * Steps:
     * 1. Type any credentials into Username and Password fields
     * 2. Clear the inputs
     * 3. Click Login button
     * 4. Verify an error message: "Username is required"
     */

    @Test(dataProvider = "randomCredentials", dataProviderClass = LoginDataProvider.class,
            description = "UC-1: Verify error message when credentials are cleared")
    public void testLoginWithEmptyCredentials(String username, String password) {
        logger.info("TC-UC1: Testing login with empty credentials");
        logger.info("Username to type: {}, Password to type: {}", username, password);
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        // Type credentials
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Clear inputs
        loginPage.clearUsername();
        loginPage.clearPassword();

        // Click Login
        loginPage.clickLogin();

        // Verify error message
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        assertThat(loginPage.getErrorMessage())
                .as("Error message text")
                .isEqualTo(TestDataConstants.ERROR_USERNAME_REQUIRED);

        logger.info("Test passed: Error message '{}' displayed correctly",
                TestDataConstants.ERROR_USERNAME_REQUIRED);
    }

    /**
     * UC-2: Test Login form with credentials by passing Username
     * Steps:
     * 1. Type any credentials in username
     * 2. Enter password
     * 3. Clear the Password input
     * 4. Click Login button
     * 5. Verify error message: "Password is required"
     */

    @Test(dataProvider = "randomCredentials", dataProviderClass = LoginDataProvider.class,
            description = "UC-2: Verify error message when password is cleared")
    public void testLoginWithMissingPassword(String username, String password) {
        logger.info("TC-UC2: Testing login with missing password");
        logger.info("Username: {}", username);

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        // Type credentials
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Clear password
        loginPage.clearPassword();

        // Click Login
        loginPage.clickLogin();

        // Verify error message
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        assertThat(loginPage.getErrorMessage())
                .as("Error message text")
                .isEqualTo(TestDataConstants.ERROR_PASSWORD_REQUIRED);

        logger.info("Test passed: Error message '{}' displayed correctly",
                TestDataConstants.ERROR_PASSWORD_REQUIRED);

    }

    /**
     * UC-3: Test Login form with valid credentials
     * Steps:
     * 1. Type valid username from accepted usernames
     * 2. Enter password as 'secret_sauce'
     * 3. Click Login button
     * 4. Verify dashboard title is "Swag Labs"
     */

    @Test(dataProvider = "validCredentials", dataProviderClass = LoginDataProvider.class,
            description = "UC-3: Verify successful login with valid credentials")
    public void testLoginWithValidCredentials(String username, String password) {
        logger.info("TC-UC3: Testing login with valid credentials");
        logger.info("Username: {}, Password: {}", username, password);

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        // Type credentials
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click Login
        InventoryPage inventoryPage = loginPage.clickLogin();

        // Validate moving to the next page and the title "Swag Labs" in the dashboard
        inventoryPage.waitForInventoryPageToLoad();
        assertThat(inventoryPage.getCurrentUrl())
                .as("URL should be: "+TestDataConstants.INVENTORY_PAGE_URL)
                .isEqualTo(TestDataConstants.INVENTORY_PAGE_URL);

        assertThat(inventoryPage.getPageTitle())
                .as("Dashboard title should be 'Swag Labs'")
                .isEqualTo(TestDataConstants.DASHBOARD_TITLE);

        logger.info("Test passed: Dashboard title verified as '{}'",
                TestDataConstants.DASHBOARD_TITLE);
    }
}
