package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.SupplyService;
import by.senla.training.chaplinskiy.hotel.service.SupplyServiceImpl;

import java.util.List;

public class SupplyController {

    private final SupplyService supplyService;
    private static SupplyController supplyController;

    private SupplyController() {
        this.supplyService = SupplyServiceImpl.getSupplyService();
    }

    public static SupplyController getSupplyController() {
        if (supplyController == null) {
            supplyController = new SupplyController();
        }
        return supplyController;
    }

    public List<Supply> getSuppliesSortedByPrice() {
        return supplyService.getSuppliesSortedByPrice();
    }

    public List<Supply> getSuppliesSortedByType() {
        return supplyService.getSuppliesSortedByType();
    }

    public List<Supply> getAll() {
        return supplyService.getAll();
    }

    public String addSupply(SupplyType supplyType, int price) {
        return String.valueOf(supplyService.addSupply(supplyType, price));
    }

    public String update(Long id, int price) {
        supplyService.update(id, price);
        return "обновлено";
    }

    public void remove(Long id) {
        supplyService.remove(id);
    }

    public Supply getById(Long id) {
        try {
            return supplyService.getById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void exportFile() {
        supplyService.exportFile();
    }

    public void importFromFile() {
        supplyService.importFromFile();
    }

}
