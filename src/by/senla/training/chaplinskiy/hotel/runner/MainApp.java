package by.senla.training.chaplinskiy.hotel.runner;

import by.senla.training.chaplinskiy.hotel.entity.*;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.repository.PersonRepositoryImpl;
import by.senla.training.chaplinskiy.hotel.service.*;

import java.time.LocalDateTime;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        HotelService hotelService = HotelServiceImpl.getHotelService();
        PersonService personService = PersonServiceImpl.getPersonService();
        RoomService roomService = RoomServiceImpl.getRoomService();
        SupplyService supplyService = SupplyServiceImpl.getSupplyService();
        Person person = personService.createPerson("Yuriy", "Chaplinskiy", 31);
        Person person2 = personService.createPerson("Misha", "Chap", 21);
        Person person3 = personService.createPerson("Grisha", "Durun", 34);
        Room room1 = roomService.createRoom(Status.AVAILABLE, 15, 1, 2, 1);
        Room room2 = roomService.createRoom(Status.AVAILABLE, 1000, 2, 4, 4);
        Room room3 = roomService.createRoom(Status.AVAILABLE, 34, 3, 1, 5);
        Hotel hotel = hotelService.getHotel();
        hotelService.addRum(room1);
        hotelService.addRum(room2);
        hotelService.addRum(room3);
        hotelService.checkInPerson(person, LocalDateTime.of(2021, 11, 19, 12, 0), LocalDateTime.of(2021, 11, 20, 12, 0));
        hotelService.checkInPerson(person2, LocalDateTime.of(2021, 11, 20, 12, 0), LocalDateTime.of(2021, 11, 21, 12, 0));
        hotelService.checkInPerson(person3, LocalDateTime.of(2021, 11, 22, 12, 0), LocalDateTime.of(2021, 11, 23, 12, 0));
        hotelService.checkOutPerson(person);
        hotelService.checkInPerson(person, LocalDateTime.of(2021, 11, 21, 12, 0), LocalDateTime.of(2021, 11, 22, 12, 0));
        hotelService.checkOutPerson(person);
        hotelService.checkInPerson(person, LocalDateTime.of(2021, 11, 21, 12, 0), LocalDateTime.of(2021, 11, 22, 12, 0));
        hotelService.checkOutPerson(person);
        hotelService.checkInPerson(person, LocalDateTime.of(2021, 11, 21, 12, 0), LocalDateTime.of(2021, 11, 22, 12, 0));
        hotelService.checkOutPerson(person);

        List<Room> a = roomService.getRooms();
        for (Room i : a) {
            System.out.println("list rooms - " + i.getId());
        }

        List<Room> sortRoomsByPrice = roomService.getRoomsByPriceAsc();
        for (Room i : sortRoomsByPrice) {
            System.out.println("sorting rooms by price - " + i.getPrice());
        }

        List<Room> sortRoomsByStar = roomService.getRoomsByStarAsc();
        for (Room i : sortRoomsByStar) {
            System.out.println("sorting rooms by stars - " + i.getStar());
        }

        List<Room> sortRoomsByCapacityRoom = roomService.getRoomsByCapacityRoomAsc();
        for (Room i : sortRoomsByCapacityRoom) {
            System.out.println("sorting rooms by capacity - " + i.getCapacityRoom());
        }

        List<Room> sortAvailableRoomsByPrice = roomService.getAvailableRoomsByPriceAsc();
        for (Room i : sortAvailableRoomsByPrice) {
            System.out.println("sorting free rooms by price - " + i.getPrice());
        }

        List<Room> availableRoomsByCapacity = roomService.getAvailableRoomsByCapacityAsc();
        for (Room i : availableRoomsByCapacity) {
            System.out.println("list of free rooms by capacity - " + i.getCapacityRoom());
        }

        List<Room> availableRoomsByStar = roomService.getAvailableRoomsByStarAsc();
        for (Room i : availableRoomsByStar) {
            System.out.println("list of available rooms by stars - " + i.getStar());
        }

        List<Person> sortAbs = personService.sortAbs();
        for (Person i : sortAbs) {
            System.out.println("sorted list of guests by last name - " + i.getLastName());
        }

        List<Room> sortOccupiedRoomsSortByDate = roomService.getOccupiedRoomsSortByDateAsc();
        for (Room i : sortOccupiedRoomsSortByDate) {
            System.out.println("sort occupied rooms by release date - " + i.getReleaseDate());
        }

        int freeNumbers = roomService.getFreeNumbers();
        System.out.println("total number of free rooms - " + freeNumbers);

        int numberGuests = personService.getNumberGuests();
        System.out.println("total number of guests - " + numberGuests);

        int resul10 = roomService.getFreeNumbers();
        System.out.println("free rooms - " + resul10);

        int result11 = personService.getNumberGuests();
        System.out.println("number of guests - " + result11);


        List<Room> result12 = roomService.getAvailableRoomsByDate(LocalDateTime.of(2021, 11, 22, 12, 0));
        for (Room i : result12) {
            System.out.println("available rooms by date - " + i.getId());
        }

        int totalPrice = personService.getTotalPrice(person2);
        System.out.println("amount of payment for the room - " + totalPrice);

        List<PersonHistory> PersonHistories = room1.getPersonHistories(2);
        for (PersonHistory i : PersonHistories) {
            System.out.println("list of recent guests + name + dates of stay - " + i.getPerson().getName() + " " + i.getCheckInDate() + "    " + i.getReleaseDate());
        }


        Supply food = new Supply(SupplyType.FOOD, 23, 1, LocalDateTime.of(2021, 11, 21, 12, 0));
        Supply beer = new Supply(SupplyType.ALCOHOL, 5, 2, LocalDateTime.of(2021, 11, 22, 12, 0));

        supplyService.addSupply(food);
        supplyService.addSupply(beer);
        supplyService.addSupplyToRoom(room1, food);
        supplyService.addSupplyToRoom(room1, beer);

        List<Supply> result15 = room1.getListService();
        for (Supply i : result15) {
            System.out.println("list of services in the room - " + i.getServiceType().name());
        }

        List<Supply> result16 = room1.getListServiceSortByPriceAsc();
        for (Supply i : result16) {
            System.out.println("sorted list of guest services by price - " + i.getPrice());
        }

        List<Supply> result17 = room1.getListServiceSortByDateAsc();
        for (Supply i : result17) {
            System.out.println("sorted list of guest services by date  - " + i.getServiceDateTime());
        }

        List<Supply> result18 = supplyService.getSuppliesSortedByPrice();
        for (Supply i : result18) {
            System.out.println("view hotel prices sorted by price - " + i.getPrice());
        }

        List<Supply> result19 = supplyService.getSuppliesSortedByType();
        for (Supply i : result19) {
            System.out.println("view hotel services sorted by type - " + i.getServiceType());
        }

        List<Room> result20 = roomService.getRoomsByPriceAsc();
        for (Room i : result20) {
            System.out.println("view the prices of rooms in the hotel sorted by price - " + i.getPrice());
        }
    }

}
