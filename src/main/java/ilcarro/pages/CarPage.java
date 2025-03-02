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
    public static String photosFolder = PropertiesLoader.loadProperty("photos.folder");



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
        // Загружаем данные из CSV-файла
        List<String[]> carData = PropertiesLoader.loadCsv(filePath);
        // Получаем корневую директорию проекта
        String projectDir = System.getProperty("user.dir");
        // Формируем путь к папке с изображениями, используя свойство photos.folder из selenide.properties
        String imagesBasePath = projectDir + File.separator + photosFolder + File.separator;

        if (carData.size() > 1) { // Пропускаем заголовок CSV
            for (int i = 1; i < carData.size(); i++) {
                String[] car = carData.get(i);
                String manufacture = car[0];
                String model = car[1];
                String year = car[2];
                String fuel = car[3];
                String seats = car[4];
                String classCar = car[5];
                String price = car[6];
                String about = car[7];
                // Получаем имя файла фотографии
                String photoFileName = car[8];

                System.out.println("Adding a car: " + manufacture + " " + model + " added successful.");

                $("#pickUpPlace").shouldBe(Condition.visible, Duration.ofSeconds(10))
                        .setValue(PropertiesLoader.loadProperty("valid.location"));
                $(".pac-item").shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

                $("#make").setValue(manufacture);
                $("#model").setValue(model);
                $("#year").setValue(year);
                $("#fuel").selectOption(fuel);
                $("#seats").setValue(seats);
                $("#class").setValue(classCar);
                $("#serialNumber").setValue(String.valueOf(System.currentTimeMillis()));
                $("#price").setValue(price);
                $("#about").setValue(about);

                $("#about").scrollIntoView(true);

                // Полный путь к фотографии
                String fullPhotoPath = imagesBasePath + photoFileName;
                File photoFile = new File(fullPhotoPath);
                if (!photoFile.exists()) {
                    throw new RuntimeException("The photo was not found: " + photoFile.getAbsolutePath());
                }

                $("#photos").uploadFile(photoFile);

                clickOnSubmitButtonWithScroll();

                waitForSuccessMessage(manufacture + " " + model + " added successful");

                // Обновление страницы перед добавлением следующей машины (если это не последняя строка CSV)
                if (i < carData.size() - 1) {
                    System.out.println("Update the page...");
                    Selenide.refresh();
                    $x("(//a[@class='navigation-link'])[2]").shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
                }
            }
        }
    }

    private void clickOnSubmitButtonWithScroll() {
        SelenideElement submitButton = $x("//button[text()='Submit']");
        submitButton.scrollIntoView(true);

        // Ожидание активности кнопки
        submitButton.shouldBe(Condition.enabled, Duration.ofSeconds(10));

        submitButton.click();
    }

    public void waitForSuccessMessage (String expectedMessage){
        $(".message").shouldHave(Condition.text(expectedMessage), Duration.ofSeconds(10));
    }
}

