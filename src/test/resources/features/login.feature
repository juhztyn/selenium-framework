Feature: Login Functionality

  Scenario Outline: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid username "<username>" and password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the home page
    Examples:
    | username      | password     |
    | standard_user | secret_sauce |
    | problem_user  | secret_sauce |
    | error_user    | secret_sauce |
    | visual_user   | secret_sauce |