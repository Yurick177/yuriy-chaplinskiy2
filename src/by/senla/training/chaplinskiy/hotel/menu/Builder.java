package by.senla.training.chaplinskiy.hotel.menu;

import by.senla.training.chaplinskiy.hotel.dto.PersonHistoryDto;
import by.senla.training.chaplinskiy.hotel.entity.Person;
import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.service.*;

import java.util.List;
import java.util.Scanner;

public class Builder {

    private static Builder builder;

    private Builder() {
    }

    public static Builder getBuilder() {
        if (builder == null) {
            builder = new Builder();
        }
        return builder;
    }

    private Menu rootMenu = new Menu();
    private final RoomService roomService = RoomServiceImpl.getRoomService();
    private final PersonService personService = PersonServiceImpl.getPersonService();
    private final SupplyService supplyService = SupplyServiceImpl.getSupplyService();
    private final PersonHistoryService personHistoryService = PersonHistoryServiceImpl.getPersonHistoryService();
    private final Scanner scan = new Scanner(System.in);

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {

        MenuItem roomMenuItem = getRoomMenuItem();
        roomMenuItem.setNextMenu(rootMenu);
        rootMenu.getMenuItems().add(roomMenuItem);

        MenuItem personMenuItem = getPersonMenuItem();
        personMenuItem.setNextMenu(rootMenu);
        rootMenu.getMenuItems().add(personMenuItem);

        MenuItem supplyMenuItem = getSupplyMenuItem();
        supplyMenuItem.setNextMenu(rootMenu);
        rootMenu.getMenuItems().add(supplyMenuItem);

        MenuItem personHistoryMenuItem = getPersonHistoryMenuItem();
        personHistoryMenuItem.setNextMenu(rootMenu);
        rootMenu.getMenuItems().add(personHistoryMenuItem);

        MenuItem exitMenuItem = new MenuItem();
        exitMenuItem.setTitle("5 Exit ");
        IAction exitMenu = () -> rootMenu = null;
        exitMenuItem.setAction(exitMenu);
        rootMenu.getMenuItems().add(exitMenuItem);
    }

