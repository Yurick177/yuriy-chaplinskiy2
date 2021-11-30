package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private List<Person> persons;
    private static PersonRepositoryImpl personRepository = null;


    public List<Person> getPersons() {
        if (persons == null) {
            persons = new ArrayList<>();
        }
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public static PersonRepositoryImpl getPersonRepository() {
        if (personRepository == null) {
            personRepository = new PersonRepositoryImpl();
        }
        return personRepository;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

}
