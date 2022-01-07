package by.senla.training.chaplinskiy.hotel.converter;

import java.util.List;

public interface CsvConverter<T> {

    List<T> getFromStrings(List<String> lines);

    List<String> getStrings(List<T> entities);

}
