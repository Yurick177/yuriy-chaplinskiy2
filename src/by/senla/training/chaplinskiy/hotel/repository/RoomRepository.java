package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> getRooms();

    void setRooms(List<Room> rooms);

}
