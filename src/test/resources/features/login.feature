@smoke
Feature: Login Page

  Background:
    Given the user is on the "LOGIN" page

  Scenario Outline: Validate general elements on the page appear
    When the user should see the "<element>" on the login page
    Examples:
      | element                     |
      | login logo                  |
      | accepted usernames section  |
      | accepted passwords section  |
      | username field              |
      | password field              |

  Scenario: Successful login with valid credentials
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    And the user should be redirected to the "HOME" page

  Scenario: Unsuccessful login with locked out credentials
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should see the "login error" on the login page