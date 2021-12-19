package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Supply;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplyRepositoryImpl implements SupplyRepository {

    private List<Supply> supplies;
    private static SupplyRepositoryImpl supplyRepository = null;

    private SupplyRepositoryImpl() {
        this.supplies = new ArrayList<>();
    }

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

    public List<Supply> getAll() {
        return supplies;
    }

    public Long addSupply(Supply supply) {
        long id = supplies.stream().mapToLong(Supply::getId).max().orElse(0);
        supply.setId(id + 1);
        supplies.add(supply);
        return supply.getId();
    }

    public void update(Supply supply) {
        supplies.stream().filter(a -> a.getId().equals(supply.getId())).findFirst().ifPresent(currentSupply -> currentSupply.setPrice(supply.getPrice()));
    }

    public void remove(Long id) {
        List<Supply> newSupply = supplies.stream().filter(a -> !a.getId().equals(id)).collect(Collectors.toList());
        setSupplies(newSupply);
    }

    public Supply getById(Long id) {
        return supplies.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Supply> addAll(List<Supply> supplies) {
        for (Supply supply : supplies) {
            if (supply.getId() == null) {
                addSupply(supply);
            } else {
                update(supply);
            }
        }
        return supplies;
    }

}
