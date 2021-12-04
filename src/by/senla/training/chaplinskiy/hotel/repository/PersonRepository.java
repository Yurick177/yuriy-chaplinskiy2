package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> getPersons();

    void setPersons(List<Person> persons);

    void addPerson(Person person);

}
