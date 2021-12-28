package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.SupplyService;
import by.senla.training.chaplinskiy.hotel.service.SupplyServiceImpl;
import by.senla.training.chaplinskiy.hotel.utils.ScannerUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public Long addSupply(Scanner scanner) {
        System.out.println(" введите тип услуги " + Arrays.toString(SupplyType.values()));
        String supplyType = scanner.nextLine();
        SupplyType supplyType1 = null;
        try {
            supplyType1 = SupplyType.valueOf(supplyType);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка!!! вводите тип, как указанно");
            addSupply(scanner);
        }
        System.out.println(" введите цену ");
        int price = 0;
        try {
            price = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка!!! вы ввели цену не правильно, вводите только цифры");
            addSupply(scanner);
        }
        return supplyService.addSupply(supplyType1, price);
    }

    public void update(Scanner scanner) {
        System.out.println(" введите id услуги ");
        Long id = ScannerUtils.getLongFromConsole(scanner);
        System.out.println(" введите новую цену ");
        int price = 0;
        try {
            price = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException r) {
            System.out.println("Ошибка!!! вводите только цифры");
            update(scanner);
        }
        supplyService.update(id, price);
    }

    public void remove(Scanner scanner) {
        System.out.println(" введите id услуги, которую нужно удалить");
        Long id = ScannerUtils.getLongFromConsole(scanner);
        supplyService.remove(id);
    }

    public Supply getById(Scanner scanner) {
        System.out.println(" введите id услуги ");
        Long id = ScannerUtils.getLongFromConsole(scanner);
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
