package ilcarro.stepDefinitions;

import ilcarro.pages.CarPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarPageSteps {
//    @When("The user enters the car data")
//    public void userEnterCarData(DataTable table){
//        new CarPage().enterCarData(table);
//    }
    @And("The user presses the Submit button")
    public void userClickOnSubmitButton() {
        new CarPage().clickOnSubmitButton();
    }

    @Then("The user checks the message {string}")
    public void userVerifySuccessMessage(String text) {
        new CarPage().verifySuccessMessage(text);
    }


    @When("The user enters the data of the ISproperties car")
    public void userEnterCarDataIsProperties() {
        new CarPage().inputAutoDetailsFromProperties();
    }

    @When("The user enters multiple cars data from CSV file {string}")
    public void theUserEntersMultipleCarsDataFromCSVFile(String filePath) {
        new CarPage().inputMultipleAutoDetailsFromCsv(filePath);
    }
}

