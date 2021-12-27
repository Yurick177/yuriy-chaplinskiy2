package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.excel.CsvReader;
import by.senla.training.chaplinskiy.hotel.excel.CsvWriter;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static by.senla.training.chaplinskiy.hotel.utils.LocalDateTimeUtils.getDate;

public class PersonServiceImpl implements PersonService {

    private static PersonService personService = null;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final CsvReader csvReader;
    private final CsvWriter csvWriter;
    private final PropertiesService propertiesService;

    public PersonServiceImpl() {
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.roomService = RoomServiceImpl.getRoomService();
        this.csvReader = CsvReader.getCsvReader();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.propertiesService = PropertiesService.getPropertiesService();
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
        Person person = null;
        try {
            person = personRepository.getPersonById(personId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            getTotalPrice(scanner);
        }
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

    public Long checkInPerson(Scanner scanner) {
        List<Room> rooms = roomRepository.getRooms();
        for (Room room : rooms) {
            if (room.getStatus().equals(Status.AVAILABLE)) {
                System.out.println("введите id пользователя ");
                Long id = Long.parseLong(scanner.nextLine());
                Person person = null;
                try {
                    person = personRepository.getPersonById(id);
                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                    checkInPerson(scanner);
                }
                LocalDateTime checkInDate = getDate(scanner, "введите год.месяц.день.часы.минуты заселения");
                LocalDateTime releaseDate = getDate(scanner, "введите год.месяц.день.часы.минуты выселения");
                roomService.addPerson(room, person, checkInDate, releaseDate);
                return room.getId();
            }
        }
        return null;
    }

    public void checkOutPerson(Scanner scanner) {
        System.out.println("введите id клиента ");
        Long personId = Long.parseLong(scanner.nextLine());
        try {
            personRepository.getPersonById(personId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            checkOutPerson(scanner);
        }

        System.out.println("введите id комнаты");
        Long roomId = Long.parseLong(scanner.nextLine());

        try {

            Room roomById = roomRepository.getRoomById(roomId);
            Person person = roomById.getPerson();
            if (person != null && person.getId().equals(personId)) {
                roomService.removePerson(roomById);
                System.out.println("выселен из комнаты");
            } else {
                System.out.println("в этой комнате человек не проживает");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void importFromFile() {
        try {
            List<String> linesFromFile = csvReader.getLinesFromFile(propertiesService.getValue("personPath"));
            List<Person> personList = getPersonsFromStrings(linesFromFile);
            personRepository.addAllPerson(personList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не правильный путь к файлу");
        }
    }

    private List<Person> getPersonsFromStrings(List<String> lines) {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Person person = getPersonFromString(lines.get(i));
            personList.add(person);
        }
        return personList;
    }

    private Person getPersonFromString(String line) {
        String[] split = line.split(",");
        Long id = Objects.equals(split[0], "") ? null : Long.parseLong(split[0]);
        String name = split[1];
        String lastName = split[2];
        int age = Integer.parseInt(split[3]);
        Person person = new Person(name, lastName, age);
        person.setId(id);
        return person;
    }

    public void exportFile() {
        List<Person> personList = personRepository.getPersons();
        List<String> lines = new ArrayList<>();
        lines.add("id,name,LastName,age");
        for (Person person : personList) {
            String line = person.getId() + "," + person.getName() + "," + person.getLastName() + "," + person.getAge();
            lines.add(line);
        }
        csvWriter.writeLinesToFile(lines, propertiesService.getValue("personResultPath"));
    }

}
