package by.senla.training.chaplinskiy.hotel.controller;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.service.PersonHistoryService;
import by.senla.training.chaplinskiy.hotel.service.PersonHistoryServiceImpl;
import by.senla.training.chaplinskiy.hotel.utils.ScannerUtils;

import java.util.List;
import java.util.Scanner;

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

    public List<PersonHistoryDto> getPersonHistoriesByRoomId(Scanner scanner) {
        System.out.println("введите room id");
        Long roomId = ScannerUtils.getLongFromConsole(scanner);
        return personHistoryService.getPersonHistoriesByRoomId(roomId);
    }

    public void exportFile() {
        personHistoryService.exportFile();
    }

}
