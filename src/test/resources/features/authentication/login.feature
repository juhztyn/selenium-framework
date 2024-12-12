@all @login
Feature: Login Page

  Background:
    Given the user navigates to the "LOGIN" page

  @ui-validation
  Scenario: General elements appear on the login page
    Then the user should see the "login logo" on the login page
    And the user should see the "accepted usernames section" on the login page
    And the user should see the "accepted passwords section" on the login page

  @ui-validation
  Scenario: Form elements appear on the login page
    And the user should see the "username field" on the login page
    And the user should see the "password field" on the login page
    And the user should see the "login button" on the login page

  @e2e @functional
  Scenario: Successful login with valid credentials takes me to home page
    When the user logs in as a "standard" user
    Then the user is on the "HOME" page
    And the user should see the "products header" on the home page

  @functional
  Scenario: Error appears when using locked out credentials
    When the user logs in as a "lockedout" user
    Then the user should see the "login error container" on the login page
    And the user should see the "login error" on the login page
    And the user should see the "login error close button" on the login page
    And the user should see the "locked out error message" on the login page
    And the user is on the "LOGIN" page