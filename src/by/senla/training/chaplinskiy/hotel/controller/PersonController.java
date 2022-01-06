package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.exception.ServiceException;
import by.senla.training.chaplinskiy.hotel.service.PersonService;
import by.senla.training.chaplinskiy.hotel.service.PersonServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class PersonController {

    private final PersonService personService;
    private static PersonController personController;

    private PersonController() {
        this.personService = PersonServiceImpl.getPersonService();
    }

    public static PersonController getPersonController() {
        if (personController == null) {
            personController = new PersonController();
        }
        return personController;
    }

    public String createPerson(String name, String lastName, int age) {
        return String.valueOf(personService.createPerson(name, lastName, age));
    }

    public List<Person> sortAbs() {
        return personService.sortAbs();
    }

    public int getNumberGuests() {
        return personService.getNumberGuests();
    }

    public String getTotalPrice(Long personId) {
        try {
            return String.valueOf(personService.getTotalPrice(personId));
        } catch (EntityNotFoundException e) {
            return e.getMessage();
        }
    }

    public String checkInPerson(Long id, LocalDateTime checkInDate, LocalDateTime releaseDate) {
        try {
            return String.valueOf(personService.checkInPerson(id, checkInDate, releaseDate));
        } catch (EntityNotFoundException e) {
            return e.getMessage();
        }
    }

    public String checkOutPerson(Long personId, Long roomId) {
        try {
            personService.checkOutPerson(personId, roomId);
        } catch (EntityNotFoundException | ServiceException e) {
            return e.getMessage();
        }
        return "Выселен из номера";
    }

    public void importFromFile() {
        personService.importFromFile();
    }

    public void exportFile() {
        personService.exportFile();
    }

}
