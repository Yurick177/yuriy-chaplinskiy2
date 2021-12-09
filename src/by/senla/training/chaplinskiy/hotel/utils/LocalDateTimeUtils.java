package by.senla.training.chaplinskiy.hotel.utils;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {

    public static LocalDateTime getLocalDateTimeFromString(String date) {
        String[] split = date.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        int hour = Integer.parseInt(split[3]);
        int minute = Integer.parseInt(split[4]);
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
