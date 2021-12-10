package by.senla.training.chaplinskiy.hotel.menu;

import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.PersonHistory;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.service.*;

import java.util.List;
import java.util.Scanner;

public class Builder {

    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        RoomService roomService = RoomServiceImpl.getRoomService();
        PersonService personService = PersonServiceImpl.getPersonService();
        SupplyService supplyService = SupplyServiceImpl.getSupplyService();
        Scanner scan = new Scanner(System.in);
        rootMenu = new Menu();

        MenuItem getRoomsItem = new MenuItem();
        setTitleToMenu(" Show all rooms ", getRoomsItem);
        IAction getRoom = () -> {
            List<Room> rooms = roomService.getRooms();
            for (Room i : rooms) {
                System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsItem.setAction(getRoom);
        rootMenu.getMenuItems().add(getRoomsItem);

        MenuItem addRoomItem = new MenuItem();
        setTitleToMenu(" add room ", addRoomItem);
        IAction addRoom = () -> {
            roomService.createRoom(scan);
        };
        addRoomItem.setAction(addRoom);
        rootMenu.getMenuItems().add(addRoomItem);

        MenuItem createPersonItem = new MenuItem();
        setTitleToMenu(" create person ", createPersonItem);
        IAction createPerson = () -> {
            System.out.println("create person");
            Long id = personService.createPerson(scan);
            System.out.println("person id is " + id);
        };
        createPersonItem.setAction(createPerson);
        rootMenu.getMenuItems().add(createPersonItem);

        MenuItem checkInPersonItem = new MenuItem();
        setTitleToMenu(" check in person ", checkInPersonItem);
        IAction checkInPerson = () -> {
            Long roomId = personService.checkInPerson(scan);
            if (roomId != null) {
                System.out.println("человек заселен в комнату под номером " + roomId);
            } else {
                System.out.println("свободных комнат нет");
            }
        };
        checkInPersonItem.setAction(checkInPerson);
        rootMenu.getMenuItems().add(checkInPersonItem);

        MenuItem checkOutPersonItem = new MenuItem();
        setTitleToMenu(" check out person ", checkOutPersonItem);
        IAction checkOutPerson = () -> personService.checkOutPerson(scan);
        checkOutPersonItem.setAction(checkOutPerson);
        rootMenu.getMenuItems().add(checkOutPersonItem);

        MenuItem sortAbsItem = new MenuItem();
        setTitleToMenu(" sort person by abs ", sortAbsItem);
        IAction sortAbc = () -> {
            List<Person> persons = personService.sortAbs();
            for (Person i : persons) {
                System.out.println(i.getId() + " " + i.getName() + " " + i.getLastName() + " " + i.getAge());
            }
        };
        sortAbsItem.setAction(sortAbc);
        rootMenu.getMenuItems().add(sortAbsItem);

        MenuItem getNumberGuestsItem = new MenuItem();
        setTitleToMenu(" show list guests ", getNumberGuestsItem);
        IAction getNumberGuest = () -> System.out.println(personService.getNumberGuests());
        getNumberGuestsItem.setAction(getNumberGuest);
        rootMenu.getMenuItems().add(getNumberGuestsItem);

        MenuItem getTotalPriceItem = new MenuItem();
        setTitleToMenu(" show total price ", getTotalPriceItem);
        IAction getTotalPrice = () -> System.out.println(personService.getTotalPrice(scan));
        getTotalPriceItem.setAction(getTotalPrice);
        rootMenu.getMenuItems().add(getTotalPriceItem);

        MenuItem getRoomsByPriceAscItem = new MenuItem();
        setTitleToMenu(" show list rooms sort by price asc ", getRoomsByPriceAscItem);
        IAction getRoomsByPriceAsc = () -> {
            List<Room> rooms = roomService.getRoomsByPriceAsc();
            for (Room i : rooms) {
                System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByPriceAscItem.setAction(getRoomsByPriceAsc);
        rootMenu.getMenuItems().add(getRoomsByPriceAscItem);

        MenuItem getRoomsByPriceDescItem = new MenuItem();
        setTitleToMenu(" show list rooms sort by price desc ", getRoomsByPriceDescItem);
        IAction getRoomsByPriceDesc = () -> {
            List<Room> rooms = roomService.getRoomsByPriceDesc();
            for (Room i : rooms) {
                System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByPriceDescItem.setAction(getRoomsByPriceDesc);
        rootMenu.getMenuItems().add(getRoomsByPriceDescItem);

        MenuItem getRoomsByStarAscItem = new MenuItem();
        setTitleToMenu(" show list rooms sort by star asc ", getRoomsByStarAscItem);
        IAction getRoomsByStarAsc = () -> {
            List<Room> rooms = roomService.getRoomsByStarAsc();
            for (Room i : rooms) {
                System.out.println(" количество звезд " + i.getStar() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByStarAscItem.setAction(getRoomsByStarAsc);
        rootMenu.getMenuItems().add(getRoomsByStarAscItem);

        MenuItem getRoomsByStarDescItem = new MenuItem();
        setTitleToMenu(" show list rooms sort by star desc ", getRoomsByStarDescItem);
        IAction getRoomsByStarDesc = () -> {
            List<Room> rooms = roomService.getRoomsByStarDesc();
            for (Room i : rooms) {
                System.out.println("количество звезд " + i.getStar() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByStarDescItem.setAction(getRoomsByStarDesc);
        rootMenu.getMenuItems().add(getRoomsByStarDescItem);

        MenuItem getRoomsByCapacityRoomAscItem = new MenuItem();
        setTitleToMenu(" show list rooms sort by capacity asc ", getRoomsByCapacityRoomAscItem);
        IAction getRoomsByCapacityRoomAsc = () -> {
            List<Room> rooms = roomService.getRoomsByCapacityRoomAsc();
            for (Room i : rooms) {
                System.out.println("вместимость " + i.getCapacityRoom() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " статус " + i.getStatus());
            }
        };
        getRoomsByCapacityRoomAscItem.setAction(getRoomsByCapacityRoomAsc);
        rootMenu.getMenuItems().add(getRoomsByCapacityRoomAscItem);

        MenuItem getRoomsByCapacityRoomDescItem = new MenuItem();
        setTitleToMenu(" show list rooms sort by capacity desc ", getRoomsByCapacityRoomDescItem);
        IAction getRoomsByCapacityRoomDesc = () -> {
            List<Room> rooms = roomService.getRoomsByCapacityRoomDesc();
            for (Room i : rooms) {
                System.out.println("вместимость " + i.getCapacityRoom() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " статус " + i.getStatus());
            }
        };
        getRoomsByCapacityRoomDescItem.setAction(getRoomsByCapacityRoomDesc);
        rootMenu.getMenuItems().add(getRoomsByCapacityRoomDescItem);


        MenuItem getPersonHistoryByRoomItem = new MenuItem();
        setTitleToMenu(" show person history by room id ", getPersonHistoryByRoomItem);
        IAction getPersonHistoryByRoomId = () -> {
            List<PersonHistory> personHistoriesByRoomId = roomService.getPersonHistoriesByRoomId(scan);
            for (PersonHistory i : personHistoriesByRoomId) {
                System.out.println(i.getPerson().getName() + " " + i.getPerson().getLastName() + " " + i.getCheckInDate() + " " + i.getReleaseDate());
            }
        };
        getPersonHistoryByRoomItem.setAction(getPersonHistoryByRoomId);
        rootMenu.getMenuItems().add(getPersonHistoryByRoomItem);

        MenuItem getAvailableRoomsByPriceAscItem = new MenuItem();
        setTitleToMenu(" show available rooms by price asc ", getAvailableRoomsByPriceAscItem);
        IAction getAvailableRoomsByPriceAsc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByPriceAsc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByPriceAscItem.setAction(getAvailableRoomsByPriceAsc);
        rootMenu.getMenuItems().add(getAvailableRoomsByPriceAscItem);

        MenuItem getAvailableRoomsByPriceDescItem = new MenuItem();
        setTitleToMenu(" show available rooms by price desc ", getAvailableRoomsByPriceDescItem);
        IAction getAvailableRoomsByPriceDesc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByPriceDesc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByPriceDescItem.setAction(getAvailableRoomsByPriceDesc);
        rootMenu.getMenuItems().add(getAvailableRoomsByPriceDescItem);

        MenuItem getAvailableRoomsByCapacityAscItem = new MenuItem();
        setTitleToMenu(" show available rooms by capacity asc ", getAvailableRoomsByCapacityAscItem);
        IAction getAvailableRoomsByCapacityAsc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByCapacityAsc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " вместимость " + i.getCapacityRoom() + "цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByCapacityAscItem.setAction(getAvailableRoomsByCapacityAsc);
        rootMenu.getMenuItems().add(getAvailableRoomsByCapacityAscItem);

        MenuItem getAvailableRoomsByCapacityDescItem = new MenuItem();
        setTitleToMenu(" show available rooms by capacity desc ", getAvailableRoomsByCapacityDescItem);
        IAction getAvailableRoomsByCapacityDesc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByCapacityDesc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " вместимость " + i.getCapacityRoom() + "цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByCapacityDescItem.setAction(getAvailableRoomsByCapacityDesc);
        rootMenu.getMenuItems().add(getAvailableRoomsByCapacityDescItem);

        MenuItem getAvailableRoomsByStarAscItem = new MenuItem();
        setTitleToMenu(" show available rooms by star asc ", getAvailableRoomsByStarAscItem);
        IAction getAvailableRoomsByStarAsc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByStarAsc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getAvailableRoomsByStarAscItem.setAction(getAvailableRoomsByStarAsc);
        rootMenu.getMenuItems().add(getAvailableRoomsByStarAscItem);

        MenuItem getAvailableRoomsByStarDescItem = new MenuItem();
        setTitleToMenu(" show available rooms by star desc ", getAvailableRoomsByStarDescItem);
        IAction getAvailableRoomsByStarDesc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByStarDesc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getAvailableRoomsByStarDescItem.setAction(getAvailableRoomsByStarDesc);
        rootMenu.getMenuItems().add(getAvailableRoomsByStarDescItem);

        MenuItem getOccupiedRoomsSortByDateDescItem = new MenuItem();
        setTitleToMenu(" show occupied rooms sort By date desc ", getOccupiedRoomsSortByDateDescItem);
        IAction getOccupiedRoomsSortByDateDesc = () -> {
            List<Room> rooms = roomService.getOccupiedRoomsSortByDateDesc();
            for (Room i : rooms) {
                System.out.println("дата " + i.getCheckInDate() + "статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getOccupiedRoomsSortByDateDescItem.setAction(getOccupiedRoomsSortByDateDesc);
        rootMenu.getMenuItems().add(getOccupiedRoomsSortByDateDescItem);

        MenuItem getOccupiedRoomsSortByDateAscItem = new MenuItem();
        setTitleToMenu(" show occupied rooms sort By date desc ", getOccupiedRoomsSortByDateAscItem);
        IAction getOccupiedRoomsSortByDateAsc = () -> {
            List<Room> rooms = roomService.getOccupiedRoomsSortByDateAsc();
            for (Room i : rooms) {
                System.out.println("дата " + i.getCheckInDate() + "статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getOccupiedRoomsSortByDateAscItem.setAction(getOccupiedRoomsSortByDateAsc);
        rootMenu.getMenuItems().add(getOccupiedRoomsSortByDateAscItem);

        MenuItem getAvailableRoomsByDateItem = new MenuItem();
        setTitleToMenu(" show available rooms by date ", getAvailableRoomsByDateItem);
        IAction getAvailableRoomsByDate = () -> roomService.getAvailableRoomsByDate(scan);
        getAvailableRoomsByDateItem.setAction(getAvailableRoomsByDate);
        rootMenu.getMenuItems().add(getAvailableRoomsByDateItem);

        MenuItem getFreeNumbersItem = new MenuItem();
        setTitleToMenu(" show list free numbers ", getFreeNumbersItem);
        IAction getFreeNumbers = () -> System.out.println(roomService.getFreeNumbers());
        getFreeNumbersItem.setAction(getFreeNumbers);
        rootMenu.getMenuItems().add(getFreeNumbersItem);

        MenuItem getSuppliesSortedByPriceItem = new MenuItem();
        setTitleToMenu(" show supplies sorted by price ", getSuppliesSortedByPriceItem);
        IAction getSuppliesSortedByPrice = () -> {
            List<Supply> supply = supplyService.getSuppliesSortedByPrice();
            for (Supply i : supply) {
                System.out.println("цена " + i.getPrice());
            }
        };
        getSuppliesSortedByPriceItem.setAction(getSuppliesSortedByPrice);
        rootMenu.getMenuItems().add(getSuppliesSortedByPriceItem);

        MenuItem getSuppliesSortedByTypeItem = new MenuItem();
        setTitleToMenu(" show supplies sorted by type ", getSuppliesSortedByTypeItem);
        IAction getSuppliesSortedByType = () -> {
            List<Supply> supply = supplyService.getSuppliesSortedByType();
            for (Supply i : supply) {
                System.out.println("тип " + i.getServiceType());
            }
        };
        getSuppliesSortedByTypeItem.setAction(getSuppliesSortedByType);
        rootMenu.getMenuItems().add(getSuppliesSortedByTypeItem);

        MenuItem getAllSupplyItem = new MenuItem();
        setTitleToMenu(" show all supply ", getAllSupplyItem);
        IAction getAllSupply = () -> {
            List<Supply> supplies = supplyService.getAll();
            for (Supply i : supplies) {
                System.out.println("id " + i.getId() + " цена " + i.getPrice() + " тип " + i.getServiceType());
            }
        };
        getAllSupplyItem.setAction(getAllSupply);
        rootMenu.getMenuItems().add(getAllSupplyItem);

        MenuItem addSupplyItem = new MenuItem();
        setTitleToMenu(" add supply ", addSupplyItem);
        IAction addSupply = () -> System.out.println(supplyService.addSupply(scan));
        addSupplyItem.setAction(addSupply);
        rootMenu.getMenuItems().add(addSupplyItem);

        MenuItem updateSupplyItem = new MenuItem();
        setTitleToMenu(" update supply ", updateSupplyItem);
        IAction updateSupply = () -> supplyService.update(scan);
        updateSupplyItem.setAction(updateSupply);
        rootMenu.getMenuItems().add(updateSupplyItem);

        MenuItem removeSupplyItem = new MenuItem();
        setTitleToMenu(" remove supply ", removeSupplyItem);
        IAction removeSupply = () -> supplyService.remove(scan);
        removeSupplyItem.setAction(removeSupply);
        rootMenu.getMenuItems().add(removeSupplyItem);

        MenuItem getByIdSupplyItem = new MenuItem();
        setTitleToMenu(" show by id supply ", getByIdSupplyItem);
        IAction getByIdSupply = () -> {
            Supply supply = supplyService.getById(scan);
            if (supply != null) {
                System.out.println(supply.getId() + " " + supply.getPrice() + " " + supply.getServiceType());
            } else {
                System.out.println("услугу по такому id не нашли");
            }
        };
        getByIdSupplyItem.setAction(getByIdSupply);
        rootMenu.getMenuItems().add(getByIdSupplyItem);

    }

    public void setTitleToMenu(String titleToMenu, MenuItem menuItem) {
        int menuItemNumber = rootMenu.getMenuItems().size() + 1;
        menuItem.setTitle(menuItemNumber + titleToMenu);
    }

}
