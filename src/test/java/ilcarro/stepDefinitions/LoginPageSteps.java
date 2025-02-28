package ilcarro.stepDefinitions;

import ilcarro.pages.CarPage;
import ilcarro.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
    @And("The user enters valid data")
    public void userEnterValidCredentials() {
        new LoginPage().enterCredentials();
    }

    @And("The user presses the Yalla button")
    public void userClickOnYallaButton() {
        new LoginPage().clickOnYallaButton();
    }

    @Then("The user checks the display of the message about the successful login")
    public void userVerifySuccessLoginMessage() {
        new LoginPage().verifyTextMessage("Logged in success");
    }

    @And("The user introduces valid email and invalid password")
    public void userEntersInvalidPassword(DataTable table) {
        new LoginPage().enterCredentials(table);
    }

    @Then("The user checks the display of the message about the unsuccessful login")
    public void userCheckErrorMessage() {
        new LoginPage().verifyTextMessage("\"Login or Password incorrect\"");
    }

    @And("The user enters the valid data {string} and {string}")
    public void userEnterCredentials() {
        new LoginPage().enterCredentials();
    }

    @And("The user introduces valid email and password data")
    public void userEnterValidCredentialsTable(DataTable table) {
        new LoginPage().enterCredentials(table);
    }

}
