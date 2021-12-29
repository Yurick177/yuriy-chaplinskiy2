package by.senla.training.chaplinskiy.hotel.converter;

import by.senla.training.chaplinskiy.hotel.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonCsvConverter implements CsvConverter<Person> {

    private static PersonCsvConverter personCsvConverter;

    private PersonCsvConverter() {
    }

    public static PersonCsvConverter getPersonCsvConverter() {
        if (personCsvConverter == null) {
            personCsvConverter = new PersonCsvConverter();
        }
        return personCsvConverter;
    }

    @Override
    public List<Person> getFromStrings(List<String> lines) {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Person person = getPersonFromString(lines.get(i));
            personList.add(person);
        }
        return personList;
    }

    private Person getPersonFromString(String line) {
        String[] split = line.split(",");
        Long id = Objects.equals(split[0], "") ? null : Long.parseLong(split[0]);
        String name = split[1];
        String lastName = split[2];
        int age = Integer.parseInt(split[3]);
        Person person = new Person(name, lastName, age);
        person.setId(id);
        return person;
    }

    @Override
    public List<String> getStrings(List<Person> entities) {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,LastName,age");
        for (Person person : entities) {
            String line = person.getId() + "," + person.getName() + "," + person.getLastName() + "," + person.getAge();
            lines.add(line);
        }
        return lines;
    }

}
