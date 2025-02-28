Feature: Home page

  @HomeComponent
  Scenario: Check that the heading of the home page is displayed
    Given The user launches a browser
    When The user opens the home page Ilcarro
    Then Check that the heading of the home page is displayed
    And The user closes the browser