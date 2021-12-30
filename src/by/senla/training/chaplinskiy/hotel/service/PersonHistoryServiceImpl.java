package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.converter.CsvConverter;
import by.senla.training.chaplinskiy.hotel.converter.PersonHistoryCsvConverter;
import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.exception.EntityNotFoundException;
import by.senla.training.chaplinskiy.hotel.repository.PersonHistoryRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonHistoryRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepository;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.stringreader.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryServiceImpl implements PersonHistoryService {

    private static PersonHistoryService personHistoryService = null;
    private final PersonHistoryRepository personHistoryRepository;
    private final PersonRepository personRepository;
    private final CsvWriter csvWriter;
    private final PropertiesService propertiesService;
    private final CsvConverter<PersonHistory> csvConverter;

    private PersonHistoryServiceImpl() {
        this.personHistoryRepository = PersonHistoryRepositoryImpl.getPersonHistoryRepository();
        this.personRepository = PersonRepositoryImpl.getPersonRepository();
        this.csvWriter = CsvWriter.getCsvWriter();
        this.propertiesService = PropertiesService.getPropertiesService();
        this.csvConverter = PersonHistoryCsvConverter.getPersonHistoryCsvConverter();
    }

    public static PersonHistoryService getPersonHistoryService() {
        if (personHistoryService == null) {
            personHistoryService = new PersonHistoryServiceImpl();
        }
        return personHistoryService;
    }

    @Override
    public List<PersonHistoryDto> getPersonHistoriesByRoomId(Long roomId) throws EntityNotFoundException {
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

    public void exportFile() {
        List<PersonHistory> personHistoryList = personHistoryRepository.getAll();
        List<String> lines = csvConverter.getStrings(personHistoryList);
        csvWriter.writeLinesToFile(lines, propertiesService.getValue("personHistoryResultPath"));
    }

    public void addPersonHistory(PersonHistory personHistory) {
        List<PersonHistory> personHistoriesByRoomId = personHistoryRepository.getPersonHistoriesByRoomId(personHistory.getRoomId());
        String personHistorySize = propertiesService.getValue("personHistorySize");
        int size = Integer.parseInt(personHistorySize);
        if (personHistoriesByRoomId.size() >= size) {
            long id = personHistoriesByRoomId.stream().mapToLong(PersonHistory::getId).min().orElse(0);
            personHistoryRepository.removeById(id);
        }
        personHistoryRepository.addPersonHistory(personHistory);
    }

}
