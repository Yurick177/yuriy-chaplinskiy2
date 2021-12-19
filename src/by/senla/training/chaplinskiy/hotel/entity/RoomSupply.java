package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;

public class RoomSupply {

    private Long id;
    private Long supplyId;
    private Long roomId;
    private Long personId;
    private LocalDateTime serviceDateTime;

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

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
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
