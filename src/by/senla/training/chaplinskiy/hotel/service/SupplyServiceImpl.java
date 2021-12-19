package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.excel.CsvReader;
import by.senla.training.chaplinskiy.hotel.excel.CsvWriter;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepository;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class SupplyServiceImpl implements SupplyService {

    private static SupplyServiceImpl supplyService = null;
    private final SupplyRepository supplyRepository;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    private SupplyServiceImpl() {
        this.supplyRepository = SupplyRepositoryImpl.getSupplyRepository();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.csvReader = CsvReader.getCsvReader();
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

    public void exportFile() {
        List<Supply> supplyList = supplyService.getAll();
        List<String> lines = new ArrayList<>();
        lines.add("id,serviceType,price");
        for (Supply supply : supplyList) {
            String line = supply.getId() + "," + supply.getServiceType() + "," + supply.getPrice();
            lines.add(line);
        }
        csvWriter.writeLinesToFile(lines, "C:\\Users\\Ura\\IdeaProjects\\yuriy-chaplinskiy1\\resources\\Supply_Result.csv");
    }

    public void importFromFile() {
        try {
            List<String> lineFromString = csvReader.getLinesFromFile("C:\\Users\\Ura\\IdeaProjects\\yuriy-chaplinskiy1\\resources\\Supply.csv");
            List<Supply> supplyList = getSuppliesFromStrings(lineFromString);
            supplyRepository.addAll(supplyList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не правильный путь к файлу");
        }
    }

    private List<Supply> getSuppliesFromStrings(List<String> lines) {
        List<Supply> supplyList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Supply supply = getSupplyFromString(lines.get(i));
            supplyList.add(supply);
        }
        return supplyList;
    }

    private Supply getSupplyFromString(String line) {
        String[] split = line.split(",");
        Long id = Objects.equals(split[0], "") ? null : Long.parseLong(split[0]);
        SupplyType serviceType = SupplyType.valueOf(split[1]);
        int price = Integer.parseInt(split[2]);
        Supply supply = new Supply(serviceType,price);
        supply.setId(id);
        return supply;
    }

}
