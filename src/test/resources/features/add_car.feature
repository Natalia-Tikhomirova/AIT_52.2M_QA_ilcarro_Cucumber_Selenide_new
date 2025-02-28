Feature: Adding a car

  Background: Open the browser and go to the home page
    Given The user launches a browser
    When The user opens the home page Ilcarro
    And The user clicks on the Login link
    And The user enters valid data
    And The user presses the Yalla button
    Then The user checks the display of the message about the successful login

  @AddCar
  Scenario: Successful adding a car
    And The user presses the Let the Car Work button
   #When The user enters the car data
   #   | location | manufacture | model   | year | fuel   | seats | classCar | price | about        | photo                           |
    #  | Haifa | Toyota | Corolla | 2000 | Diesel | 4 | Sedan | 1000 | The best car | "C:\AIT_TR_QA\5_ilcarro_Cucumber_Selenide\Los.png" |
    When The user enters the data of the ISproperties car
    And The user presses the Submit button
    Then The user checks the message "Toyota Corolla added successful"
    And The user closes the browser