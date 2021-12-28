package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.converter.CsvConverter;
import by.senla.training.chaplinskiy.hotel.converter.SupplyCsvConverter;
import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.exception.CustomRuntimeException;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepository;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvReader;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvWriter;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

public class SupplyServiceImpl implements SupplyService {

    private static SupplyServiceImpl supplyService = null;
    private final SupplyRepository supplyRepository;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;
    private final PropertiesService propertiesService;
    private final CsvConverter<Supply> csvConverter;

    private SupplyServiceImpl() {
        this.supplyRepository = SupplyRepositoryImpl.getSupplyRepository();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.csvReader = CsvReader.getCsvReader();
        this.propertiesService = PropertiesService.getPropertiesService();
        this.csvConverter = SupplyCsvConverter.getSupplyCsvConverter();
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

    public Long addSupply(SupplyType supplyType1, int price) {
        Supply supply = new Supply(supplyType1, price);
        return supplyRepository.addSupply(supply);
    }

    public void update(Long id, int price) {
        Supply supply = new Supply(null, price);
        supply.setId(id);
        supplyRepository.update(supply);
    }

    public void remove(Long id) {

        supplyRepository.remove(id);
    }

    public Supply getById(Long id) throws EntityNotFoundException {
        return supplyRepository.getById(id);
    }

    public void exportFile() {
        List<Supply> supplyList = supplyService.getAll();
        List<String> lines = csvConverter.getStrings(supplyList);
        csvWriter.writeLinesToFile(lines, propertiesService.getValue("supplyResultPath"));
    }

    public void importFromFile() {
        try {
            List<String> lineFromString = csvReader.getLinesFromFile(propertiesService.getValue("supplyPath"));
            List<Supply> supplyList = csvConverter.getFromStrings(lineFromString);
            supplyRepository.addAll(supplyList);
        } catch (FileNotFoundException e) {
            throw new CustomRuntimeException("Не правильный путь к файлу");
        }
    }


}
