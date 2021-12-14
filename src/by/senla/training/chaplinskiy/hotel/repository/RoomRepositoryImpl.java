package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.util.ArrayList;
import java.util.List;

import static by.senla.training.chaplinskiy.hotel.entity.Status.AVAILABLE;

public class RoomRepositoryImpl implements RoomRepository {

    private List<Room> rooms;
    private static RoomRepositoryImpl roomRepository = null;

    public List<Room> getRooms() {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public static RoomRepositoryImpl getRoomRepository() {
        if (roomRepository == null) {
            roomRepository = new RoomRepositoryImpl();
        }
        return roomRepository;
    }

    public void removePerson(Room room) {
        room.setPerson(null);
        room.setStatus(AVAILABLE);
    }

    public Room getRoomById(Long id) {
        return rooms.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

}
