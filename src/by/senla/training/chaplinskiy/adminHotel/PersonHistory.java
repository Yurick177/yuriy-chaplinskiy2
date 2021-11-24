package by.senla.training.chaplinskiy.adminHotel;

import java.time.LocalDateTime;

public class PersonHistory {

    private Person person;
    private LocalDateTime releaseDate;
    private LocalDateTime checkInDate;

    public PersonHistory(Person person, LocalDateTime releaseDate, LocalDateTime checkInDate) {
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
}
