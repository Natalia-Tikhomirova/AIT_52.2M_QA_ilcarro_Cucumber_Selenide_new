package ilcarro.pages;

import com.codeborne.selenide.CollectionCondition;
import ilcarro.utils.PropertiesLoader;
import io.cucumber.datatable.DataTable;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CarPage {

    public static String location = PropertiesLoader.loadProperty("valid.location");
    public static String manufacture = PropertiesLoader.loadProperty("valid.manufacture");
    public static String model = PropertiesLoader.loadProperty("valid.model");
    public static String year = PropertiesLoader.loadProperty("valid.year");
    public static String fuel = PropertiesLoader.loadProperty("valid.fuel");
    public static String seats = PropertiesLoader.loadProperty("valid.seats");
    public static String carClass = PropertiesLoader.loadProperty("valid.carClass");
    public static String serialNumber = String.valueOf(System.currentTimeMillis());
    public static String price = PropertiesLoader.loadProperty("valid.price");
    public static String about = PropertiesLoader.loadProperty("valid.about");
    public static String photos = PropertiesLoader.loadProperty("valid.photos");


    public void inputAutoDetailsFromProperties() {
        $("#pickUpPlace").setValue(location);
        $(".pac-item").shouldBe(visible).click(); //клик по первому в списке варианту
        $("#make").setValue(manufacture);
        $("#model").setValue(model);
        $("#year").setValue(year);
        $("#fuel").selectOption(fuel);
        $("#seats").setValue(seats);
        $("#class").setValue(carClass);
        $("#serialNumber").setValue(serialNumber);
        $("#price").setValue(price);
        $("#about").setValue(about);
        $("#photos").setValue(photos);
    }

    public void enterCarData(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String location = dataTable.get(0).get("location");
        String manufacture = dataTable.get(0).get("manufacture");
        String model = dataTable.get(0).get("model");
        String year = dataTable.get(0).get("year");
        String fuel = dataTable.get(0).get("fuel");
        String seats = dataTable.get(0).get("seats");
        String classCar = dataTable.get(0).get("classCar");
        String price = dataTable.get(0).get("price");
        String about = dataTable.get(0).get("about");
        String photo = dataTable.get(0).get("photo");
        enterCarDataToForm(location, manufacture,model,year,fuel,seats,classCar,price,about,photo);
    }

    private void enterCarDataToForm(
            String location,
            String manufacture,
            String model,
            String year,
            String fuel,
            String seats,
            String classCar,
            String price,
            String about,
            String photo) {
        $("#pickUpPlace").setValue(location);
        $$(".pac-item").shouldBe(CollectionCondition.sizeGreaterThan(0));
        $$(".pac-item").first().click();
        $("#make").setValue(manufacture);
        $("#model").setValue(model);
        $("#year").setValue(year);
        $("#fuel").selectOption(fuel);
        $("#seats").setValue(seats);
        $("#class").setValue(classCar);
        $("#serialNumber").setValue(String.valueOf(System.currentTimeMillis()));
        $("#price").setValue(price);
        $("#about").setValue(about);

//        $("#photos").setValue(photo);
//        $("#photos").val(photo);
//        $("#photos").append(photo);

        $("#photos").uploadFile(new File(photo));
    }

    public void clickOnSubmitButton() {
        $x("//button[text()='Submit']").click();
    }

    public void verifySuccessMessage(String textToCheck) {
        $(".message").shouldHave(text(textToCheck));
    }
}
