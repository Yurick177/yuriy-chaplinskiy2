package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.repository.*;
import by.senla.training.chaplinskiy.hotel.utils.ScannerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonHistoryServiceImpl implements PersonHistoryService {

    private static PersonHistoryService personHistoryService = null;
    private final PersonHistoryRepository personHistoryRepository;
    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;

    private PersonHistoryServiceImpl() {
        this.roomRepository = RoomRepositoryImpl.getRoomRepository();
        this.personHistoryRepository = PersonHistoryRepositoryImpl.getPersonHistoryRepository();
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
    }

    public static PersonHistoryService getPersonHistoryService() {
        if (personHistoryService == null) {
            personHistoryService = new PersonHistoryServiceImpl();
        }
        return personHistoryService;
    }

    @Override
    public List<PersonHistoryDto> getPersonHistoriesByRoomId(Scanner scanner) {
        System.out.println("введите room id");
        Long roomId = ScannerUtils.getLongFromConsole(scanner);
        List<PersonHistory> personHistoriesByRoomId = personHistoryRepository.getPersonHistoriesByRoomId(roomId);
        List<PersonHistoryDto> personHistoryDtos = new ArrayList<>();
        for (PersonHistory i : personHistoriesByRoomId) {
            PersonHistoryDto personHistoryDto = new PersonHistoryDto();
            personHistoryDto.setId(i.getId());
            personHistoryDto.setPersonId(i.getPersonId());
            personHistoryDto.setReleaseDate(i.getReleaseDate());
            personHistoryDto.setCheckInDate(i.getCheckInDate());
            personHistoryDto.setRoomId(i.getRoomId());
            Person person = personRepository.getPersonById(i.getPersonId());
            personHistoryDto.setPersonFirstName(person.getName());
            personHistoryDto.setPersonLastName(person.getLastName());
            personHistoryDtos.add(personHistoryDto);
        }

        return personHistoryDtos;

    }
}
