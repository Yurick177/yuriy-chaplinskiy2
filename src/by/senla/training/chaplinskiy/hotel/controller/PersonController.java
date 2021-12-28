package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.PersonService;
import by.senla.training.chaplinskiy.hotel.service.PersonServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static by.senla.training.chaplinskiy.hotel.utils.ScannerUtils.getDate;

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

    public Long createPerson(Scanner scanner) {
        System.out.println("введите имя");
        String name = scanner.nextLine();
        System.out.println("введите фамилию");
        String lastName = scanner.nextLine();
        System.out.println("введите возраст");
        int age = Integer.parseInt(scanner.nextLine());
        return personService.createPerson(name, lastName, age);
    }

    public List<Person> sortAbs() {
        return personService.sortAbs();
    }

    public int getNumberGuests() {
        return personService.getNumberGuests();
    }

    public int getTotalPrice(Scanner scanner) {
        System.out.println("введите Id клиента");
        Long personId = Long.parseLong(scanner.nextLine());
        try {
            return personService.getTotalPrice(personId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return getTotalPrice(scanner);
        }
    }

    public Long checkInPerson(Scanner scanner) {
        System.out.println("введите id пользователя ");
        Long id = Long.parseLong(scanner.nextLine());
        LocalDateTime checkInDate = getDate(scanner, "введите год.месяц.день.часы.минуты заселения");
        LocalDateTime releaseDate = getDate(scanner, "введите год.месяц.день.часы.минуты выселения");
        try {
            return personService.checkInPerson(id, checkInDate, releaseDate);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return checkInPerson(scanner);
        }
    }

    public void checkOutPerson(Scanner scanner) {
        System.out.println("введите id клиента ");
        Long personId = Long.parseLong(scanner.nextLine());
        System.out.println("введите id комнаты");
        Long roomId = Long.parseLong(scanner.nextLine());
        try {
            personService.checkOutPerson(personId, roomId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            checkOutPerson(scanner);
        }
    }

    public void importFromFile() {
        personService.importFromFile();
    }

    public void exportFile() {
        personService.exportFile();
    }

}
