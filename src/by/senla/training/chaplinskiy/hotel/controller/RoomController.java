package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.RoomService;
import by.senla.training.chaplinskiy.hotel.service.RoomServiceImpl;
import by.senla.training.chaplinskiy.hotel.utils.ScannerUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static by.senla.training.chaplinskiy.hotel.utils.ScannerUtils.getDate;

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

    public Room createRoom(Scanner scanner) {
        System.out.println("Введите статус комнаты");
        for (Status status : Status.values()) {
            System.out.println(status.name());
        }
        String statusString = scanner.nextLine();
        Status status = Status.valueOf(statusString);
        System.out.println("введите цену");
        String price = scanner.nextLine();
        System.out.println("введите номер комнаты");
        String id = scanner.nextLine();
        System.out.println("введите звезду");
        String star = scanner.nextLine();
        System.out.println("введите вместимость");
        String capacityRoom = scanner.nextLine();
        return roomService.createRoom(status, Integer.parseInt(price), Long.parseLong(id), Integer.parseInt(star), Integer.parseInt(capacityRoom));
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

    public List<Room> getAvailableRoomsByDate(Scanner scanner) {
        LocalDateTime localDateTime = getDate(scanner, "введите год.месяц.день.часы.минуты заселения");
        return roomService.getAvailableRoomsByDate(localDateTime);
    }

    public void exportFile() {
        roomService.exportFile();
    }

    public void importFromFile() {
        roomService.importFromFile();
    }

    public void changeStatus(Scanner scanner) {
        System.out.println("введите id комнаты ");
        Long id = ScannerUtils.getLongFromConsole(scanner);
        System.out.println("введите статус");
        Status status = Status.valueOf(scanner.nextLine());
        try {
            roomService.changeStatus(id, status);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            changeStatus(scanner);
        }
    }

}
