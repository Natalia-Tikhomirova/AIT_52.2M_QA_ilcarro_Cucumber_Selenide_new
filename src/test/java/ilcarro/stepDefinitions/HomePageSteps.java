package ilcarro.stepDefinitions;

import ilcarro.core.BasePage;
import ilcarro.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


public class HomePageSteps {
    @Given("The user launches a browser")
    public void userLaunchesBrowser() {
        new BasePage().launchBrowser();
    }

    @When("The user opens the home page Ilcarro")
    public void userOpensHomePage() {
        new HomePage().openHomePage();
    }

    @Then("Check that the heading of the home page is displayed")
    public void verifyHomePageTitle() {
        Assertions.assertTrue(new HomePage().isHomePageTitlePresent());
    }

    @And("The user closes the browser")
    public void userQuitBrowser() {
        new BasePage().quitBrowser();
    }

    @And("The user clicks on the Login link")
    public void userClickOnLoginLink() {
        new HomePage().clickOnLoginLink();
    }

    @And("The user presses the Let the Car Work button")
    public void userClickOnLetTheCarWorkLink() {
        new HomePage().clickOnLetTheCarWorkLink();
    }
}
