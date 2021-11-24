package by.senla.training.chaplinskiy.adminHotel;

import java.time.LocalDateTime;
import java.util.List;

public class AdminHotel {

    public static void main(String[] args) {
        Person person = new Person("Yuriy", "Chaplinskiy", 31);
        Person person2 = new Person("Misha", "Chap", 21);
        Person person3 = new Person("Grisha", "Durun", 34);
        Room room1 = new Room(Status.AVAILABLE, 15, 1, 2, 1);
        Room room2 = new Room(Status.AVAILABLE, 1000, 2, 4, 4);
        Room room3 = new Room(Status.AVAILABLE, 34, 3, 1, 5);
        Hotel hotel = new Hotel();
        hotel.addRum(room1);
        hotel.addRum(room2);
        hotel.addRum(room3);
        hotel.checkInPerson(person, LocalDateTime.of(2021, 11, 19, 12, 0), LocalDateTime.of(2021, 11, 20, 12, 0));
        hotel.checkInPerson(person2, LocalDateTime.of(2021, 11, 20, 12, 0), LocalDateTime.of(2021, 11, 21, 12, 0));
        hotel.checkInPerson(person3, LocalDateTime.of(2021, 11, 22, 12, 0), LocalDateTime.of(2021, 11, 23, 12, 0));
        hotel.checkOutPerson(person);
        hotel.checkInPerson(person, LocalDateTime.of(2021, 11, 21, 12, 0), LocalDateTime.of(2021, 11, 22, 12, 0));
        hotel.checkOutPerson(person);
        hotel.checkInPerson(person, LocalDateTime.of(2021, 11, 21, 12, 0), LocalDateTime.of(2021, 11, 22, 12, 0));
        hotel.checkOutPerson(person);
        hotel.checkInPerson(person, LocalDateTime.of(2021, 11, 21, 12, 0), LocalDateTime.of(2021, 11, 22, 12, 0));
        hotel.checkOutPerson(person);

        List<Room> a = hotel.getRooms();
        for (Room i : a) {
            System.out.println("list rooms - " + i.getId());
        }

        List<Room> sortRoomsByPrice = hotel.getRoomsByPriceAsc();
        for (Room i : sortRoomsByPrice) {
            System.out.println("sorting rooms by price - " + i.getPrice());
        }

        List<Room> sortRoomsByStar = hotel.getRoomsByStarAsc();
        for (Room i : sortRoomsByStar) {
            System.out.println("sorting rooms by stars - " + i.getStar());
        }

        List<Room> sortRoomsByCapacityRoom = hotel.getRoomsByCapacityRoomAsc();
        for (Room i : sortRoomsByCapacityRoom) {
            System.out.println("sorting rooms by capacity - " + i.getCapacityRoom());
        }

        List<Room> sortAvailableRoomsByPrice = hotel.getAvailableRoomsByPriceAsc();
        for (Room i : sortAvailableRoomsByPrice) {
            System.out.println("sorting free rooms by price - " + i.getPrice());
        }

        List<Room> availableRoomsByCapacity = hotel.getAvailableRoomsByCapacityAsc();
        for (Room i : availableRoomsByCapacity) {
            System.out.println("list of free rooms by capacity - " + i.getCapacityRoom());
        }

        List<Room> availableRoomsByStar = hotel.getAvailableRoomsByStarAsc();
        for (Room i : availableRoomsByStar) {
            System.out.println("list of available rooms by stars - " + i.getStar());
        }

        List<Person> sortAbs = hotel.sortAbs();
        for (Person i : sortAbs) {
            System.out.println("sorted list of guests by last name - " + i.getLastName());
        }

        List<Room> sortOccupiedRoomsSortByDate = hotel.getOccupiedRoomsSortByDateAsc();
        for (Room i : sortOccupiedRoomsSortByDate) {
            System.out.println("sort occupied rooms by release date - " + i.getReleaseDate());
        }

        int freeNumbers = hotel.getFreeNumbers();
        System.out.println("total number of free rooms - " + freeNumbers);

        int numberGuests = hotel.getNumberGuests();
        System.out.println("total number of guests - " + numberGuests);

        int resul10 = hotel.getFreeNumbers();
        System.out.println("free rooms - " + resul10);

        int result11 = hotel.getNumberGuests();
        System.out.println("number of guests - " + result11);


        List<Room> result12 = hotel.getAvailableRoomsByDate(LocalDateTime.of(2021, 11, 22, 12, 0));
        for (Room i : result12) {
            System.out.println("available rooms by date - " + i.getId());
        }

        int totalPrice = hotel.getTotalPrice(person2);
        System.out.println("amount of payment for the room - " + totalPrice);

        List<PersonHistory> PersonHistories = room1.getPersonHistories(2);
        for (PersonHistory i : PersonHistories) {
            System.out.println("list of recent guests + name + dates of stay - " + i.getPerson().getName() + " " + i.getCheckInDate() + "    " + i.getReleaseDate());
        }


        Service food = new Service(ServiceType.FOOD, 23, 1, LocalDateTime.of(2021, 11, 21, 12, 0));
        Service beer = new Service(ServiceType.ALCOHOL, 5, 2, LocalDateTime.of(2021, 11, 22, 12, 0));

        hotel.addService(food);
        hotel.addService(beer);
        hotel.addServiceToRoom(room1, food);
        hotel.addServiceToRoom(room1, beer);

        List<Service> result15 = room1.getListService();
        for (Service i : result15) {
            System.out.println("list of services in the room - " + i.getServiceType().name());
        }

        List<Service> result16 = room1.getListServiceSortByPriceAsc();
        for (Service i : result16) {
            System.out.println("sorted list of guest services by price - " + i.getPrice());
        }

        List<Service> result17 = room1.getListServiceSortByDateAsc();
        for (Service i : result17) {
            System.out.println("sorted list of guest services by date  - " + i.getServiceDateTime());
        }

        List<Service> result18 = hotel.getServicesSortedPriceByService();
        for (Service i : result18) {
            System.out.println("view hotel prices sorted by price - " + i.getPrice());
        }

        List<Service> result19 = hotel.getServicesSortedByServiceType();
        for (Service i : result19) {
            System.out.println("view hotel services sorted by type - " + i.getServiceType());
        }

        List<Room> result20 = hotel.getRoomsByPriceAsc();
        for (Room i : result20) {
            System.out.println("view the prices of rooms in the hotel sorted by price - " + i.getPrice());
        }
    }

}
