package ilcarro.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
    public static List<String[]> loadCsv(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build()) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Ошибка загрузки CSV-файла: " + filePath, e);
        }
    }
}
