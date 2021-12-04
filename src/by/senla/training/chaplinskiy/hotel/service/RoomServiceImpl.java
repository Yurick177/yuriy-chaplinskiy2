package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.repository.PersonHistoryRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonHistoryRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static by.senla.training.chaplinskiy.hotel.entity.Status.OCСUPIED;

public class RoomServiceImpl implements RoomService {

    private static RoomService roomService = null;
    private PersonHistoryRepository personHistoryRepository;
    private RoomRepository roomRepository;

    private RoomServiceImpl() {
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.personHistoryRepository = PersonHistoryRepositoryImpl.getPersonHistoryRepository();
    }

    public static RoomService getRoomService() {
        if (roomService == null) {
            roomService = new RoomServiceImpl();
        }
        return roomService;
    }

    public Room createRoom(Status status, int price, long id, int star, int capacityRoom) {
        Room room = new Room(status, price, id, star, capacityRoom);
        roomRepository.getRooms().add(room);
        return room;
    }

    public List<Room> getRooms() {
        return roomRepository.getRooms();
    }

    public List<Room> getRoomsByPriceAsc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByPriceDesc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() < o2.getPrice() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByStarAsc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() > o2.getStar() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByStarDesc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() < o2.getStar() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByCapacityRoomAsc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() > o2.getCapacityRoom() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;

    }

    public List<Room> getRoomsByCapacityRoomDesc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() < o2.getCapacityRoom() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        List<Room> rooms = roomRepository.getRooms();
        List<Room> availableRooms = new ArrayList<>();
        for (Room i : rooms) {
            if (i.getStatus().equals(Status.AVAILABLE)) {
                availableRooms.add(i);
            }
        }
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByPriceAsc() {

        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByPriceDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() < o2.getPrice() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCapacityAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() > o2.getCapacityRoom() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCapacityDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() < o2.getCapacityRoom() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByStarAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() > o2.getStar() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByStarDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() < o2.getStar() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getOccupiedRooms() {
        List<Room> rooms = roomRepository.getRooms();
        List<Room> availableRooms = new ArrayList<>();
        for (Room i : rooms) {
            if (i.getStatus().equals(OCСUPIED)) {
                availableRooms.add(i);
            }
        }
        return availableRooms;
    }

    public List<Room> getOccupiedRoomsSortByDateDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getReleaseDate().isBefore(o2.getReleaseDate()) ? 1 : -1;
            }
        };
        List<Room> occupiedRooms = getOccupiedRooms();
        occupiedRooms.sort(comparator);
        return occupiedRooms;
    }

    public List<Room> getOccupiedRoomsSortByDateAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getReleaseDate().isAfter(o2.getReleaseDate()) ? 1 : -1;
            }
        };
        List<Room> occupiedRooms = getOccupiedRooms();
        occupiedRooms.sort(comparator);
        return occupiedRooms;
    }

    public int getFreeNumbers() {
        List<Room> freeNumbers = getAvailableRooms();
        return freeNumbers.size();
    }

    public List<Room> getAvailableRoomsByDate(LocalDateTime localDateTime) {
        List<Room> rooms = roomRepository.getRooms();
        List<Room> result = new ArrayList<>();
        for (Room i : rooms) {
            if (i.getReleaseDate() == null || i.getReleaseDate().isBefore(localDateTime)) {
                result.add(i);
            }
        }
        return result;
    }

    public void addPerson(Room room, Person person, LocalDateTime checkInDate, LocalDateTime releaseDate) {
        room.setPerson(person);
        room.setStatus(OCСUPIED);
        room.setReleaseDate(releaseDate);
        room.setCheckInDate(checkInDate);
        PersonHistory personHistory = new PersonHistory(person, releaseDate, checkInDate);
        personHistoryRepository.addPersonHistory(personHistory);
        room.getPersonHistories().add(personHistory);

    }

}