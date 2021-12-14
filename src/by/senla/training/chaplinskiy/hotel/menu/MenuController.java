package by.senla.training.chaplinskiy.hotel.menu;

import java.util.Scanner;

public class MenuController {

    private Builder builder;
    private Navigator navigator;

    public void run() {
        builder = new Builder();
        builder.buildMenu();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите пункт действия:");
            navigator = new Navigator();
            navigator.setCurrentMenu(builder.getRootMenu());
            navigator.printMenu();
            navigate(scan);

        }
    }

    private void navigate(Scanner scan) {
        System.out.println("Ведите нужную цифру");
        String b = scan.nextLine();
        try {
            int index = Integer.parseInt(b);
            navigator.navigate(index);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Вы ввели не тот символ");
            navigate(scan);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("вы ввели цифру, которой не существует");
            navigate(scan);
        }
    }

}
