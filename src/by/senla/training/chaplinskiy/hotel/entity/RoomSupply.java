package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;

public class RoomSupply {

    private Long id;
    private Supply supply;
    private Long roomId;
    private Long personId;
    private  LocalDateTime serviceDateTime;

    public LocalDateTime getServiceDateTime() {
        return serviceDateTime;
    }

    public void setServiceDateTime(LocalDateTime serviceDateTime) {
        this.serviceDateTime = serviceDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
