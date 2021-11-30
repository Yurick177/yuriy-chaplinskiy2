package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Supply;

import java.util.ArrayList;
import java.util.List;

public class SupplyRepositoryImpl implements SupplyRepository {

    private List<Supply> supplies;
    private static SupplyRepositoryImpl supplyRepository = null;

    public List<Supply> getSupplies() {
        if (supplies == null) {
            supplies = new ArrayList<>();
        }
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public static SupplyRepositoryImpl getSupplyRepository() {
        if (supplyRepository == null) {
            supplyRepository = new SupplyRepositoryImpl();
        }
        return supplyRepository;
    }

}
