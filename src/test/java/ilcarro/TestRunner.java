package ilcarro;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

//@RunWith(Cucumber.class) // Говорит фреймворку какой класс использовать для запуска тестов.
//@CucumberOptions // для настройки параметров выполнения тестов
//        (       // Указывает путь к файлам .feature, которые содержат сценарии тестов.
//                features = "src/test/resources/features",
//                // Где находятся реализации шагов (методов), соответствующих сценариям в .feature файлах
//                glue = "ilcarro.stepDefinitions",
//                // Определяет, какие плагины будут использоваться для формирования отчётов.
//                //      pretty: выводит шаги тестов в понятном для чтения формате в консоль.
//                //      html:target/cucumber-reports: создаёт HTML-отчёт по результатам тестирования в папке target/cucumber-reports
//                plugin = {"pretty", "html:target/cucumber-reports.html"},
//                tags = "@Login or @InvalidPassword"
//        )

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "ilcarro.stepDefinitions")
@ConfigurationParameter(key = "cucumber.plugin", value = "html:target/cucumber-reports.html, json:target/cucumber-reports.json")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@AddCar")
//    gradle clean test -Dcucumber.filter.tags=@Login
public class TestRunner {
}




