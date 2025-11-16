package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {}

    public static void initDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--headless=new"); // optional for stability
                    chromeOptions.addArguments("--user-data-dir=C:/temp/chrome-profile-" + Thread.currentThread().getId());
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    // If Firefox is not in default path, set binary explicitly:
                    // firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
