package by.senla.training.chaplinskiy.hotel.stringreader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    private static CsvWriter csvWriter;

    private CsvWriter() {
    }

    public static CsvWriter getCsvWriter() {
        if (csvWriter == null) {
            csvWriter = new CsvWriter();
        }
        return csvWriter;
    }

    public void writeLinesToFile(List<String> lines, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < lines.size(); i++) {
                fileWriter.write(lines.get(i));
                if (i != lines.size() - 1) {
                    fileWriter.write(System.lineSeparator());
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(" Не удалось найти путь");
        }
    }

}
