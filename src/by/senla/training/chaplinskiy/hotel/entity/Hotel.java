package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hotel {

    private List<Room> rooms;
    private List<Person> persons;
    private List<Supply> services;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Supply> getServices() {
        return services;
    }

}
