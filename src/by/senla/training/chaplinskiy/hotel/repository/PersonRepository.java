package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> getPersons();

    void setPersons(List<Person> persons);

    Long addPerson(Person person);

    Person getPersonById(Long id);

}
