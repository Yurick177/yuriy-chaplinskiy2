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

    public String createPerson(String name, String lastName, String ageString) {
        try {
            int age = Integer.parseInt(ageString);
            return String.valueOf(personService.createPerson(name, lastName, age));
        } catch (NumberFormatException a) {
            return "Ошибка !!! вы ввели не тот символ";
        }

    }

    public List<Person> sortAbs() {
        return personService.sortAbs();
    }

    public int getNumberGuests() {
        return personService.getNumberGuests();
    }

    public String getTotalPrice(String personIdString) {
        try {
            Long personId = Long.parseLong(personIdString);
            return String.valueOf(personService.getTotalPrice(personId));
        } catch (EntityNotFoundException e) {
            return e.getMessage();
        } catch (NumberFormatException a) {
            return "Ошибка !!! вы ввели не тот символ";
        }
    }

    public String checkInPerson(Long id, LocalDateTime checkInDate, LocalDateTime releaseDate) {
        try {
            return String.valueOf(personService.checkInPerson(id, checkInDate, releaseDate));
        } catch (EntityNotFoundException e) {
            return e.getMessage();
        }
    }

    public String checkOutPerson(String personIdString, String roomIdString) {
        try {
            Long personId = Long.parseLong(personIdString);
            Long roomId = Long.parseLong(roomIdString);
            personService.checkOutPerson(personId, roomId);
        } catch (EntityNotFoundException | ServiceException e) {
            return e.getMessage();
        } catch (NumberFormatException a) {
            return "Ошибка !!! вы ввели не тот символ";
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
