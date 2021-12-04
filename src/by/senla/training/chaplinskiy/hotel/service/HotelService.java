package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Hotel;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface HotelService {

    void createHotel();

    Hotel getHotel();

    void addRum(Room room);

    void checkInPerson(Person person, LocalDateTime checkInDate, LocalDateTime releaseDate);

    void checkOutPerson(Person person);

}
