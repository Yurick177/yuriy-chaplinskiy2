package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;

import java.util.List;

public interface PersonHistoryService {

    List<PersonHistoryDto> getPersonHistoriesByRoomId(Long roomId) throws EntityNotFoundException;

    void exportFile();

    void addPersonHistory(PersonHistory personHistory);

}
