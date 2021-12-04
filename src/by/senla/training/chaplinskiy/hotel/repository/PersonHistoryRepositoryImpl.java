package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryRepositoryImpl implements PersonHistoryRepository {

    private static PersonHistoryRepositoryImpl personHistoryRepository = null;
    private List<PersonHistory> personHistoryList;

    private PersonHistoryRepositoryImpl() {
        this.personHistoryList = new ArrayList<>();
    }

    public static PersonHistoryRepository getPersonHistoryRepository() {
        if (personHistoryRepository == null) {
            personHistoryRepository = new PersonHistoryRepositoryImpl();
        }
        return personHistoryRepository;
    }

    public void addPersonHistory(PersonHistory personHistory) {
        personHistoryList.add(personHistory);
    }

}
