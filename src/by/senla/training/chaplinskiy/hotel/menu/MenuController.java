package by.senla.training.chaplinskiy.hotel.menu;

import java.util.Scanner;

public class MenuController {

    private static MenuController menuController;
    private final Builder builder;
    private final Navigator navigator;

    private MenuController() {
        builder = Builder.getBuilder();
        navigator = Navigator.getNavigator();
    }

    public static MenuController getMenuController() {
        if (menuController == null) {
            menuController = new MenuController();
        }
        return menuController;
    }

    public void run() {
        builder.buildMenu();
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (builder.getRootMenu() != null) {
                System.out.println("Выберите пункт действия:");
                navigator.setCurrentMenu(builder.getRootMenu());
                navigator.printMenu();
                navigator.navigate(scan);
            } else {
                break;
            }
        }
    }

}
