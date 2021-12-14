package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.util.List;
import java.util.Scanner;

public interface PersonService {

    Long createPerson(Scanner scanner);

    List<Person> sortAbs();

    int getNumberGuests();

    int getTotalPrice(Scanner scanner);

    void addRoom(Room room);

    Long checkInPerson(Scanner scanner);

    void checkOutPerson(Scanner scanner);

}
