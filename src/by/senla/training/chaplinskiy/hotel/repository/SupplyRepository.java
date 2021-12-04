package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Supply;

import java.util.List;

public interface SupplyRepository {

    List<Supply> getSupplies();

    void setSupplies(List<Supply> supplies);

}
