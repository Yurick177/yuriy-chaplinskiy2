package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;

import java.util.List;

public interface PersonHistoryService {

    List<PersonHistoryDto> getPersonHistoriesByRoomId(Long roomId);

    void exportFile();

    void addPersonHistory(PersonHistory personHistory);

}
