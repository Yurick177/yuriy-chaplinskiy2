package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Hotel;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelServiceImpl implements HotelService {

    private static HotelServiceImpl hotelService = null;
    private RoomService roomService;
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    private PersonRepository personRepository;

    private HotelServiceImpl() {
        this.hotelRepository = HotelRepositoryImpl.getHotelRepository();
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


    public void addRum(Room room) {
        List<Room> rooms = roomRepository.getRooms();
        rooms.add(room);
    }

    public void createHotel() {
        Hotel hotel = new Hotel();
        hotelRepository.setHotel(hotel);
    }

    public Hotel getHotel() {
        if (hotelRepository.getHotel() == null) {
            createHotel();
        }
        return hotelRepository.getHotel();
    }

    public void checkInPerson(Person person, LocalDateTime checkInDate, LocalDateTime releaseDate) {
        List<Room> rooms = roomRepository.getRooms();

        for (Room room : rooms) {
            if (room.getStatus().equals(Status.AVAILABLE)) {
                personRepository.addPerson(person);
                roomService.addPerson(room, person, checkInDate, releaseDate);
                break;
            }
        }
    }

    public void checkOutPerson(Person person) {
        List<Room> rooms = roomRepository.getRooms();
        for (Room room : rooms) {

            if (room.getPerson() != null && room.getPerson().equals(person)) {
                removePerson(person);
            }
        }
    }

    private void removePerson(Person person) {
        List<Person> persons = personRepository.getPersons();
        persons.remove(person);
    }

}
