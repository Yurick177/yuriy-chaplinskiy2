package by.senla.training.chaplinskiy.hotel.utils;

import by.senla.training.chaplinskiy.hotel.exception.LocalDateTimeFromStringException;

import java.time.LocalDateTime;
import java.util.Scanner;

import static by.senla.training.chaplinskiy.hotel.utils.LocalDateTimeUtils.getLocalDateTimeFromString;

public class ScannerUtils {

    public static Long getLongFromConsole(Scanner scanner) {
        Long result = null;
        try {
            result = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Ошибка !!! вы ввели не тот символ");
            getLongFromConsole(scanner);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("Ошибка !!! вы ввели цифру, которой не существует");
            getLongFromConsole(scanner);
        }
        return result;
    }

    public static LocalDateTime getDate(Scanner scanner, String s) {
        System.out.println(s);
        String date = scanner.nextLine();
        try {
            return getLocalDateTimeFromString(date);
        } catch (LocalDateTimeFromStringException e) {
            System.out.println(e.getMessage());
            return getDate(scanner, s);
        }
    }

}
