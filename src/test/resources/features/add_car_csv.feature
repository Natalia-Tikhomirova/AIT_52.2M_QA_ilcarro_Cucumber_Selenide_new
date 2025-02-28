Feature: Adding multiple cars from CSV

  Background: Open the browser and go to the home page
    Given The user launches a browser
    When The user opens the home page Ilcarro
    And The user clicks on the Login link
    And The user enters valid data
    And The user presses the Yalla button
    Then The user checks the display of the message about the successful login


  @AddCarFromCSV
  Scenario: Successful adding multiple cars from CSV
    And The user presses the Let the Car Work button
    When The user enters multiple cars data from CSV file "src/test/resources/cars.csv"
    And The user closes the browser