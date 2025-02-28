Feature: LOGIN

  Background: Open the browser and go to the home page
    Given The user launches a browser
    When The user opens the home page Ilcarro

  @Login
  Scenario: Successful user login 1
    And The user clicks on the Login link
    And The user enters valid data
    And The user presses the Yalla button
    Then The user checks the display of the message about the successful login
    And The user closes the browser

  @LoginWitString
  Scenario: Successful user login 2
    And The user clicks on the Login link
    And The user enters the valid data "test_qa@gmail.com" and "password@1"
    And The user presses the Yalla button
    Then The user checks the display of the message about the successful login
    And The user closes the browser

  @LoginWitTable
  Scenario: Successful user login 3
    And The user clicks on the Login link
    And The user introduces valid email and password data
      | email             | password   |
      | test_qa@gmail.com | Password@1 |
    And The user presses the Yalla button
    Then The user checks the display of the message about the successful login
    And The user closes the browser

  @InvalidPassword
  Scenario Outline: The unsuccessful login of the user
    AndThe user clicks on the Login link
    And The user introduces valid email and invalid password
      | email   | password   |
      | <email> | <password> |
    And The user presses the Yalla button
    Then The user checks the display of the message about the unsuccessful login
    And The user closes the browser

    Examples:
      | email             | password   |
      | test_qa@gmail.com | Password1  |
      | test_qa@gmail.com | password@1 |
      | test_qa@gmail.com | Password@  |