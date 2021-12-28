package by.senla.training.chaplinskiy.hotel.converter;

import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryCsvConverter implements CsvConverter<PersonHistory> {

    private static PersonHistoryCsvConverter personHistoryCsvConverter;

    private PersonHistoryCsvConverter() {
    }

    public static PersonHistoryCsvConverter getPersonHistoryCsvConverter() {
        if (personHistoryCsvConverter == null) {
            personHistoryCsvConverter = new PersonHistoryCsvConverter();
        }
        return personHistoryCsvConverter;
    }

    @Override
    public List<PersonHistory> getFromStrings(List<String> lines) {
        return null;
    }

    @Override
    public List<String> getStrings(List<PersonHistory> personHistoryList) {
        List<String> lines = new ArrayList<>();
        lines.add("id,personId,releaseDate,checkInDate,roomId");
        for (PersonHistory personHistory : personHistoryList) {
            String line = personHistory.getId() + "," + personHistory.getPersonId() + "," + personHistory.getReleaseDate() + "," + personHistory.getCheckInDate() + "," + personHistory.getRoomId();
            lines.add(line);
        }
        return lines;
    }

}
