package com.epam.training.elene_shubitidze.saucedemo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton configuration reader for managing application properties
 * Reads configuration from config.properties file in resources folder
 */

public class ConfigReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static ConfigReader instance;
    private final Properties properties;

    /**
     * Private constructor to enforce singleton pattern
     * Loads properties from config.properties file
     */
    private ConfigReader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
                logger.info("Configuration loaded successfully");
            } else {
                throw new RuntimeException("config.properties not found in resources folder");
            }
        } catch (IOException e) {
            logger.error("Error loading configuration: {}", e.getMessage());
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Get singleton instance of ConfigReader
     * Thread-safe implementation using double-checked locking
     * @return ConfigReader instance
     */
    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    /**
     * Get base URL for the application
     * @return base URL as String
     */
    public String getBaseUrl() {
        return properties.getProperty("base.url", "https://www.saucedemo.com/");
    }

    /**
     * Get browser type
     * @return browser name as String (chrome, firefox)
     */
    public String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    /**
     * Get explicit wait timeout in seconds
     * @return timeout in seconds
     */
    public int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait", "10"));
    }

    /**
     * Get page load timeout in seconds
     * @return timeout in seconds
     */
    public int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout", "15"));
    }

    /**
     * Check if parallel execution is enabled
     * @return true if parallel execution enabled, false otherwise
     */
    public boolean isParallelExecution() {
        return Boolean.parseBoolean(properties.getProperty("parallel.execution", "true"));
    }

    /**
     * Get thread count for parallel execution
     * @return number of threads
     */
    public int getThreadCount() {
        return Integer.parseInt(properties.getProperty("thread.count", "2"));
    }

    /**
     * Check if screenshot should be taken on test failure
     * @return true if enabled, false otherwise
     */
    public boolean takeScreenshotOnFailure() {
        return Boolean.parseBoolean(properties.getProperty("take.screenshot.on.failure", "true"));
    }

    /**
     * Get screenshot's storage path
     * @return path as String
     */
    public String getScreenshotPath() {
        return properties.getProperty("screenshot.path", "./test-output/screenshots/");
    }

    /**
     * Get log level
     * @return log level as String (INFO, DEBUG, ERROR)
     */
    public String getLogLevel() {
        return properties.getProperty("log.level", "INFO");
    }

    /**
     * Get log file's path
     * @return path as String
     */
    public String getLogPath() {
        return properties.getProperty("log.path", "./logs/test-execution.log");
    }

    /**
     * Get a generic string property with default
     * @param key property key
     * @param defaultValue default if not found
     * @return String property value
     */
    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get a generic int property with default
     * @param key property key
     * @param defaultValue default if not found
     * @return int property value
     */
    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    /**
     * Get a generic boolean property with default
     * @param key property key
     * @param defaultValue default if not found
     * @return boolean property value
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }
}
