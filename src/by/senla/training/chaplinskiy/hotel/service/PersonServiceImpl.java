package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
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

public class PersonServiceImpl implements PersonService {

    private static PersonService personService = null;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;

    public PersonServiceImpl() {
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
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

}
