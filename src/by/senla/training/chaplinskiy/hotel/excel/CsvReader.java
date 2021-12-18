package by.senla.training.chaplinskiy.hotel.excel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    private static CsvReader csvReader;
    private FileReader fileReader;

    private CsvReader() {
    }

    public static CsvReader getCsvReader() {
        if (csvReader == null) {
            csvReader = new CsvReader();
        }
        return csvReader;
    }

    public List<String> getLinesFromFile(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        Scanner scanner = new Scanner(fileReader);
        List<String> lineFile = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lineFile.add(scanner.nextLine());
        }
        return lineFile;
    }

}
