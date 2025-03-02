package ilcarro.core;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasePage {
    public void launchBrowser() {
        open("about:blank");
        //getWebDriver().manage().window().setPosition(new Point(2200, 200));
        getWebDriver().manage().window().maximize();
    }

    public void quitBrowser() {
        WebDriverRunner.getWebDriver().quit();
    }
}
