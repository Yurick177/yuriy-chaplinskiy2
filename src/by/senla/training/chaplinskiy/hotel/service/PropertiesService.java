package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.stringreader.CsvReader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesService {

    private final Map<String, String> properties;
    private static PropertiesService propertiesService;

    private PropertiesService() {
        CsvReader csvReader = CsvReader.getCsvReader();
        properties = new HashMap<>();
        try {
            List<String> linesFromFile = csvReader.getLinesFromFile("resources/hotel.properties");
            for (String line : linesFromFile) {
                if (line != null && line.length() > 0) {
                    String[] split = line.split("=");
                    properties.put(split[0], split[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" файл hotel.properties не найден");
        }
    }

    public static PropertiesService getPropertiesService() {
        if (propertiesService == null) {
            propertiesService = new PropertiesService();
        }
        return propertiesService;
    }

    public String getValue(String key) {
        return properties.get(key);
    }

}
