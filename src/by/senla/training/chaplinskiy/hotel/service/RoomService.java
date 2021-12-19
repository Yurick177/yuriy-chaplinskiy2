package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public interface RoomService {

    void addPerson(Room room, Person person, LocalDateTime checkInDate, LocalDateTime releaseDate);

    Room createRoom(Scanner scanner);

    List<Room> getRooms();

    List<Room> getRoomsByPriceAsc();

    List<Room> getRoomsByPriceDesc();

    List<Room> getRoomsByStarAsc();

    List<Room> getRoomsByStarDesc();

    List<Room> getRoomsByCapacityRoomAsc();

    List<Room> getRoomsByCapacityRoomDesc();

    List<Room> getAvailableRooms();

    List<Room> getAvailableRoomsByPriceAsc();

    List<Room> getAvailableRoomsByPriceDesc();

    List<Room> getAvailableRoomsByCapacityAsc();

    List<Room> getAvailableRoomsByCapacityDesc();

    List<Room> getAvailableRoomsByStarAsc();

    List<Room> getAvailableRoomsByStarDesc();

    List<Room> getOccupiedRooms();

    List<Room> getOccupiedRoomsSortByDateDesc();

    List<Room> getOccupiedRoomsSortByDateAsc();

    int getFreeNumbers();

    List<Room> getAvailableRoomsByDate(Scanner scanner);

    void removePerson(Room room);

    void exportFile();

    void importFromFile();

}
