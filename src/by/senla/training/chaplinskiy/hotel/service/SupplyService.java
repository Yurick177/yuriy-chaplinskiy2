package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Supply;

import java.util.List;

public interface SupplyService {

    List<Supply> getSupplies();

    List<Supply> getSuppliesSortedByPrice();

    List<Supply> getSuppliesSortedByType();

    void addSupply(Supply supply);

    void removeService(Supply service);

    void addSupplyToRoom(Room room, Supply supply);

}
