package by.senla.training.chaplinskiy.hotel.service;

import by.senla.training.chaplinskiy.hotel.entity.Room;

import java.util.Scanner;

public interface HotelService {

    void addRoom(Room room);

    Long checkInPerson(Scanner scanner);

    void checkOutPerson(Scanner scanner);

}
