package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.service.PersonHistoryService;
import by.senla.training.chaplinskiy.hotel.service.PersonHistoryServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class PersonHistoryController {

    private final PersonHistoryService personHistoryService;
    private static PersonHistoryController personHistoryController;

    private PersonHistoryController() {
        this.personHistoryService = PersonHistoryServiceImpl.getPersonHistoryService();
    }

    public static PersonHistoryController getPersonHistoryController() {
        if (personHistoryController == null) {
            personHistoryController = new PersonHistoryController();
        }
        return personHistoryController;
    }

    public String getPersonHistoriesByRoomId(Long roomId) {
        try {
            List<PersonHistoryDto> personHistoriesByRoomId = personHistoryService.getPersonHistoriesByRoomId(roomId);
            return personHistoriesByRoomId.stream()
                    .map(i -> i.getPersonFirstName() + " " + i.getPersonLastName() + " " + i.getCheckInDate() + " " + i.getReleaseDate())
                    .collect(Collectors.joining("\n"));
        } catch (EntityNotFoundException e) {
            return e.getMessage();
        }
    }

    public void exportFile() {
        personHistoryService.exportFile();
    }

}
