package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;

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

    public Room getRoomById(Long id) throws EntityNotFoundException {
        return rooms.stream().filter(a -> a.getId().equals(id)).findFirst().orElseThrow(() -> new EntityNotFoundException("Комната по такому id не найдена"));
    }

    public List<Room> addAll(List<Room> rooms) {
        for (Room room : rooms) {
            if (room.getId() == null) {
                addRoom(room);
            } else {
                update(room);
            }
        }
        return rooms;
    }

    public void update(Room room) {
        try {
            Room current = getRoomById(room.getId());
            current.setStatus(room.getStatus());
            current.setPrice(room.getPrice());
            current.setId(room.getId());
            current.setStar(room.getStar());
            current.setCapacityRoom(room.getCapacityRoom());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Long addRoom(Room room) {
        long id = rooms.stream().mapToLong(Room::getId).max().orElse(0);
        room.setId(id + 1);
        rooms.add(room);
        return room.getId();
    }

}
