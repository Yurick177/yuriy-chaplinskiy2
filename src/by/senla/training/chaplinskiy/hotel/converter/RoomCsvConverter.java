package by.senla.training.chaplinskiy.hotel.converter;

import by.senla.training.chaplinskiy.hotel.entity.Room;
import by.senla.training.chaplinskiy.hotel.entity.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomCsvConverter implements CsvConverter<Room> {

    private static RoomCsvConverter roomCsvConverter;

    private RoomCsvConverter() {

    }

    public static RoomCsvConverter getRoomCsvConverter() {
        if (roomCsvConverter == null) {
            roomCsvConverter = new RoomCsvConverter();
        }
        return roomCsvConverter;
    }

    @Override
    public List<Room> getFromStrings(List<String> lines) {
        List<Room> roomsList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Room room = getRoomFromString(lines.get(i));
            roomsList.add(room);
        }
        return roomsList;
    }

    private Room getRoomFromString(String line) {
        String[] split = line.split(",");
        Long id = Objects.equals(split[0], "") ? null : Long.parseLong(split[0]);
        Status status = Status.valueOf(split[1]);
        int price = Integer.parseInt(split[2]);
        int star = Integer.parseInt(split[3]);
        int capacityRoom = Integer.parseInt(split[4]);
        return new Room(status, price, id, star, capacityRoom);
    }


    @Override
    public List<String> getStrings(List<Room> roomList) {
        List<String> lines = new ArrayList<>();
        lines.add("id,status,price,star,capacityRoom");
        for (Room room : roomList) {
            String line = room.getId() + "," + room.getStatus() + "," + room.getPrice() + "," + room.getStar() + "," + room.getCapacityRoom();
            lines.add(line);
        }
        return lines;
    }

}
