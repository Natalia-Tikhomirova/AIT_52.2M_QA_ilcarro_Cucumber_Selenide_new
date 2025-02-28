package ilcarro.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ilcarro.utils.PropertiesLoader;
import io.cucumber.datatable.DataTable;

import java.io.File;
import java.time.Duration;
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
        enterCarDataToForm(location, manufacture, model, year, fuel, seats, classCar, price, about, photo);
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

    public void inputMultipleAutoDetailsFromCsv(String filePath) {
        List<String[]> carData = PropertiesLoader.loadCsv(filePath);

        if (carData.size() > 1) { // Пропускаем заголовок
            for (int i = 1; i < carData.size(); i++) {
                String[] car = carData.get(i);
                String manufacture = car[0];
                String model = car[1];

                System.out.println("Добавление автомобиля: " + manufacture + " " + model);

                $("#pickUpPlace").shouldBe(Condition.visible, Duration.ofSeconds(10))
                        .setValue(PropertiesLoader.loadProperty("valid.location"));
                $(".pac-item").shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

                $("#make").setValue(manufacture);
                $("#model").setValue(model);
                $("#year").setValue(car[2]);
                $("#fuel").selectOption(car[3]);
                $("#seats").setValue(car[4]);
                $("#class").setValue(car[5]);
                $("#serialNumber").setValue(String.valueOf(System.currentTimeMillis()));
                $("#price").setValue(car[6]);
                $("#about").setValue(car[7]);

                $("#about").scrollIntoView(true);

                String photoPath = PropertiesLoader.loadProperty("valid.photos");
                File photoFile = new File(photoPath);

                if (!photoFile.exists()) {
                    throw new RuntimeException("Фото не найдено: " + photoFile.getAbsolutePath());
                }

                $("#photos").uploadFile(photoFile);

                // Прокручиваем к кнопке Submit и кликаем
                clickOnSubmitButtonWithScroll();

                // Ждем сообщение об успешном добавлении машины
                waitForSuccessMessage(manufacture + " " + model + " added successful");

                // Обновляем страницу перед добавлением следующей машины
                if (i < carData.size() - 1) { // Не обновлять после последней машины
                    System.out.println("Обновляем страницу...");
                    Selenide.refresh();

                    // Ждем загрузки страницы и кликаем "Let the Car Work"
                    $x("(//a[@class='navigation-link'])[2]").shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
                }
            }
        }
    }

    private void clickOnSubmitButtonWithScroll() {
        SelenideElement submitButton = $x("//button[text()='Submit']");
        submitButton.scrollIntoView(true);

        // Ждем, пока кнопка станет активной
        submitButton.shouldBe(Condition.enabled, Duration.ofSeconds(10));

        submitButton.click();
    }

    public void waitForSuccessMessage (String expectedMessage){
        $(".message").shouldHave(Condition.text(expectedMessage), Duration.ofSeconds(10));
    }
}

