package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Room {

    private final Long id;
    private List<PersonHistory> personHistories;
    private LocalDateTime releaseDate;
    private LocalDateTime checkInDate;
    private int capacityRoom;
    private int star;
    private Status status;
    private int price;
    private Person person;
    private List<Supply> services;

    public Room(Status status, int price, long id, int star, int capacityRoom) {
        this.status = status;
        this.id = id;
        this.price = price;
        this.services = new ArrayList<>();
        this.star = star;
        this.capacityRoom = capacityRoom;
        this.personHistories = new ArrayList<>();
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

    public int getStar() {
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

    public List<PersonHistory> getPersonHistories(int lastNumber) {
        int personHistorySize = personHistories.size();
        if (personHistorySize < lastNumber) {
            return personHistories;
        } else {
            return personHistories.subList(personHistorySize - lastNumber - 1, personHistorySize - 1);
        }
    }

    public List<Supply> getListService() {
        return services;

    }

    public List<Supply> getListServiceSortByPriceAsc() {
        Comparator<Supply> comparator = (o1, o2) -> o1.getPrice() > o2.getPrice() ? 1 : -1;
        services.sort(comparator);
        return services;
    }

    public List<Supply> getListServiceSortByPriceDesc() {
        Comparator<Supply> comparator = (o1, o2) -> o1.getPrice() < o2.getPrice() ? 1 : -1;
        services.sort(comparator);
        return services;
    }

    public List<Supply> getListServiceSortByDateAsc() {
        Comparator<Supply> comparator = Comparator.comparing(Supply::getServiceDateTime);
        services.sort(comparator);
        return services;
    }

    public List<Supply> getListServiceSortByDateDesc() {
        Comparator<Supply> comparator = (o1, o2) -> o1.getServiceDateTime().compareTo(o2.getServiceDateTime()) * -1;
        services.sort(comparator);
        return services;
    }

}