    private MenuItem getSupplyMenuItem() {
        MenuItem supplyMenuItem = new MenuItem();
        supplyMenuItem.setTitle("3 Supply menu");
        Menu supplyMenu = new Menu();

        MenuItem getSuppliesSortedByPriceItem = new MenuItem();
        getSuppliesSortedByPriceItem.setTitle(" 1 Show supplies sorted by price ");
        IAction getSuppliesSortedByPrice = () -> {
            List<Supply> supply = supplyService.getSuppliesSortedByPrice();
            for (Supply i : supply) {
                System.out.println("цена " + i.getPrice());
            }
        };
        getSuppliesSortedByPriceItem.setAction(getSuppliesSortedByPrice);
        supplyMenu.getMenuItems().add(getSuppliesSortedByPriceItem);

        MenuItem getSuppliesSortedByTypeItem = new MenuItem();
        getSuppliesSortedByTypeItem.setTitle(" 2 Show supplies sorted by type ");
        IAction getSuppliesSortedByType = () -> {
            List<Supply> supply = supplyService.getSuppliesSortedByType();
            for (Supply i : supply) {
                System.out.println("тип " + i.getServiceType());
            }
        };
        getSuppliesSortedByTypeItem.setAction(getSuppliesSortedByType);
        supplyMenu.getMenuItems().add(getSuppliesSortedByTypeItem);

        MenuItem getAllSupplyItem = new MenuItem();
        getAllSupplyItem.setTitle(" 3 Show all supply ");
        IAction getAllSupply = () -> {
            List<Supply> supplies = supplyService.getAll();
            for (Supply i : supplies) {
                System.out.println("id " + i.getId() + " цена " + i.getPrice() + " тип " + i.getServiceType());
            }
        };
        getAllSupplyItem.setAction(getAllSupply);
        supplyMenu.getMenuItems().add(getAllSupplyItem);

        MenuItem addSupplyItem = new MenuItem();
        addSupplyItem.setTitle(" 4 Add supply ");
        IAction addSupply = () -> System.out.println(supplyService.addSupply(scan));
        addSupplyItem.setAction(addSupply);
        supplyMenu.getMenuItems().add(addSupplyItem);

        MenuItem updateSupplyItem = new MenuItem();
        updateSupplyItem.setTitle(" 5 Update supply ");
        IAction updateSupply = () -> supplyService.update(scan);
        updateSupplyItem.setAction(updateSupply);
        supplyMenu.getMenuItems().add(updateSupplyItem);

        MenuItem removeSupplyItem = new MenuItem();
        removeSupplyItem.setTitle(" 6 Remove supply ");
        IAction removeSupply = () -> supplyService.remove(scan);
        removeSupplyItem.setAction(removeSupply);
        supplyMenu.getMenuItems().add(removeSupplyItem);

        MenuItem getByIdSupplyItem = new MenuItem();
        getByIdSupplyItem.setTitle(" 7 Show by id supply ");
        IAction getByIdSupply = () -> {
            Supply supply = supplyService.getById(scan);
            if (supply != null) {
                System.out.println(supply.getId() + " " + supply.getPrice() + " " + supply.getServiceType());
            } else {
                System.out.println("услугу по такому id не нашли");
            }
        };
        getByIdSupplyItem.setAction(getByIdSupply);
        supplyMenu.getMenuItems().add(getByIdSupplyItem);

        MenuItem exportFileItem = new MenuItem();
        exportFileItem.setTitle(" 8 exportFile ");
        IAction exportFile = supplyService::exportFile;
        exportFileItem.setAction(exportFile);
        supplyMenu.getMenuItems().add(exportFileItem);

        MenuItem importFromFileItem = new MenuItem();
        importFromFileItem.setTitle(" 9 importFromFile ");
        IAction importFromFile = supplyService::importFromFile;
        importFromFileItem.setAction(importFromFile);
        supplyMenu.getMenuItems().add(importFromFileItem);

        MenuItem rollBackSupplyItem = new MenuItem();
        supplyMenu.getMenuItems().add(rollBackSupplyItem);
        rollBackSupplyItem.setTitle(" 10 вернуться ");
        IAction rollBackSupply = () -> rootMenu = supplyMenuItem.getNextMenu();
        rollBackSupplyItem.setAction(rollBackSupply);

        supplyMenu.getMenuItems().add(supplyMenuItem);
        IAction supplyMenuAction = () -> rootMenu = supplyMenu;
        supplyMenuItem.setAction(supplyMenuAction);
        return supplyMenuItem;
    }

