package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private By usernameField = By.cssSelector("#user-name");
    private By passwordField = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage (WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clearUsername() {
        clear(usernameField);
    }

    public void clearPassword() {
        clear(passwordField);
    }

    public void clickLogin () {
        waitForClickability(loginButton).click();
    }

    public String getErrorMessage () {
        return driver.findElement(errorMessage).getText();
    }

 }
