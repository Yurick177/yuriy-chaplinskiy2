package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;

public class PersonHistory {

    private Long id;
    private final Person person;
    private final LocalDateTime releaseDate;
    private final LocalDateTime checkInDate;
    private final Long roomId;

    public PersonHistory(Person person, LocalDateTime releaseDate, LocalDateTime checkInDate, Long roomId) {
        this.roomId = roomId;
        this.person = person;
        this.releaseDate = releaseDate;
        this.checkInDate = checkInDate;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getRoomId() {
        return roomId;
    }
}
