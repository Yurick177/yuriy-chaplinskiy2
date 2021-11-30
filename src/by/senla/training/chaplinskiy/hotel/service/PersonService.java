package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.List;

public interface PersonService {

    Person createPerson(String name, String lastName, int age);

    List<Person> sortAbs();

    int getNumberGuests();

    int getTotalPrice(Person person);

}
