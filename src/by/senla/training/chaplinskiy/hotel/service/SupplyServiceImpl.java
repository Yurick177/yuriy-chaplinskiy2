package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepository;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepositoryImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SupplyServiceImpl implements SupplyService {

    private static SupplyServiceImpl supplyService = null;
    private final SupplyRepository supplyRepository;
    private final RoomRepository roomRepository;

    private SupplyServiceImpl() {
        this.supplyRepository = SupplyRepositoryImpl.getSupplyRepository();
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
    }

    public static SupplyServiceImpl getSupplyService() {
        if (supplyService == null) {
            supplyService = new SupplyServiceImpl();
        }
        return supplyService;
    }

    public List<Supply> getSupplies() {
        return supplyRepository.getSupplies();
    }

    public List<Supply> getSuppliesSortedByPrice() {
        List<Supply> supplies = supplyRepository.getSupplies();
        Comparator<Supply> comparator = (o1, o2) -> o1.getPrice() > o2.getPrice() ? 1 : -1;
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
        SupplyType supplyType1 = SupplyType.valueOf(supplyType);
        System.out.println(" введите цену ");
        int price = Integer.parseInt(scanner.nextLine());
        Supply supply = new Supply(supplyType1, price);
        Long id = supplyRepository.addSupply(supply);
        return id;
    }

    public void update(Scanner scanner) {
        System.out.println(" введите id услуги ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println(" введите новую цену ");
        int price = Integer.parseInt(scanner.nextLine());
        Supply supply = new Supply(null, price);
        supply.setId(id);
        supplyRepository.update(supply);
    }

    public void remove(Scanner scanner) {
        System.out.println(" введите id услуги, которую нужно удалить");
        Long id = Long.parseLong(scanner.nextLine());
        supplyRepository.remove(id);
    }

    public Supply getById(Scanner scanner) {
        System.out.println(" введите id услуги ");
        Long id = Long.parseLong(scanner.nextLine());
        return supplyRepository.getById(id);
    }

}
