package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static by.senla.training.chaplinskiy.hotel.LocalDateTimeUtils.getLocalDateTimeFromString;

public class HotelServiceImpl implements HotelService {

    private static HotelServiceImpl hotelService = null;
    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;

    private HotelServiceImpl() {
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
        this.roomService = RoomServiceImpl.getRoomService();
    }

    public static HotelService getHotelService() {
        if (hotelService == null) {
            hotelService = new HotelServiceImpl();
        }
        return hotelService;
    }

    public void addRoom(Room room) {
        List<Room> rooms = roomRepository.getRooms();
        rooms.add(room);
    }

    public Long checkInPerson(Scanner scanner) {
        List<Room> rooms = roomRepository.getRooms();
        for (Room room : rooms) {
            if (room.getStatus().equals(Status.AVAILABLE)) {
                System.out.println("введите id пользователя ");
                Long id = Long.parseLong(scanner.nextLine());
                Person person = personRepository.getPersonById(id);
                System.out.println("введите год.месяц.день.часы.минуты заселения");
                String date = scanner.nextLine();
                LocalDateTime checkInDate = getLocalDateTimeFromString(date);
                System.out.println("введите год.месяц.день.часы.минуты выселения");
                String date2 = scanner.nextLine();
                LocalDateTime releaseDate = getLocalDateTimeFromString(date2);
                roomService.addPerson(room, person, checkInDate, releaseDate);
                return room.getId();
            }
        }
        return null;
    }

    public void checkOutPerson(Scanner scanner) {
        System.out.println("введите id клиента ");
        Long personId = Long.parseLong(scanner.nextLine());
        Person personById = personRepository.getPersonById(personId);
        if (personById != null) {
            System.out.println("введите id комнаты");
            Long roomId = Long.parseLong(scanner.nextLine());
            Room roomById = roomRepository.getRoomById(roomId);
            if (roomById != null) {
                Person person = roomById.getPerson();
                if (person != null && person.getId().equals(personId)) {
                    roomService.removePerson(roomById);
                    System.out.println("выселен из комнаты");
                } else {
                    System.out.println("в этой комнате человек не проживает");
                }
            } else {
                System.out.println("комната по id не найдена ");
            }
        } else {
            System.out.println("клиент по id не найден ");
        }
    }

}
