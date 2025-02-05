# BDD Selenium Cucumber Framework

A Behavior-Driven Development (BDD) framework using Selenium, Cucumber, and JUnit for automating UI tests, following best practices like the Page Object Model (POM) and Singleton pattern for WebDriver management.

## Description

This project is designed to automate web UI testing using BDD principles with Selenium, Cucumber, and JUnit. It supports a modular, scalable approach that can be used across multiple projects. The framework follows the Page Object Model (POM) and ensures efficient WebDriver management using a Singleton pattern, supporting dynamic environment switching through configuration files. The framework is designed to be highly maintainable and reusable for various test automation scenarios.

## Getting Started

### Dependencies

* Java JDK 11+
* Maven
* Selenium WebDriver
* Cucumber
* JUnit
* ChromeDriver or GeckoDriver (depending on the browser)

Ensure that you have the appropriate browser driver (e.g., `chromedriver`, `geckodriver`) installed and available in your system path.

### Installing

1. Clone the repository to your local machine: `git clone https://github.com/your-repo-url.git`
2. Navigate to the project directory: `cd your-project-directory`
3. Install dependencies using Maven: `mvn clean install`

### Executing program

To execute tests, use the following steps:

1. To run tests with Maven: `mvn test`
2. To run specific Cucumber features: `mvn test -Dcucumber.options=“src/test/resources/features/login.feature”`
3. Modify the `config.properties` file to change base URLs or browser options as needed. 
Examples inside of `config.properties` include: 
   1. `base.url.local=https://www.saucedemo.com/`
   2. `browser=chrome`

## Help

If you encounter any issues with browser drivers, ensure that they are up-to-date and correctly configured in your system path.

For help with Cucumber options: `mvn test -Dcucumber.options=”–help”`

## Authors

Justin Harrison 
[GitHub Profile](https://github.com/juhztyn)

## Version History

* 0.1
    * Initial Release with basic BDD setup using Selenium, Cucumber, and JUnit