package by.senla.training.chaplinskiy.hotel.menu;

import java.util.List;

public class Navigator {

    private Menu currentMenu;

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

    public void navigate(Integer index) {
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        MenuItem menuItem = menuItems.get(index - 1);
        System.out.println("Вы выбрали " + menuItem.getTitle());
        menuItem.getAction().execute();
    }

}
