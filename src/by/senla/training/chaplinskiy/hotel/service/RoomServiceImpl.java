package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.excel.CsvReader;
import by.senla.training.chaplinskiy.hotel.excel.CsvWriter;
import by.senla.training.chaplinskiy.hotel.repository.PersonHistoryRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonHistoryRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

import static by.senla.training.chaplinskiy.hotel.entity.Status.AVAILABLE;
import static by.senla.training.chaplinskiy.hotel.entity.Status.OCСUPIED;
import static by.senla.training.chaplinskiy.hotel.utils.LocalDateTimeUtils.getDate;

public class RoomServiceImpl implements RoomService {

    private static RoomService roomService = null;
    private final PersonHistoryRepository personHistoryRepository;
    private final RoomRepository roomRepository;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    private RoomServiceImpl() {
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.personHistoryRepository = PersonHistoryRepositoryImpl.getPersonHistoryRepository();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.csvReader = CsvReader.getCsvReader();
    }

    public static RoomService getRoomService() {
        if (roomService == null) {
            roomService = new RoomServiceImpl();
        }
        return roomService;
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

        Room room = new Room(status, Integer.parseInt(price), Long.parseLong(id), Integer.parseInt(star), Integer.parseInt(capacityRoom));
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

    public List<Room> getAvailableRoomsByDate(Scanner scanner) {
        LocalDateTime localDateTime = getDate(scanner, "введите год.месяц.день.часы.минуты заселения");
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
        personHistoryRepository.addPersonHistory(personHistory);
        room.getPersonHistories().add(personHistory);

    }

    @Override
    public void removePerson(Room room) {
        room.setPerson(null);
        room.setStatus(AVAILABLE);
    }

    public void exportFile() {
        List<Room> roomList = roomService.getRooms();
        List<String> lines = new ArrayList<>();
        lines.add("id,status,price,star,capacityRoom");
        for (Room room : roomList) {
            String line = room.getId() + "," + room.getStatus() + "," + room.getPrice() + "," + room.getStar() + "," + room.getCapacityRoom();
            lines.add(line);
        }
        csvWriter.writeLinesToFile(lines, "C:\\Users\\Ura\\IdeaProjects\\yuriy-chaplinskiy1\\resources\\Room_Result.csv");
    }

    public void importFromFile() {
        try {
            List<String> lineFromString = csvReader.getLinesFromFile("C:\\Users\\Ura\\IdeaProjects\\yuriy-chaplinskiy1\\resources\\Room.csv");
            List<Room> roomList = getRoomsFromStrings(lineFromString);
            roomRepository.addAll(roomList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не правильный путь к файлу");
        }
    }

    private List<Room> getRoomsFromStrings(List<String> lines) {
        List<Room> roomsList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Room room = getRoomFromString(lines.get(i));
            roomsList.add(room);
        }
        return roomsList;
    }

    private Room getRoomFromString(String line) {
        String[] split = line.split(",");
        Long id = Objects.equals(split[0], "") ? null : Long.parseLong(split[0]);
        Status status = Status.valueOf(split[1]);
        int price = Integer.parseInt(split[2]);
        int star = Integer.parseInt(split[3]);
        int capacityRoom = Integer.parseInt(split[4]);
        return new Room(status, price, id, star, capacityRoom);
    }

}
