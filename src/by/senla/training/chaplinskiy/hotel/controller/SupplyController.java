package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.SupplyService;
import by.senla.training.chaplinskiy.hotel.service.SupplyServiceImpl;

import java.util.List;

public class SupplyController {

    private final SupplyService supplyService;
    private static SupplyController supplyController;

    private SupplyController() {
        this.supplyService = SupplyServiceImpl.getSupplyService();
    }

    public static SupplyController getSupplyController() {
        if (supplyController == null) {
            supplyController = new SupplyController();
        }
        return supplyController;
    }

    public List<Supply> getSuppliesSortedByPrice() {
        return supplyService.getSuppliesSortedByPrice();
    }

    public List<Supply> getSuppliesSortedByType() {
        return supplyService.getSuppliesSortedByType();
    }

    public List<Supply> getAll() {
        return supplyService.getAll();
    }

    public String addSupply(String supplyType, String priceString) {
        SupplyType supplyType1;
        try {
            supplyType1 = SupplyType.valueOf(supplyType);
        } catch (IllegalArgumentException e) {
            return "Ошибка!!! вводите тип, как указанно";
        }
        int price;
        try {
            price = Integer.parseInt(priceString);
        } catch (NumberFormatException e) {
            return "Ошибка!!! вы ввели цену не правильно, вводите только цифры";
        }
        return String.valueOf(supplyService.addSupply(supplyType1, price));
    }

    public String update(Long id, String priceString) {
        int price;
        try {
            price = Integer.parseInt(priceString);
        } catch (NumberFormatException r) {
            return "Ошибка!!! вводите только цифры";
        }
        supplyService.update(id, price);
        return "обновлено";
    }

    public void remove(Long id) {
        supplyService.remove(id);
    }

    public Supply getById(Long id) {
        try {
            return supplyService.getById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void exportFile() {
        supplyService.exportFile();
    }

    public void importFromFile() {
        supplyService.importFromFile();
    }

}
