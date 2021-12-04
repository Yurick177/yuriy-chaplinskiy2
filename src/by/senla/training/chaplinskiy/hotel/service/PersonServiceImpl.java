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

public class PersonServiceImpl implements PersonService {

    private static PersonService personService = null;
    private PersonRepository personRepository;
    private RoomRepository roomRepository;


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

    public Person createPerson(String name, String lastName, int age) {
        Person person = new Person(name, lastName, age);
        personRepository.getPersons().add(person);
        return person;
    }

    public List<Person> sortAbs() {
        List<Person> persons = personRepository.getPersons();
        persons.sort(new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return persons;
    }

    public int getNumberGuests() {
        List<Person> persons = personRepository.getPersons();
        return persons.size();
    }

    public int getTotalPrice(Person person) {
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
