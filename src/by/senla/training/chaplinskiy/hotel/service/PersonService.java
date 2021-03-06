package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonService {

    Long createPerson(String name, String lastName, int age);

    List<Person> sortAbs();

    int getNumberGuests();

    int getTotalPrice(Long personId) throws EntityNotFoundException;

    Long checkInPerson(Long id, LocalDateTime checkInDate, LocalDateTime releaseDate) throws EntityNotFoundException;

    void checkOutPerson(Long personId, Long roomId) throws EntityNotFoundException, ServiceException;

    void importFromFile();

    void exportFile();

}
