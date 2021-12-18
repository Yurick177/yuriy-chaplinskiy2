package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> getRooms();

    Room getRoomById(Long id);

    Long addRoom(Room room);

    void update(Room room);

    List<Room> addAll(List<Room> rooms);

}
