package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;

import java.util.List;

public interface SupplyService {

    List<Supply> getSuppliesSortedByPrice();

    List<Supply> getSuppliesSortedByType();

    List<Supply> getAll();

    Long addSupply(SupplyType supplyType1, int price);

    void update(Long id, int price);

    void remove(Long id);

    Supply getById(Long id) throws EntityNotFoundException;

    void exportFile();

    void importFromFile();

}