    private MenuItem getPersonMenuItem() {
        MenuItem personMenuItem = new MenuItem();
        personMenuItem.setNextMenu(rootMenu);
        personMenuItem.setTitle("2 Person menu");
        Menu personMenu = new Menu();

        MenuItem createPersonItem = new MenuItem();
        createPersonItem.setTitle(" 1 Create person ");
        IAction createPerson = () -> {
            System.out.println("Create person");
            Long id = personService.createPerson(scan);
            System.out.println("Person id is " + id);
        };
        createPersonItem.setAction(createPerson);
        personMenu.getMenuItems().add(createPersonItem);

        MenuItem checkInPersonItem = new MenuItem();
        checkInPersonItem.setTitle(" 2 Check in person ");
        IAction checkInPerson = () -> {
            Long roomId = personService.checkInPerson(scan);
            if (roomId != null) {
                System.out.println("человек заселен в комнату под номером " + roomId);
            } else {
                System.out.println("свободных комнат нет");
            }
        };
        checkInPersonItem.setAction(checkInPerson);
        personMenu.getMenuItems().add(checkInPersonItem);

        MenuItem checkOutPersonItem = new MenuItem();
        checkOutPersonItem.setTitle(" 3 Check out person ");
        IAction checkOutPerson = () -> personService.checkOutPerson(scan);
        checkOutPersonItem.setAction(checkOutPerson);
        personMenu.getMenuItems().add(checkOutPersonItem);

        MenuItem sortAbsItem = new MenuItem();
        sortAbsItem.setTitle(" 4 Sort person by abs ");
        IAction sortAbc = () -> {
            List<Person> persons = personService.sortAbs();
            for (Person i : persons) {
                System.out.println(i.getId() + " " + i.getName() + " " + i.getLastName() + " " + i.getAge());
            }
        };
        sortAbsItem.setAction(sortAbc);
        personMenu.getMenuItems().add(sortAbsItem);

        MenuItem getNumberGuestsItem = new MenuItem();
        getNumberGuestsItem.setTitle(" 5 Show list guests ");
        IAction getNumberGuest = () -> System.out.println(personService.getNumberGuests());
        getNumberGuestsItem.setAction(getNumberGuest);
        personMenu.getMenuItems().add(getNumberGuestsItem);

        MenuItem getTotalPriceItem = new MenuItem();
        getTotalPriceItem.setTitle(" 6 Show total price ");
        IAction getTotalPrice = () -> System.out.println(personService.getTotalPrice(scan));
        getTotalPriceItem.setAction(getTotalPrice);
        personMenu.getMenuItems().add(getTotalPriceItem);

        MenuItem importPersonFromFileItem = new MenuItem();
        importPersonFromFileItem.setTitle(" 7 ImportPersonFromFile");
        IAction importPersonFromFile = personService::importFromFile;
        importPersonFromFileItem.setAction(importPersonFromFile);
        personMenu.getMenuItems().add(importPersonFromFileItem);

        MenuItem exportFileItem = new MenuItem();
        exportFileItem.setTitle(" 8 ExportFile");
        IAction exportFile = personService::exportFile;
        exportFileItem.setAction(exportFile);
        personMenu.getMenuItems().add(exportFileItem);

        MenuItem rollBackPersonItem = new MenuItem();
        personMenu.getMenuItems().add(rollBackPersonItem);
        rollBackPersonItem.setTitle(" 9 вернуться ");
        IAction rollBackPerson = () -> rootMenu = personMenuItem.getNextMenu();
        rollBackPersonItem.setAction(rollBackPerson);

        personMenu.getMenuItems().add(personMenuItem);
        IAction personMenuAction = () -> rootMenu = personMenu;
        personMenuItem.setAction(personMenuAction);
        return personMenuItem;
    }

