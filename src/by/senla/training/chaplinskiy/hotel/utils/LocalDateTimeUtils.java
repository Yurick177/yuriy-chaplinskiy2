package by.senla.training.chaplinskiy.hotel.utils;

import by.senla.training.chaplinskiy.hotel.exception.LocalDateTimeFromStringException;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {

    public static LocalDateTime getLocalDateTimeFromString(String date) throws LocalDateTimeFromStringException {
        String[] split = date.split("\\.");
        if (split.length != 5) {
            throw new LocalDateTimeFromStringException("Ошибка!!! правильный формат 'yyyy.MM.dd.HH.mm' ");
        }
        int year;
        int month;
        int day;
        int hour;
        int minute;
        try {
            year = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            day = Integer.parseInt(split[2]);
            hour = Integer.parseInt(split[3]);
            minute = Integer.parseInt(split[4]);
        } catch (NumberFormatException e) {
            throw new LocalDateTimeFromStringException("Ошибка!!! неправильный формат, вводите цифры");
        }
        if (year > 9999) {
            throw new LocalDateTimeFromStringException("Ошибка!!! год не может быть больше чем 9999");
        }
        if (month > 12) {
            throw new LocalDateTimeFromStringException("Ошибка!!! месяцев должно быть не больше, чем 12");
        }
        if (day > 31) {
            throw new LocalDateTimeFromStringException("Ошибка!!! дней должно быть не больше 31");
        }
        if (hour > 24) {
            throw new LocalDateTimeFromStringException("Ошибка!!! часы не должны превышать 24");
        }
        if (minute > 60) {
            throw new LocalDateTimeFromStringException("Ошибка!!! минуты не должны превышать 60");
        }
        return LocalDateTime.of(year, month, day, hour, minute);
    }

}
