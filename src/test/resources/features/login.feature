Feature: Login Functionality

  Scenario Outline: Successful login with valid credentials
    Given the user is on the "LOGIN" page
    When the user enters valid username "<username>" and password "<password>"
    And the user clicks the login button
    And the user should be redirected to the "HOME" page
    And the user clicks on the hamburger button
    Then the user should see the navbar show up
    Examples:
    | username      | password     |
    | standard_user | secret_sauce |
    | problem_user  | secret_sauce |
    | error_user    | secret_sauce |
    | visual_user   | secret_sauce |
