package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static by.senla.training.chaplinskiy.hotel.utils.LocalDateTimeUtils.getLocalDateTimeFromString;

public class PersonServiceImpl implements PersonService {

    private static PersonService personService = null;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    public PersonServiceImpl() {
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.roomService = RoomServiceImpl.getRoomService();
    }

    public static PersonService getPersonService() {
        if (personService == null) {
            personService = new PersonServiceImpl();
        }
        return personService;
    }

    public Long createPerson(Scanner scanner) {
        System.out.println("введите имя");
        String name = scanner.nextLine();
        System.out.println("введите фамилию");
        String lastName = scanner.nextLine();
        System.out.println("введите возраст");
        int age = Integer.parseInt(scanner.nextLine());
        Person person = new Person(name, lastName, age);
        return personRepository.addPerson(person);
    }

    public List<Person> sortAbs() {
        List<Person> persons = personRepository.getPersons();
        persons.sort(Comparator.comparing(Person::getLastName));
        return persons;
    }

    public int getNumberGuests() {
        List<Person> persons = personRepository.getPersons();
        return persons.size();
    }

    public int getTotalPrice(Scanner scanner) {
        System.out.println("введите Id клиента");
        Long personId = Long.parseLong(scanner.nextLine());
        Person person = personRepository.getPersonById(personId);
        List<Room> rooms = roomRepository.getRooms();
        List<Room> personRooms = new ArrayList<>();
        for (Room i : rooms) {
            if (person == i.getPerson()) {
                personRooms.add(i);
            }
        }
        int price = 0;
        if (!personRooms.isEmpty()) {
            for (Room i : personRooms) {
                LocalDateTime releaseDate = i.getReleaseDate();
                LocalDateTime checkIn = i.getCheckInDate();
                long between = ChronoUnit.DAYS.between(checkIn, releaseDate);
                int dayPrice = i.getPrice();
                int roomPrice = (int) between * dayPrice;
                price += roomPrice;
            }
        }
        return price;
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
