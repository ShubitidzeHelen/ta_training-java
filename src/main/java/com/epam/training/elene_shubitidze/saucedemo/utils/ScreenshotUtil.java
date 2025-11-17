package com.epam.training.elene_shubitidze.saucedemo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for taking and saving screenshots
 */

public class ScreenshotUtil {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);
    private static final ConfigReader config = ConfigReader.getInstance();

    /**
     * Take screenshot and save to a configured path
     * @param driver WebDriver instance
     * @param testName name of the test for file naming
     * @return path to saved screenshot, or null if failed
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        if (!config.takeScreenshotOnFailure()) {
            logger.debug("Screenshot capture is disabled in configuration");
            return null;
        }

        try {
            // Create screenshot directory if it doesn't exist
            String screenshotDir = config.getScreenshotPath();
            Path dirPath = Paths.get(screenshotDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                logger.info("Created screenshot directory: {}", screenshotDir);
            }

            // Generate filename with timestamps
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = String.format("%s_%s.png",
                    sanitizeFileName(testName), timestamp);
            String filePath = screenshotDir + fileName;

            // Take a screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);

            // Copy to destination
            Files.copy(srcFile.toPath(), destFile.toPath());

            logger.info("Screenshot saved: {}", filePath);
            return filePath;

        } catch (IOException e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Sanitize test name for use in filename
     * @param name test name
     * @return sanitized name
     */
    private static String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    /**
     * Take a screenshot with automatic test name
     * @param driver WebDriver instance
     * @return path to saved screenshot, or null if failed
     */
    public static String takeScreenshot(WebDriver driver) {
        String testName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return takeScreenshot(driver, testName);
    }
}

