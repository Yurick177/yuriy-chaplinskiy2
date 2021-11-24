package by.senla.training.chaplinskiy.adminHotel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hotel {

    private List<Room> rooms;
    private List<Person> persons;
    private List<Service> services;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public List<Room> getRoomsByPriceAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByPriceDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() < o2.getPrice() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByStarAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() > o2.getStar() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByStarDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() < o2.getStar() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getRoomsByCapacityRoomAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() > o2.getCapacityRoom() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;

    }

    public List<Room> getRoomsByCapacityRoomDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() < o2.getCapacityRoom() ? 1 : -1;
            }
        };
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room i : rooms) {
            if (i.getStatus().equals(Status.AVAILABLE)) {
                availableRooms.add(i);
            }
        }
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByPriceAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByPriceDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getPrice() < o2.getPrice() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCapacityAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() > o2.getCapacityRoom() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCapacityDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getCapacityRoom() < o2.getCapacityRoom() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByStarAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() > o2.getStar() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByStarDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getStar() < o2.getStar() ? 1 : -1;
            }
        };
        List<Room> availableRooms = getAvailableRooms();
        availableRooms.sort(comparator);
        return availableRooms;
    }

    public List<Room> getOccupiedRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room i : rooms) {
            if (i.getStatus().equals(Status.OCСUPIED)) {
                availableRooms.add(i);
            }
        }
        return availableRooms;
    }

    public List<Person> sortAbs() {
        persons.sort(new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return persons;
    }

    public List<Room> getOccupiedRoomsSortByDateDesc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getReleaseDate().isBefore(o2.getReleaseDate()) ? 1 : -1;
            }
        };
        List<Room> occupiedRooms = getOccupiedRooms();
        occupiedRooms.sort(comparator);
        return occupiedRooms;
    }

    public List<Room> getOccupiedRoomsSortByDateAsc() {
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getReleaseDate().isAfter(o2.getReleaseDate()) ? 1 : -1;
            }
        };
        List<Room> occupiedRooms = getOccupiedRooms();
        occupiedRooms.sort(comparator);
        return occupiedRooms;
    }

    public int getFreeNumbers() {
        List<Room> freeNumbers = getAvailableRooms();
        return freeNumbers.size();
    }

    public int getNumberGuests() {
        List<Person> persons = getPersons();
        return persons.size();
    }

    public List<Room> getAvailableRoomsByDate(LocalDateTime localDateTime) {
        List<Room> result = new ArrayList<>();
        for (Room i : rooms) {
            if (i.getReleaseDate() == null || i.getReleaseDate().isBefore(localDateTime)) {
                result.add(i);
            }
        }
        return result;
    }

    public int getTotalPrice(Person person) {
        List<Room> personRooms = new ArrayList<>();
        for (Room i : rooms) {
            if (person == i.getPerson()) {
                personRooms.add(i);
            }
        }
        int price = 0;
        if (!personRooms.isEmpty()) {
            for (Room i : personRooms) {
                LocalDateTime releaseDate = i.getReleaseDate();
                LocalDateTime checkIn = i.getCheckInDate();
                long between = ChronoUnit.DAYS.between(checkIn, releaseDate);
                int dayPrice = i.getPrice();
                int roomPrice = (int) between * dayPrice;
                price += roomPrice;
            }

        }
        return price;

    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addRum(Room room) {
        rooms.add(room);
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }

    public void checkInPerson(Person person, LocalDateTime checkInDate, LocalDateTime releaseDate) {
        for (Room room : rooms) {
            if (room.getStatus().equals(Status.AVAILABLE)) {
                addPerson(person);
                room.addPerson(person, checkInDate, releaseDate);
                break;
            }
        }
    }

    public void checkOutPerson(Person person) {
        for (Room room : rooms) {
            if (room.getPerson().equals(person)) {
                removePerson(person);
                room.removePerson();
            }
        }
    }


    public List<Service> getServices() {
        return services;
    }

    public List<Service> getServicesSortedPriceByService() {
        Comparator<Service> comparator = new Comparator<Service>() {
            @Override
            public int compare(Service o1, Service o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }

        };
        services.sort(comparator);
        return services;
    }

    public List<Service> getServicesSortedByServiceType() {
        Comparator<Service> comparator = new Comparator<Service>() {
            @Override
            public int compare(Service o1, Service o2) {
                return o1.getServiceType().name().compareTo(o2.getServiceType().name());
            }

        };
        services.sort(comparator);
        return services;
    }


    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
        for (Room room : rooms) {
            room.getServices().remove(service);
        }
    }

    public void addServiceToRoom(Room room, Service service) {
        room.addService(service);
    }

}


