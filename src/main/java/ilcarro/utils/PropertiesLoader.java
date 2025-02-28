package ilcarro.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertiesLoader.class.getResourceAsStream("/selenide.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props.getProperty(name, "");
    }
}
