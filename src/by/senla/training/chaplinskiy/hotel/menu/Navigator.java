package by.senla.training.chaplinskiy.hotel.menu;

import java.util.List;
import java.util.Scanner;

public class Navigator {

    private static Navigator navigator;
    private Menu currentMenu;

    private Navigator() {
    }

    public static Navigator getNavigator() {
        if (navigator == null) {
            navigator = new Navigator();
        }
        return navigator;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        for (MenuItem i : menuItems) {
            System.out.println(i.getTitle());
        }
    }

    public void navigate(Scanner scan) {
        System.out.println("Ведите нужную цифру");
        String b = scan.nextLine();
        try {
            int index = Integer.parseInt(b);
            List<MenuItem> menuItems = currentMenu.getMenuItems();
            MenuItem menuItem = menuItems.get(index - 1);
            System.out.println("Вы выбрали " + menuItem.getTitle());
            menuItem.getAction().execute();
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Вы ввели не тот символ");
            navigate(scan);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("вы ввели цифру, которой не существует");
            navigate(scan);
        }
    }

}
