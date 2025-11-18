package com.epam.training.elene_shubitidze.saucedemo.data;

import java.util.Arrays;
import java.util.List;

/**
 * Test data constants for SauceDemo website
 * Contains all accepted usernames, passwords, and expected error messages
 */

public class TestDataConstants {

    //List of URLs
    public static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    // List of page titles
    public static final String DASHBOARD_TITLE = "Swag Labs";

    //List of active usernames
    public static final List<String> ACTIVE_USERNAMES = Arrays.asList(
            "standard_user",
            "problem_user",
            "performance_glitch_user",
            "error_user",
            "visual_user"
    );

    //Locked out username
    public static final String LOCKED_OUT_USERNAME = "locked_out_user";

    //Active password for any user
    public static final String VALID_PASSWORD = "secret_sauce";


    //List of possible error messages
    public static final String ERROR_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Epic sadface: Password is required";
    public static final String ERROR_INVALID_CREDENTIALS = "Epic sadface: Username and password do not match any user in this service";
    public static final String ERROR_LOCKED_OUT = "Epic sadface: Sorry, this user has been locked out.";


}

