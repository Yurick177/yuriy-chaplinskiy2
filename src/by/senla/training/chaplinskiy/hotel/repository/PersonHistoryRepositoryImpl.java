package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonHistoryRepositoryImpl implements PersonHistoryRepository {

    private static PersonHistoryRepositoryImpl personHistoryRepository = null;
    private final List<PersonHistory> personHistoryList;

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
        long id = personHistoryList.stream().mapToLong(PersonHistory::getId).max().orElse(0);
        personHistory.setId(id + 1);
        personHistoryList.add(personHistory);
    }

    public List<PersonHistory> getPersonHistoriesByRoomId(Long roomId) {
        return personHistoryList.stream().filter(a -> a.getRoomId().equals(roomId)).collect(Collectors.toList());
    }

    public List<PersonHistory> getAll() {
        return personHistoryList;
    }

}
