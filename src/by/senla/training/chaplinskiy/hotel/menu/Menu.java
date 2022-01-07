package by.senla.training.chaplinskiy.hotel.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

}
