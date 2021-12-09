package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Supply;

import java.util.List;
import java.util.Scanner;

public interface SupplyService {

    List<Supply> getSuppliesSortedByPrice();

    List<Supply> getSuppliesSortedByType();

    List<Supply> getAll();

    Long addSupply(Scanner scanner);

    void update(Scanner scanner);

    void remove(Scanner scanner);

    Supply getById(Scanner scanner);

}
