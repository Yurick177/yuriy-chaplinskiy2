package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> persons;
    private static PersonRepositoryImpl personRepository = null;

    private PersonRepositoryImpl() {
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public static PersonRepositoryImpl getPersonRepository() {
        if (personRepository == null) {
            personRepository = new PersonRepositoryImpl();
        }
        return personRepository;
    }

    public Long addPerson(Person person) {
        long id = persons.stream().mapToLong(Person::getId).max().orElse(0);
        person.setId(id + 1);
        persons.add(person);
        return person.getId();
    }

    public Person getPersonById(Long id) {
        return persons.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Person> addAllPerson(List<Person> persons) {
        for (Person person : persons) {
            if (person.getId() == null) {
                addPerson(person);
            } else {
                update(person);
            }
        }
        return persons;
    }

    public void update(Person person) {
        Person current = getPersonById(person.getId());
        if (current == null) {
            System.out.println("Такого человека по Id " + person.getId() + " не найден");
        } else {
            current.setName(person.getName());
            current.setLastName(person.getLastName());
            current.setAge(person.getAge());
        }
    }

}
