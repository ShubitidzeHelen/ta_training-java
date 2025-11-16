package org.example;

import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;


public class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected int timeout;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("") String browserParam) {

        String browser = browserParam.isEmpty() ? ConfigReader.getProperty("browser") : browserParam;
        timeout = ConfigReader.getIntProperty("timeout");

        logger.info("Initializing browser: {}", browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                DriverManager.initDriver(browser);
                break;
            case "firefox":
                DriverManager.initDriver(browser);
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("url"));
        logger.info("Opened URL: {}", ConfigReader.getProperty("url"));

    }


    @AfterMethod
    public void tearDown() {
        logger.info("Quitting WebDriver");
        DriverManager.quitDriver();
    }

}
