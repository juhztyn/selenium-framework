@login
Feature: Login Page

  Background:
    Given the user is on the "LOGIN" page

  @ui-validation
  Scenario Outline: Validate general elements on the page appear
    When the user should see the "<element>" on the login page
    Examples:
      | element                     |
      | login logo                  |
      | accepted usernames section  |
      | accepted passwords section  |
      | username field              |
      | password field              |
      | login button                |

  @functional
  Scenario: Successful login with valid credentials
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the "HOME" page

  @functional
  Scenario: Unsuccessful login with locked out credentials
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should see the "login error container" on the login page
    And the user should see the "login error message" on the login page
    And the user should see the "login error close button" on the login page

  # Add functional test to check specific error message

  # Add E2E test here