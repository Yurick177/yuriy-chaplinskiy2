package by.senla.training.chaplinskiy.hotel.service;

import annotationconfig.ConfigProperty;
import annotationconfig.ConfigPropertyProcessor;
import by.senla.training.chaplinskiy.hotel.converter.CsvConverter;
import by.senla.training.chaplinskiy.hotel.converter.RoomCsvConverter;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.exception.CustomRuntimeException;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvReader;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvWriter;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static by.senla.training.chaplinskiy.hotel.entity.Status.AVAILABLE;
import static by.senla.training.chaplinskiy.hotel.entity.Status.OCСUPIED;

public class RoomServiceImpl implements RoomService {

    private static RoomService roomService = null;
    private final PersonHistoryService personHistoryService;
    private final RoomRepository roomRepository;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;
    private final CsvConverter<Room> csvConverter;
    @ConfigProperty(key = "roomResultPath")
    private String roomResultPath;
    @ConfigProperty(key = "roomPath")
    private String roomPath;

    private RoomServiceImpl() {
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.personHistoryService = PersonHistoryServiceImpl.getPersonHistoryService();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.csvReader = CsvReader.getCsvReader();
        this.csvConverter = RoomCsvConverter.getRoomCsvConverter();
    }

    public static RoomService getRoomService() {
        if (roomService == null) {
            roomService = new RoomServiceImpl();
            ConfigPropertyProcessor.getConfigPropertyProcessor().processAnnotation(roomService);
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
        Comparator<Room> comparator = Comparator.comparing(Room::getPrice);
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByPriceDesc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = Comparator.comparing(Room::getPrice).reversed();
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByStarAsc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = Comparator.comparing(Room::getStar);
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByStarDesc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = Comparator.comparing(Room::getStar).reversed();
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByCapacityRoomAsc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = Comparator.comparing(Room::getCapacityRoom);
        rooms.sort(comparator);
        return rooms;

    }

    public List<Room> getRoomsByCapacityRoomDesc() {
        List<Room> rooms = roomRepository.getRooms();
        Comparator<Room> comparator = Comparator.comparing(Room::getCapacityRoom).reversed();
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

        Comparator<Room> comparator = Comparator.comparing(Room::getPrice);
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByPriceDesc() {
        Comparator<Room> comparator = Comparator.comparing(Room::getPrice).reversed();
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCapacityAsc() {
        Comparator<Room> comparator = Comparator.comparing(Room::getCapacityRoom);
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCapacityDesc() {
        Comparator<Room> comparator = Comparator.comparing(Room::getCapacityRoom).reversed();
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByStarAsc() {
        Comparator<Room> comparator = Comparator.comparing(Room::getStar);
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByStarDesc() {
        Comparator<Room> comparator = Comparator.comparing(Room::getStar).reversed();
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
        Comparator<Room> comparator = Comparator.comparing(Room::getReleaseDate);
        List<Room> occupiedRooms = getOccupiedRooms();
        occupiedRooms.sort(comparator);
        return occupiedRooms;
    }

    public List<Room> getOccupiedRoomsSortByDateAsc() {
        Comparator<Room> comparator = (o1, o2) -> o1.getReleaseDate().compareTo(o2.getReleaseDate()) * -1;
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
        PersonHistory personHistory = new PersonHistory(person.getId(), releaseDate, checkInDate, room.getId());
        personHistoryService.addPersonHistory(personHistory);
        room.getPersonHistories().add(personHistory);

    }

    @Override
    public void removePerson(Room room) {
        room.setPerson(null);
        room.setStatus(AVAILABLE);
    }

    public void exportFile() {
        List<Room> roomList = roomService.getRooms();
        List<String> lines = csvConverter.getStrings(roomList);
        csvWriter.writeLinesToFile(lines, roomResultPath);
    }

    public void importFromFile() {
        try {
            List<String> lineFromString = csvReader.getLinesFromFile(roomPath);
            List<Room> roomList = csvConverter.getFromStrings(lineFromString);
            roomRepository.addAll(roomList);
        } catch (FileNotFoundException e) {
            throw new CustomRuntimeException("Не правильный путь к файлу");
        }
    }

    public void changeStatus(Long id, Status status) throws EntityNotFoundException {
        Room roomById = roomRepository.getRoomById(id);
        roomById.setStatus(status);
        roomRepository.update(roomById);
    }

}
