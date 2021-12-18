package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepository;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.utils.ScannerUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SupplyServiceImpl implements SupplyService {

    private static SupplyServiceImpl supplyService = null;
    private final SupplyRepository supplyRepository;

    private SupplyServiceImpl() {
        this.supplyRepository = SupplyRepositoryImpl.getSupplyRepository();

    }

    public static SupplyServiceImpl getSupplyService() {
        if (supplyService == null) {
            supplyService = new SupplyServiceImpl();
        }
        return supplyService;
    }

    public List<Supply> getSuppliesSortedByPrice() {
        List<Supply> supplies = supplyRepository.getSupplies();
        Comparator<Supply> comparator = Comparator.comparing(Supply::getPrice);
        supplies.sort(comparator);
        return supplies;
    }

    public List<Supply> getSuppliesSortedByType() {
        List<Supply> supplies = supplyRepository.getSupplies();
        Comparator<Supply> comparator = Comparator.comparing(o -> o.getServiceType().name());
        supplies.sort(comparator);
        return supplies;
    }

    public List<Supply> getAll() {
        return supplyRepository.getAll();
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
        Supply supply = new Supply(supplyType1, price);
        return supplyRepository.addSupply(supply);
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
        Supply supply = new Supply(null, price);
        supply.setId(id);
        supplyRepository.update(supply);
    }

    public void remove(Scanner scanner) {
        System.out.println(" введите id услуги, которую нужно удалить");
        Long id = ScannerUtils.getLongFromConsole(scanner);
        supplyRepository.remove(id);
    }

    public Supply getById(Scanner scanner) {
        System.out.println(" введите id услуги ");
        Long id = ScannerUtils.getLongFromConsole(scanner);
        return supplyRepository.getById(id);
    }

}
