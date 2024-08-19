@login
Feature: Login Page

  Background:
    Given the user is on the "LOGIN" page

  @ui-validation
  Scenario: Validate general elements on the login page appear
    Then the user should see the "login logo" on the login page
    And the user should see the "accepted usernames section" on the login page
    And the user should see the "accepted passwords section" on the login page
    And the user should see the "username field" on the login page
    And the user should see the "password field" on the login page
    And the user should see the "login button" on the login page

  @functional
  Scenario: Successful login with valid credentials
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the "HOME" page

  @functional
  Scenario: Validate error shows up with locked out credentials
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should see the "login error container" on the login page
    And the user should see the "login error message" on the login page
    And the user should see the "login error close button" on the login page

  @functional
  Scenario: Validate specific error message shows with locked out credentials
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should see the "locked out error" on the login page

  @e2e
  Scenario: User successfully logs in and is redirected to the home page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the "HOME" page
    And the user should see the "products header" on the home page
