package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;

import java.util.List;

public interface PersonHistoryRepository {

    void addPersonHistory(PersonHistory personHistory);

    List<PersonHistory> getPersonHistoriesByRoomId(Long roomId);

    List<PersonHistory> getAll();

    void removeById(Long id);

}
