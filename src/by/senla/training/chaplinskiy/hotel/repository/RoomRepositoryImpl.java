package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private final List<Room> rooms;
    private static RoomRepositoryImpl roomRepository = null;

    private RoomRepositoryImpl() {
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public static RoomRepositoryImpl getRoomRepository() {
        if (roomRepository == null) {
            roomRepository = new RoomRepositoryImpl();
        }
        return roomRepository;
    }

    public Room getRoomById(Long id) {
        return rooms.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

}
