package by.senla.training.chaplinskiy.hotel.runner;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;
import by.senla.training.chaplinskiy.hotel.menu.IAction;
import by.senla.training.chaplinskiy.hotel.menu.Menu;
import by.senla.training.chaplinskiy.hotel.menu.MenuItem;
import by.senla.training.chaplinskiy.hotel.menu.Navigator;
import by.senla.training.chaplinskiy.hotel.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        HotelService hotelService = HotelServiceImpl.getHotelService();
        RoomService roomService = RoomServiceImpl.getRoomService();
        PersonService personService = PersonServiceImpl.getPersonService();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите пункт действия:");
            Navigator navigator = new Navigator();
            Menu menu = new Menu();

            MenuItem getRoomsItem = new MenuItem();
            getRoomsItem.setTitle("1 getRooms");
            IAction getRoom = () -> {
                List<Room> rooms = roomService.getRooms();
                for (Room i : rooms) {
                    System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
                }
            };
            getRoomsItem.setAction(getRoom);
            menu.getMenuItems().add(getRoomsItem);

            MenuItem addRoomItem = new MenuItem();
            addRoomItem.setTitle("2 addRoom");
            IAction addRoom = () -> {
                roomService.createRoom(scan);
            };
            addRoomItem.setAction(addRoom);
            menu.getMenuItems().add(addRoomItem);

            MenuItem createPersonItem = new MenuItem();
            createPersonItem.setTitle("3 createPerson");
            IAction createPerson = () -> {
                System.out.println("create Person");
                Long id = personService.createPerson(scan);
                System.out.println("person id is " + id);
            };
            createPersonItem.setAction(createPerson);
            menu.getMenuItems().add(createPersonItem);

            MenuItem checkInPersonItem = new MenuItem();
            checkInPersonItem.setTitle("4 checkInPerson");
            IAction checkInPerson = () -> {
                Long roomId = hotelService.checkInPerson(scan);
                if (roomId != null) {
                    System.out.println("человек заселен в комнату под номером " + roomId);
                } else {
                    System.out.println("свободных комнат нет");
                }
            };
            checkInPersonItem.setAction(checkInPerson);
            menu.getMenuItems().add(checkInPersonItem);

            MenuItem checkOutPersonItem = new MenuItem();
            checkOutPersonItem.setTitle("5 checkOutPerson");
            IAction checkOutPerson = () -> hotelService.checkOutPerson(scan);
            checkOutPersonItem.setAction(checkOutPerson);
            menu.getMenuItems().add(checkOutPersonItem);

            MenuItem sortAbsItem = new MenuItem();
            sortAbsItem.setTitle("6 sortPersonByAbs");
            IAction sortAbc = () -> {
                List<Person> persons = personService.sortAbs();
                for (Person i : persons) {
                    System.out.println(i.getId() + " " + i.getName() + " " + i.getLastName() + " " + i.getAge());
                }
            };
            sortAbsItem.setAction(sortAbc);
            menu.getMenuItems().add(sortAbsItem);

            MenuItem getNumberGuestsItem = new MenuItem();
            getNumberGuestsItem.setTitle("7 getNumberGuests");
            IAction getNumberGuest = () -> System.out.println(personService.getNumberGuests());
            getNumberGuestsItem.setAction(getNumberGuest);
            menu.getMenuItems().add(getNumberGuestsItem);

            MenuItem getTotalPriceItem = new MenuItem();
            getTotalPriceItem.setTitle("8 getTotalPrice");
            IAction getTotalPrice = () -> System.out.println(personService.getTotalPrice(scan));
            getTotalPriceItem.setAction(getTotalPrice);
            menu.getMenuItems().add(getTotalPriceItem);

            MenuItem getRoomsByPriceAscItem = new MenuItem();
            getRoomsByPriceAscItem.setTitle("9 getRoomsByPriceAsc");
            IAction getRoomsByPriceAsc = () -> {
                List<Room> rooms = roomService.getRoomsByPriceAsc();
                for (Room i : rooms) {
                    System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
                }
            };
            getRoomsByPriceAscItem.setAction(getRoomsByPriceAsc);
            menu.getMenuItems().add(getRoomsByPriceAscItem);

            MenuItem getRoomsByPriceDescItem = new MenuItem();
            getRoomsByPriceDescItem.setTitle("10 getRoomsByPriceDesc");
            IAction getRoomsByPriceDesc = () -> {
                List<Room> rooms = roomService.getRoomsByPriceDesc();
                for (Room i : rooms) {
                    System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
                }
            };
            getRoomsByPriceDescItem.setAction(getRoomsByPriceDesc);
            menu.getMenuItems().add(getRoomsByPriceDescItem);

            MenuItem getRoomsByStarAscItem = new MenuItem();
            getRoomsByStarAscItem.setTitle("11 getRoomsByStarAsc");
            IAction getRoomsByStarAsc = () -> {
                List<Room> rooms = roomService.getRoomsByStarAsc();
                for (Room i : rooms) {
                    System.out.println(" количество звезд " + i.getStar() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
                }
            };
            getRoomsByStarAscItem.setAction(getRoomsByStarAsc);
            menu.getMenuItems().add(getRoomsByStarAscItem);

            MenuItem getRoomsByStarDescItem = new MenuItem();
            getRoomsByStarDescItem.setTitle("12 getRoomsByStarDesc");
            IAction getRoomsByStarDesc = () -> {
                List<Room> rooms = roomService.getRoomsByStarDesc();
                for (Room i : rooms) {
                    System.out.println("количество звезд " + i.getStar() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
                }
            };
            getRoomsByStarDescItem.setAction(getRoomsByStarDesc);
            menu.getMenuItems().add(getRoomsByStarDescItem);

            MenuItem getRoomsByCapacityRoomAscItem = new MenuItem();
            getRoomsByCapacityRoomAscItem.setTitle("13 getRoomsByCapacityRoomAsc");
            IAction getRoomsByCapacityRoomAsc = () -> {
                List<Room> rooms = roomService.getRoomsByCapacityRoomAsc();
                for (Room i : rooms) {
                    System.out.println("вместимость " + i.getCapacityRoom() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " статус " + i.getStatus());
                }
            };
            getRoomsByCapacityRoomAscItem.setAction(getRoomsByCapacityRoomAsc);
            menu.getMenuItems().add(getRoomsByCapacityRoomAscItem);

            MenuItem getRoomsByCapacityRoomDescItem = new MenuItem();
            getRoomsByCapacityRoomDescItem.setTitle("14 getRoomsByCapacityRoomDesc");
            IAction getRoomsByCapacityRoomDesc = () -> {
                List<Room> rooms = roomService.getRoomsByCapacityRoomDesc();
                for (Room i : rooms) {
                    System.out.println("вместимость " + i.getCapacityRoom() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " статус " + i.getStatus());
                }
            };
            getRoomsByCapacityRoomDescItem.setAction(getRoomsByCapacityRoomDesc);
            menu.getMenuItems().add(getRoomsByCapacityRoomDescItem);


            MenuItem getPersonHistoryByRoomItem = new MenuItem();
            getPersonHistoryByRoomItem.setTitle("15 getPersonHistoryByRoomId");
            IAction getPersonHistoryByRoomId = () -> {
                List<PersonHistory> personHistoriesByRoomId = roomService.getPersonHistoriesByRoomId(scan);
                for (PersonHistory i : personHistoriesByRoomId) {
                    System.out.println(i.getPerson().getName() + " " + i.getPerson().getLastName() + " " + i.getCheckInDate() + " " + i.getReleaseDate());
                }
            };
            getPersonHistoryByRoomItem.setAction(getPersonHistoryByRoomId);
            menu.getMenuItems().add(getPersonHistoryByRoomItem);

            MenuItem getAvailableRoomsByPriceAscItem = new MenuItem();
            getAvailableRoomsByPriceAscItem.setTitle("16 getAvailableRoomsByPriceAsc");
            IAction getAvailableRoomsByPriceAsc = () -> {
                List<Room> rooms = roomService.getAvailableRoomsByPriceAsc();
                for (Room i : rooms) {
                    System.out.println("статус " + i.getStatus() + " цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
                }
            };
            getAvailableRoomsByPriceAscItem.setAction(getAvailableRoomsByPriceAsc);
            menu.getMenuItems().add(getAvailableRoomsByPriceAscItem);

            MenuItem getAvailableRoomsByPriceDescItem = new MenuItem();
            getAvailableRoomsByPriceDescItem.setTitle("17 getAvailableRoomsByPriceDesc");
            IAction getAvailableRoomsByPriceDesc = () -> {
                List<Room> rooms = roomService.getAvailableRoomsByPriceDesc();
                for (Room i : rooms) {
                    System.out.println("статус " + i.getStatus() + " цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
                }
            };
            getAvailableRoomsByPriceDescItem.setAction(getAvailableRoomsByPriceDesc);
            menu.getMenuItems().add(getAvailableRoomsByPriceDescItem);

            MenuItem getAvailableRoomsByCapacityAscItem = new MenuItem();
            getAvailableRoomsByCapacityAscItem.setTitle("18 getAvailableRoomsByCapacityAsc");
            IAction getAvailableRoomsByCapacityAsc = () -> {
                List<Room> rooms = roomService.getAvailableRoomsByCapacityAsc();
                for (Room i : rooms) {
                    System.out.println("статус " + i.getStatus() + " вместимость " + i.getCapacityRoom() + "цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
                }
            };
            getAvailableRoomsByCapacityAscItem.setAction(getAvailableRoomsByCapacityAsc);
            menu.getMenuItems().add(getAvailableRoomsByCapacityAscItem);

            MenuItem getAvailableRoomsByCapacityDescItem = new MenuItem();
            getAvailableRoomsByCapacityDescItem.setTitle("19  getAvailableRoomsByCapacityDesc");
            IAction getAvailableRoomsByCapacityDesc = () -> {
                List<Room> rooms = roomService.getAvailableRoomsByCapacityDesc();
                for (Room i : rooms) {
                    System.out.println("статус " + i.getStatus() + " вместимость " + i.getCapacityRoom() + "цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
                }
            };
            getAvailableRoomsByCapacityDescItem.setAction(getAvailableRoomsByCapacityDesc);
            menu.getMenuItems().add(getAvailableRoomsByCapacityDescItem);

            MenuItem getAvailableRoomsByStarAscItem = new MenuItem();
            getAvailableRoomsByStarAscItem.setTitle("20 getAvailableRoomsByStarAsc");
            IAction getAvailableRoomsByStarAsc = () -> {
                List<Room> rooms = roomService.getAvailableRoomsByStarAsc();
                for (Room i : rooms) {
                    System.out.println("статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
                }
            };
            getAvailableRoomsByStarAscItem.setAction(getAvailableRoomsByStarAsc);
            menu.getMenuItems().add(getAvailableRoomsByStarAscItem);

            MenuItem getAvailableRoomsByStarDescItem = new MenuItem();
            getAvailableRoomsByStarDescItem.setTitle("21 getAvailableRoomsByStarDesc");
            IAction getAvailableRoomsByStarDesc = () -> {
                List<Room> rooms = roomService.getAvailableRoomsByStarDesc();
                for (Room i : rooms) {
                    System.out.println("статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
                }
            };
            getAvailableRoomsByStarDescItem.setAction(getAvailableRoomsByStarDesc);
            menu.getMenuItems().add(getAvailableRoomsByStarDescItem);

            MenuItem getOccupiedRoomsSortByDateDescItem = new MenuItem();
            getOccupiedRoomsSortByDateDescItem.setTitle("22 getOccupiedRoomsSortByDateDesc");
            IAction getOccupiedRoomsSortByDateDesc = () -> {
                List<Room> rooms = roomService.getOccupiedRoomsSortByDateDesc();
                for (Room i : rooms) {
                    System.out.println("дата " + i.getCheckInDate() + "статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
                }
            };
            getOccupiedRoomsSortByDateDescItem.setAction(getOccupiedRoomsSortByDateDesc);
            menu.getMenuItems().add(getOccupiedRoomsSortByDateDescItem);

            MenuItem getOccupiedRoomsSortByDateAscItem = new MenuItem();
            getOccupiedRoomsSortByDateAscItem.setTitle("23 getOccupiedRoomsSortByDateAsc");
            IAction getOccupiedRoomsSortByDateAsc = () -> {
                List<Room> rooms = roomService.getOccupiedRoomsSortByDateAsc();
                for (Room i : rooms) {
                    System.out.println("дата " + i.getCheckInDate() + "статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
                }
            };
            getOccupiedRoomsSortByDateAscItem.setAction(getOccupiedRoomsSortByDateAsc);
            menu.getMenuItems().add(getOccupiedRoomsSortByDateAscItem);

            MenuItem getFreeNumbersItem = new MenuItem();
            getFreeNumbersItem.setTitle("24 getFreeNumbers");
            IAction getFreeNumbers = () -> System.out.println(roomService.getFreeNumbers());
            getFreeNumbersItem.setAction(getFreeNumbers);
            menu.getMenuItems().add(getFreeNumbersItem);

            navigator.setCurrentMenu(menu);
            navigator.printMenu();
            System.out.println("Ведите нужную цифру");
            String b = scan.nextLine();
            navigator.navigate(Integer.valueOf(b));
        }
    }
}


