package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;

import java.util.List;
import java.util.Scanner;

public interface PersonHistoryService {

    List<PersonHistoryDto> getPersonHistoriesByRoomId(Scanner scanner);

    void exportFile();

    void addPersonHistory(PersonHistory personHistory);

}
