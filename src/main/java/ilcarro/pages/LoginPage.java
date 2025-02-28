package ilcarro.pages;

import ilcarro.utils.PropertiesLoader;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public static String email = PropertiesLoader.loadProperty("valid.email");
    public static String password = PropertiesLoader.loadProperty("valid.password");

    public void clickOnYallaButton() {
       $(byXpath("//button[.='Y’alla!']")).click();
    }

    public void verifyTextMessage(String textToCheck) {
        $(".message").shouldHave(text(textToCheck));
        $x("//button[.='Ok']").click();
    }

    public void enterCredentials() {
        $("#email").setValue(email);
        $("#password").setValue(password);
    }

//    public void enterCredentials(String email, String password) {
//        $("#email").setValue(email);
//        $("#password").setValue(password);
//    }


    public void enterCredentials(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");

        enterCredentials();
    }
}
