package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.converter.CsvConverter;
import by.senla.training.chaplinskiy.hotel.converter.PersonCsvConverter;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.exception.CustomRuntimeException;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.exception.ServiceException;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepository;
import by.senla.training.chaplinskiy.hotel.repository.RoomRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvReader;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvWriter;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private static PersonService personService = null;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final CsvReader csvReader;
    private final CsvWriter csvWriter;
    private final PropertiesService propertiesService;
    private final CsvConverter<Person> csvConverter;

    public PersonServiceImpl() {
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.roomService = RoomServiceImpl.getRoomService();
        this.csvReader = CsvReader.getCsvReader();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.propertiesService = PropertiesService.getPropertiesService();
        this.csvConverter = PersonCsvConverter.getPersonCsvConverter();
    }

    public static PersonService getPersonService() {
        if (personService == null) {
            personService = new PersonServiceImpl();
        }
        return personService;
    }

    public Long createPerson(String name, String lastName, int age) {
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

    public int getTotalPrice(Long personId) throws EntityNotFoundException {

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

    public Long checkInPerson(Long id, LocalDateTime checkInDate, LocalDateTime releaseDate) throws EntityNotFoundException {
        List<Room> rooms = roomRepository.getRooms();
        for (Room room : rooms) {
            if (room.getStatus().equals(Status.AVAILABLE)) {
                Person person = personRepository.getPersonById(id);
                roomService.addPerson(room, person, checkInDate, releaseDate);
                return room.getId();
            }
        }
        return null;
    }

    public void checkOutPerson(Long personId, Long roomId) throws EntityNotFoundException, ServiceException {
        personRepository.getPersonById(personId);
        Room roomById = roomRepository.getRoomById(roomId);
        Person person = roomById.getPerson();
        if (person != null && person.getId().equals(personId)) {
            roomService.removePerson(roomById);
        } else {
            throw new ServiceException("в этой комнате человек не проживает");
        }
    }

    @Override
    public void importFromFile() {
        try {
            List<String> linesFromFile = csvReader.getLinesFromFile(propertiesService.getValue("personPath"));
            List<Person> personList = csvConverter.getFromStrings(linesFromFile);
            personRepository.addAllPerson(personList);
        } catch (FileNotFoundException e) {
            throw new CustomRuntimeException("Не правильный путь к файлу");
        }
    }

    public void exportFile() {
        List<Person> personList = personRepository.getPersons();
        List<String> lines = csvConverter.getStrings(personList);
        csvWriter.writeLinesToFile(lines, propertiesService.getValue("personResultPath"));
    }

}
