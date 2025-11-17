package com.epam.training.elene_shubitidze.saucedemo.data;

import org.testng.annotations.DataProvider;

/**
 * TestNG DataProvider for login test scenarios
 * Provides test data for UC-1, UC-2, and UC-3 use cases
 * Supports parallel execution
 */

public class LoginDataProvider {

    /**
     * Provides random username and password combinations
     * Can be used in different negative test scenarios
     *
     * @return Object[][] with username, password
     */
    @DataProvider(name = "randomCredentials", parallel = true)
    public static Object[][] getRandomCredentials() {
        return new Object[][] {
                {"any_username", "any_password"},
                {"test_user", "test_pass"}
        };
    }

    /**
     * Provides valid usernames and valid password
     * Can be used for positive login scenarios
     *
     * @return Object[][] with username, password
     */
    @DataProvider(name = "validCredentials", parallel = true)
    public static Object[][] getValidCredentials() {
            return TestDataConstants.ACTIVE_USERNAMES.stream()
                    .map(username -> new Object[]{username, TestDataConstants.VALID_PASSWORD})
                    .toArray(Object[][]::new);
        }
}
