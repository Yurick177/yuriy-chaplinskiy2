package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepository;
import by.senla.training.chaplinskiy.hotel.repository.SupplyRepositoryImpl;

import java.util.Comparator;
import java.util.List;

public class SupplyServiceImpl implements SupplyService {

    private static SupplyServiceImpl supplyService = null;
    private SupplyRepository supplyRepository;
    private RoomRepository roomRepository;

    private SupplyServiceImpl() {
        this.supplyRepository = SupplyRepositoryImpl.getSupplyRepository();
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
    }

    public static SupplyServiceImpl getSupplyService() {
        if (supplyService == null) {
            supplyService = new SupplyServiceImpl();
        }
        return supplyService;
    }

    public List<Supply> getSupplies() {
        List<Supply> supplies = supplyRepository.getSupplies();
        return supplies;
    }

    public List<Supply> getSuppliesSortedByPrice() {
        List<Supply> supplies = supplyRepository.getSupplies();
        Comparator<Supply> comparator = new Comparator<Supply>() {
            @Override
            public int compare(Supply o1, Supply o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }

        };
        supplies.sort(comparator);
        return supplies;
    }

    public List<Supply> getSuppliesSortedByType() {
        List<Supply> supplies = supplyRepository.getSupplies();
        Comparator<Supply> comparator = new Comparator<Supply>() {
            @Override
            public int compare(Supply o1, Supply o2) {
                return o1.getServiceType().name().compareTo(o2.getServiceType().name());
            }

        };
        supplies.sort(comparator);
        return supplies;
    }

    public void addSupply(Supply supply) {
        List<Supply> supplies = supplyRepository.getSupplies();
        supplies.add(supply);
    }

    public void removeService(Supply service) {
        List<Supply> supplies = supplyRepository.getSupplies();
        List<Room> rooms = roomRepository.getRooms();
        supplies.remove(service);
        for (Room room : rooms) {
            room.getServices().remove(service);
        }
    }

    public void addSupplyToRoom(Room room, Supply supply) {
        room.addService(supply);
    }

}
