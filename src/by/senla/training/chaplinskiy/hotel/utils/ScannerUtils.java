package by.senla.training.chaplinskiy.hotel.utils;

import java.util.Scanner;

public class ScannerUtils {

    public static Long getLongFromConsole(Scanner scanner) {
        Long result = null;
        try {
            result = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Вы ввели не тот символ");
            getLongFromConsole(scanner);
        }
        return result;
    }

}
