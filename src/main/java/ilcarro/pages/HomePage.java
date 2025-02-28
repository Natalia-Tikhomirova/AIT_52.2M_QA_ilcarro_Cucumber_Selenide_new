package ilcarro.pages;

import ilcarro.core.BasePage;
import ilcarro.utils.PropertiesLoader;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public static String baseUrl = PropertiesLoader.loadProperty("url");

    public void openHomePage() {
        open(baseUrl);
    }

    public boolean isHomePageTitlePresent() {
        return $("h1").shouldHave(text("Find your car now!")).isDisplayed();
    }

    public void clickOnLoginLink() {
         $(byXpath("//a[.=' Log in ']")).click();
    }

    public void clickOnLetTheCarWorkLink() {

        $(byXpath("//a[.=' Let the car work ']")).click();
        //$x("//a[.=' Let the car work ']").click(); // другой способ написания селектора
    }
}
