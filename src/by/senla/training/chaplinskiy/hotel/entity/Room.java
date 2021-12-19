package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private  Long id;
    private List<PersonHistory> personHistories;
    private LocalDateTime releaseDate;
    private LocalDateTime checkInDate;
    private int capacityRoom;
    private Integer star;
    private Status status;
    private int price;
    private Person person;
    private List<Supply> services;

    public Room(Status status, int price, Long id, int star, int capacityRoom) {
        this.status = status;
        this.id = id;
        this.price = price;
        this.services = new ArrayList<>();
        this.star = star;
        this.capacityRoom = capacityRoom;
        this.personHistories = new ArrayList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<PersonHistory> getPersonHistories() {
        return personHistories;
    }

    public void setPersonHistories(List<PersonHistory> personHistories) {
        this.personHistories = personHistories;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCapacityRoom(int capacityRoom) {
        this.capacityRoom = capacityRoom;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public int getCapacityRoom() {
        return capacityRoom;
    }

    public Integer getStar() {
        return star;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Supply> getServices() {
        return services;
    }

    public void setServices(List<Supply> services) {
        this.services = services;
    }

    public Person getPerson() {

        return person;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void changeStatusToOnService() {
        this.status = Status.ON_SERVICE;
    }

    public void changeStatusToAvailable() {
        this.status = Status.AVAILABLE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void addService(Supply service) {
        services.add(service);
    }

}
