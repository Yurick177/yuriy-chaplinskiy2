package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;

import java.util.List;

public interface PersonRepository {

    List<Person> getPersons();

    Long addPerson(Person person);

    Person getPersonById(Long id) throws EntityNotFoundException;

    List<Person> addAllPerson(List<Person> persons);

}
