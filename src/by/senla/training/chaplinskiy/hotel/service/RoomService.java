package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {

    void addPerson(Room room, Person person, LocalDateTime checkInDate, LocalDateTime releaseDate);

    Room createRoom(Status status, int price, long id, int star, int capacityRoom);

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

    List<Room> getAvailableRoomsByDate(LocalDateTime localDateTime);

}