    private MenuItem getRoomMenuItem() {
        MenuItem roomMenuItem = new MenuItem();
        roomMenuItem.setNextMenu(rootMenu);
        roomMenuItem.setTitle("1 Room menu");
        Menu roomMenu = new Menu();

        MenuItem getRoomsItem = new MenuItem();
        getRoomsItem.setTitle(" 1 show all rooms");
        IAction getRoom = () -> {
            List<Room> rooms = roomService.getRooms();
            for (Room i : rooms) {
                System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsItem.setAction(getRoom);
        roomMenu.getMenuItems().add(getRoomsItem);

        MenuItem addRoomItem = new MenuItem();
        addRoomItem.setTitle(" 2 Add room ");
        IAction addRoom = () -> roomService.createRoom(scan);
        addRoomItem.setAction(addRoom);
        roomMenu.getMenuItems().add(addRoomItem);

        MenuItem getRoomsByPriceAscItem = new MenuItem();
        getRoomsByPriceAscItem.setTitle(" 3 Show list rooms sort by price asc ");
        IAction getRoomsByPriceAsc = () -> {
            List<Room> rooms = roomService.getRoomsByPriceAsc();
            for (Room i : rooms) {
                System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByPriceAscItem.setAction(getRoomsByPriceAsc);
        roomMenu.getMenuItems().add(getRoomsByPriceAscItem);

        MenuItem getRoomsByPriceDescItem = new MenuItem();
        getRoomsByPriceDescItem.setTitle(" 4 Show list rooms sort by price desc ");
        IAction getRoomsByPriceDesc = () -> {
            List<Room> rooms = roomService.getRoomsByPriceDesc();
            for (Room i : rooms) {
                System.out.println("цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByPriceDescItem.setAction(getRoomsByPriceDesc);
        roomMenu.getMenuItems().add(getRoomsByPriceDescItem);

        MenuItem getRoomsByStarAscItem = new MenuItem();
        getRoomsByStarAscItem.setTitle(" 5 Show list rooms sort by star asc ");
        IAction getRoomsByStarAsc = () -> {
            List<Room> rooms = roomService.getRoomsByStarAsc();
            for (Room i : rooms) {
                System.out.println(" количество звезд " + i.getStar() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByStarAscItem.setAction(getRoomsByStarAsc);
        roomMenu.getMenuItems().add(getRoomsByStarAscItem);

        MenuItem getRoomsByStarDescItem = new MenuItem();
        getRoomsByStarDescItem.setTitle(" 6 Show list rooms sort by star desc ");
        IAction getRoomsByStarDesc = () -> {
            List<Room> rooms = roomService.getRoomsByStarDesc();
            for (Room i : rooms) {
                System.out.println("количество звезд " + i.getStar() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " вместимость " + i.getCapacityRoom() + " статус " + i.getStatus());
            }
        };
        getRoomsByStarDescItem.setAction(getRoomsByStarDesc);
        roomMenu.getMenuItems().add(getRoomsByStarDescItem);

        MenuItem getRoomsByCapacityRoomAscItem = new MenuItem();
        getRoomsByCapacityRoomAscItem.setTitle(" 7 Show list rooms sort by capacity asc ");
        IAction getRoomsByCapacityRoomAsc = () -> {
            List<Room> rooms = roomService.getRoomsByCapacityRoomAsc();
            for (Room i : rooms) {
                System.out.println("вместимость " + i.getCapacityRoom() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " статус " + i.getStatus());
            }
        };
        getRoomsByCapacityRoomAscItem.setAction(getRoomsByCapacityRoomAsc);
        roomMenu.getMenuItems().add(getRoomsByCapacityRoomAscItem);

        MenuItem getRoomsByCapacityRoomDescItem = new MenuItem();
        getRoomsByCapacityRoomDescItem.setTitle(" 8 Show list rooms sort by capacity desc ");
        IAction getRoomsByCapacityRoomDesc = () -> {
            List<Room> rooms = roomService.getRoomsByCapacityRoomDesc();
            for (Room i : rooms) {
                System.out.println("вместимость " + i.getCapacityRoom() + " цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar() + " статус " + i.getStatus());
            }
        };
        getRoomsByCapacityRoomDescItem.setAction(getRoomsByCapacityRoomDesc);
        roomMenu.getMenuItems().add(getRoomsByCapacityRoomDescItem);

        MenuItem getPersonHistoryByRoomItem = new MenuItem();
        getPersonHistoryByRoomItem.setTitle(" 9 Show person history by room id ");
        IAction getPersonHistoryByRoomId = () -> {
            List<PersonHistoryDto> personHistoriesByRoomId = personHistoryService.getPersonHistoriesByRoomId(scan);
            for (PersonHistoryDto i : personHistoriesByRoomId) {
                System.out.println(i.getPersonFirstName() + " " + i.getPersonLastName() + " " + i.getCheckInDate() + " " + i.getReleaseDate());
            }
        };
        getPersonHistoryByRoomItem.setAction(getPersonHistoryByRoomId);
        roomMenu.getMenuItems().add(getPersonHistoryByRoomItem);

        MenuItem getAvailableRoomsByPriceAscItem = new MenuItem();
        getAvailableRoomsByPriceAscItem.setTitle(" 10 Show available rooms by price asc ");
        IAction getAvailableRoomsByPriceAsc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByPriceAsc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByPriceAscItem.setAction(getAvailableRoomsByPriceAsc);
        roomMenu.getMenuItems().add(getAvailableRoomsByPriceAscItem);

        MenuItem getAvailableRoomsByPriceDescItem = new MenuItem();
        getAvailableRoomsByPriceDescItem.setTitle(" 11 Show available rooms by price desc ");
        IAction getAvailableRoomsByPriceDesc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByPriceDesc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByPriceDescItem.setAction(getAvailableRoomsByPriceDesc);
        roomMenu.getMenuItems().add(getAvailableRoomsByPriceDescItem);

        MenuItem getAvailableRoomsByCapacityAscItem = new MenuItem();
        getAvailableRoomsByCapacityAscItem.setTitle(" 12 Show available rooms by capacity asc ");
        IAction getAvailableRoomsByCapacityAsc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByCapacityAsc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " вместимость " + i.getCapacityRoom() + "цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByCapacityAscItem.setAction(getAvailableRoomsByCapacityAsc);
        roomMenu.getMenuItems().add(getAvailableRoomsByCapacityAscItem);

        MenuItem getAvailableRoomsByCapacityDescItem = new MenuItem();
        getAvailableRoomsByCapacityDescItem.setTitle(" 13 Show available rooms by capacity desc ");
        IAction getAvailableRoomsByCapacityDesc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByCapacityDesc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " вместимость " + i.getCapacityRoom() + "цена номера " + i.getPrice() + " номер комнаты " + i.getId() + " количество звезд " + i.getStar());
            }
        };
        getAvailableRoomsByCapacityDescItem.setAction(getAvailableRoomsByCapacityDesc);
        roomMenu.getMenuItems().add(getAvailableRoomsByCapacityDescItem);

        MenuItem getAvailableRoomsByStarAscItem = new MenuItem();
        getAvailableRoomsByStarAscItem.setTitle(" 14 Show available rooms by star asc ");
        IAction getAvailableRoomsByStarAsc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByStarAsc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getAvailableRoomsByStarAscItem.setAction(getAvailableRoomsByStarAsc);
        roomMenu.getMenuItems().add(getAvailableRoomsByStarAscItem);

        MenuItem getAvailableRoomsByStarDescItem = new MenuItem();
        getAvailableRoomsByStarDescItem.setTitle(" 15 Show available rooms by star desc ");
        IAction getAvailableRoomsByStarDesc = () -> {
            List<Room> rooms = roomService.getAvailableRoomsByStarDesc();
            for (Room i : rooms) {
                System.out.println("статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getAvailableRoomsByStarDescItem.setAction(getAvailableRoomsByStarDesc);
        roomMenu.getMenuItems().add(getAvailableRoomsByStarDescItem);

        MenuItem getOccupiedRoomsSortByDateDescItem = new MenuItem();
        getOccupiedRoomsSortByDateDescItem.setTitle(" 16 Show occupied rooms sort By date desc ");
        IAction getOccupiedRoomsSortByDateDesc = () -> {
            List<Room> rooms = roomService.getOccupiedRoomsSortByDateDesc();
            if (!rooms.isEmpty()) {
                for (Room i : rooms) {
                    System.out.println("дата " + i.getCheckInDate() + "статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
                }
            } else {
                System.out.println("занятых комнат нет");
            }
        };
        getOccupiedRoomsSortByDateDescItem.setAction(getOccupiedRoomsSortByDateDesc);
        roomMenu.getMenuItems().add(getOccupiedRoomsSortByDateDescItem);

        MenuItem getOccupiedRoomsSortByDateAscItem = new MenuItem();
        getOccupiedRoomsSortByDateAscItem.setTitle(" 17 Show occupied rooms sort By date desc ");
        IAction getOccupiedRoomsSortByDateAsc = () -> {
            List<Room> rooms = roomService.getOccupiedRoomsSortByDateAsc();
            for (Room i : rooms) {
                System.out.println("дата " + i.getCheckInDate() + "статус " + i.getStatus() + " количество звезд " + i.getStar() + "цена номера " + i.getPrice() + " вместимость " + i.getCapacityRoom() + " номер комнаты " + i.getId());
            }
        };
        getOccupiedRoomsSortByDateAscItem.setAction(getOccupiedRoomsSortByDateAsc);
        roomMenu.getMenuItems().add(getOccupiedRoomsSortByDateAscItem);

        MenuItem getAvailableRoomsByDateItem = new MenuItem();
        getAvailableRoomsByDateItem.setTitle(" 18 Show available rooms by date ");
        IAction getAvailableRoomsByDate = () -> roomService.getAvailableRoomsByDate(scan);
        getAvailableRoomsByDateItem.setAction(getAvailableRoomsByDate);
        roomMenu.getMenuItems().add(getAvailableRoomsByDateItem);

        MenuItem getFreeNumbersItem = new MenuItem();
        getFreeNumbersItem.setTitle(" 19 Show list free numbers ");
        IAction getFreeNumbers = () -> System.out.println(roomService.getFreeNumbers());
        getFreeNumbersItem.setAction(getFreeNumbers);
        roomMenu.getMenuItems().add(getFreeNumbersItem);

        MenuItem exportFileItem = new MenuItem();
        exportFileItem.setTitle(" 20 exportFile ");
        IAction exportFile = roomService::exportFile;
        exportFileItem.setAction(exportFile);
        roomMenu.getMenuItems().add(exportFileItem);

        MenuItem importFromFileItem = new MenuItem();
        importFromFileItem.setTitle(" 21 importFromFile ");
        IAction importFromFile = roomService::importFromFile;
        importFromFileItem.setAction(importFromFile);
        roomMenu.getMenuItems().add(importFromFileItem);

        MenuItem rollBackItem = new MenuItem();
        roomMenu.getMenuItems().add(rollBackItem);
        rollBackItem.setTitle(" 22 вернуться ");
        IAction rollBack = () -> rootMenu = roomMenuItem.getNextMenu();
        rollBackItem.setAction(rollBack);

        roomMenuItem.setNextMenu(roomMenu);
        IAction roomMenuAction = () -> rootMenu = roomMenu;
        roomMenuItem.setAction(roomMenuAction);
        return roomMenuItem;

    }

    private MenuItem getPersonHistoryMenuItem() {
        MenuItem personHistoryMenuItem = new MenuItem();
        personHistoryMenuItem.setNextMenu(rootMenu);
        personHistoryMenuItem.setTitle("4 PersonHistoryMenu");
        Menu personHistoryMenu = new Menu();

        MenuItem personHistoryExportItem = new MenuItem();
        personHistoryExportItem.setTitle(" 1 Export ");
        IAction personHistoryExport = personHistoryService::exportFile;
        personHistoryExportItem.setAction(personHistoryExport);
        personHistoryMenu.getMenuItems().add(personHistoryExportItem);

        MenuItem rollBackItem = new MenuItem();
        personHistoryMenu.getMenuItems().add(rollBackItem);
        rollBackItem.setTitle(" 2 вернуться ");
        IAction rollBack = () -> rootMenu = personHistoryMenuItem.getNextMenu();
        rollBackItem.setAction(rollBack);

        personHistoryMenuItem.setNextMenu(personHistoryMenu);
        IAction roomMenuAction = () -> rootMenu = personHistoryMenu;
        personHistoryMenuItem.setAction(roomMenuAction);
        return personHistoryMenuItem;
    }

}
