package by.senla.training.chaplinskiy.adminHotel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Room {

    private List<PersonHistory> personHistories;
    private LocalDateTime releaseDate;
    private LocalDateTime checkInDate;
    private int capacityRoom;
    private int star;
    private final long id;
    private Status status;
    private int price;
    private Person person;
    private List<Service> services;

    public Room(Status status, int price, long id, int star, int capacityRoom) {
        this.status = status;
        this.id = id;
        this.price = price;
        this.services = new ArrayList<>();
        this.star = star;
        this.capacityRoom = capacityRoom;
        this.personHistories = new ArrayList<>();
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

    public long getId() {
        return id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Person getPerson() {
        return person;
    }

    public void addPerson(Person person, LocalDateTime checkInDate, LocalDateTime releaseDate) {
        this.person = person;
        this.status = Status.OCСUPIED;
        this.releaseDate = releaseDate;
        this.checkInDate = checkInDate;
        PersonHistory personHistory = new PersonHistory(person, releaseDate, this.checkInDate);
        this.personHistories.add(personHistory);
    }

    public void removePerson() {
        this.person = null;
        this.status = Status.AVAILABLE;
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

    public void addService(Service service) {
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

    public List<Service> getListService(){
        return services;

    }

    public List<Service> getListServiceSortByPriceAsc(){
        Comparator<Service> comparator = new Comparator<Service>() {
            @Override
            public int compare(Service o1, Service o2) {
                return o1.getPrice() > o2.getPrice()  ? 1 : -1;
            }
        };
        services.sort(comparator);
        return services;
    }

    public List<Service> getListServiceSortByPriceDesc(){
        Comparator<Service> comparator = new Comparator<Service>() {
            @Override
            public int compare(Service o1, Service o2) {
                return o1.getPrice() < o2.getPrice()  ? 1 : -1;
            }
        };
        services.sort(comparator);
        return services;
    }

    public List<Service> getListServiceSortByDateAsc(){
        Comparator<Service> comparator = new Comparator<Service>() {
            @Override
            public int compare(Service o1, Service o2) {
                return o1.getServiceDateTime().isBefore(o2.getServiceDateTime()) ? 1 : -1;
            }
        };
        services.sort(comparator);
        return services;
    }

    public List<Service> getListServiceSortByDateDesc(){
        Comparator<Service> comparator = new Comparator<Service>() {
            @Override
            public int compare(Service o1, Service o2) {
                return o1.getServiceDateTime().isAfter(o2.getServiceDateTime()) ? 1 : -1;
            }
        };
        services.sort(comparator);
        return services;
    }

}
