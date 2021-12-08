package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.List;
import java.util.Scanner;

public interface PersonService {

    Long createPerson(Scanner scanner);

    List<Person> sortAbs();

    int getNumberGuests();

    int getTotalPrice(Scanner scanner);

}
