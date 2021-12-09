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
            System.out.println("Ведите нужную цифру");
            String b = scan.nextLine();
            navigator.navigate(Integer.valueOf(b));
        }
    }
}
