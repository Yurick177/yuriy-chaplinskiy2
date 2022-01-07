package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.RoomService;
import by.senla.training.chaplinskiy.hotel.service.RoomServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class RoomController {

    private final RoomService roomService;
    private static RoomController roomController;

    private RoomController() {
        this.roomService = RoomServiceImpl.getRoomService();
    }

    public static RoomController getRoomController() {
        if (roomController == null) {
            roomController = new RoomController();
        }
        return roomController;
    }

    public void createRoom(Status status, int price, Long id, int star, int capacityRoom) {
        roomService.createRoom(status, price, id, star, capacityRoom);
    }

    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    public List<Room> getRoomsByPriceAsc() {
        return roomService.getRoomsByPriceAsc();
    }

    public List<Room> getRoomsByPriceDesc() {
        return roomService.getRoomsByPriceDesc();
    }

    public List<Room> getRoomsByStarAsc() {
        return roomService.getRoomsByStarAsc();
    }

    public List<Room> getRoomsByStarDesc() {
        return roomService.getRoomsByStarDesc();
    }

    public List<Room> getRoomsByCapacityRoomAsc() {
        return roomService.getRoomsByCapacityRoomAsc();
    }

    public List<Room> getRoomsByCapacityRoomDesc() {
        return roomService.getRoomsByCapacityRoomDesc();
    }

    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    public List<Room> getAvailableRoomsByPriceAsc() {
        return roomService.getAvailableRoomsByPriceAsc();
    }

    public List<Room> getAvailableRoomsByPriceDesc() {
        return roomService.getAvailableRoomsByPriceDesc();
    }

    public List<Room> getAvailableRoomsByCapacityAsc() {
        return roomService.getAvailableRoomsByCapacityAsc();
    }

    public List<Room> getAvailableRoomsByCapacityDesc() {
        return roomService.getAvailableRoomsByCapacityDesc();
    }

    public List<Room> getAvailableRoomsByStarAsc() {
        return roomService.getAvailableRoomsByStarAsc();
    }

    public List<Room> getAvailableRoomsByStarDesc() {
        return roomService.getAvailableRoomsByStarDesc();
    }

    public List<Room> getOccupiedRooms() {
        return roomService.getOccupiedRooms();
    }

    public List<Room> getOccupiedRoomsSortByDateDesc() {
        return roomService.getOccupiedRoomsSortByDateDesc();
    }

    public List<Room> getOccupiedRoomsSortByDateAsc() {
        return roomService.getOccupiedRoomsSortByDateAsc();
    }

    public int getFreeNumbers() {
        return roomService.getFreeNumbers();
    }

    public List<Room> getAvailableRoomsByDate(LocalDateTime localDateTime) {
        return roomService.getAvailableRoomsByDate(localDateTime);
    }

    public void exportFile() {
        roomService.exportFile();
    }

    public void importFromFile() {
        roomService.importFromFile();
    }

    public String changeStatus(Long id, Status status) {
        try {
            roomService.changeStatus(id, status);
        } catch (EntityNotFoundException e) {
            return e.getMessage();
        }
        return "Статус изменен";
    }

}
