package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;

import java.util.List;

public interface SupplyRepository {

    List<Supply> getSupplies();

    void setSupplies(List<Supply> supplies);

    List<Supply> getAll();

    Long addSupply(Supply supply);

    void update(Supply supply);

    void remove(Long id);

    Supply getById(Long id) throws EntityNotFoundException;

    List<Supply> addAll(List<Supply> supplies);

}
