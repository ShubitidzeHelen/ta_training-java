package org.example;

import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.utils.DriverManager.getDriver;

public class LoginTest extends  BaseTest {
    @Test(description = "UC-1: Test Login form with empty credentials")
    public void testEmptyCredentials() {
        logger.info("Starting UC-1: Empty credentials test");
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("dummy");
        loginPage.enterPassword("dummy");
        loginPage.clearUsername();
        loginPage.clearPassword();
        logger.info("Clicking login button with empty credentials");
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        logger.info("Captured error message: {}", error);
        assertThat(error).isEqualTo("Epic sadface: Username is required");
        logger.info("UC-1 test passed");
    }

    @Test(description = "UC-2: Test Login form with empty password")
    public void testEmptyPassword() {
        logger.info("Starting UC-2: Empty password test");
        WebDriver driver = getDriver();


        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("dummy");
        loginPage.clearPassword();

        logger.info("Clicking login button with empty password");
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        logger.info("Captured error message: {}", error);

        assertThat(error).isEqualTo("Epic sadface: Password is required");
        logger.info("UC-2 test passed");
    }

    @Test(dataProvider = "validCredentials", description = "UC-3: Test valid login credentials")
    public void testValidLogin(String username, String password) {
        logger.info("Starting UC-3: Valid login test for user {}", username);
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        logger.info("Clicking login button with valid credentials");
        loginPage.clickLogin();

        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        logger.info("Page title after login: {}", actualTitle);

        assertThat(actualTitle).isEqualTo(expectedTitle);
        logger.info("UC-3 test passed for user {}", username);
    }

    @DataProvider(name = "validCredentials", parallel = true)
    public Object[][] provideValidCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }
}
