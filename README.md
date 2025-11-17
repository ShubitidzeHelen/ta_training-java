# SauceDemo Automated Tests
The main goal is to validate a login form using three use cases (UC-1, UC-2, UC-3), implementing parallel execution, data parametrization (Data Provider), and logging (SLF4J and Logback).

## Task Description
Launch URL: https://www.saucedemo.com/
**UC-1** Test Login form with empty credentials:
Type any credentials into "Username" and "Password" fields.
Clear the inputs.
Hit the "Login" button.
Check the error messages: "Username is required".
**UC-2** Test Login form with credentials by passing Username:
Type any credentials in username.
Enter password.
Clear the "Password" input.
Hit the "Login" button.
Check the error messages: "Password is required".
**UC-3** Test Login form with credentials by passing Username & Password:
Type credentials in username which are under Accepted username are sections.
Enter password as secret sauce.
Click on Login and validate the title “Swag Labs” in the dashboard.

Provide parallel execution, add logging for tests and use Data Provider to parametrize tests. Make sure that all tasks are supported by these 3 conditions: UC-1; UC-2; UC-3.

### Implementation Requirements
Test Automation tool: Selenium WebDriver;
Project Builder: Maven;
Browsers: 1) Firefox; 2) Chrome;
Locators: CSS;
Test Runner: TestNG;
[Optional] Patterns: 1) Singleton; 2) Adapter; 3) Strategy;
[Optional] Test automation approach: BDD;
Assertions: AssertJ;
[Optional] Loggers: SLF4J.

## Project Structure and Technology Stack

### Core Technologies

Page Object Model (POM) 
**Concurrency**: ThreadLocal for thread-safe parallel driver management.
**Patterns**: Singleton Pattern, Factory Pattern (`BrowserFactory` handles dynamic driver creation for Chrome and Firefox)
**Logging**: SLF4J + Logback 

## How to Run Tests

### Prerequisites
* **Java:** Version 21
* **Maven:** Version 3.9+

### Configuration Files

`config.properties`: Environment settings: `parallel.execution=true`, `browser=chrome` (default)
`testng.xml`: Test suite definition, configures **parallel="tests"** across Chrome and Firefox
`logback.xml`: Logging, sets log level to **INFO** for all custom packages

### Execution Command

To execute the test suite in parallel as configured in `testng.xml`:

```bash
mvn clean test
