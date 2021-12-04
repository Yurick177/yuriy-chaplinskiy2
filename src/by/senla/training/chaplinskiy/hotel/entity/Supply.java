package by.senla.training.chaplinskiy.hotel.entity;

import java.time.LocalDateTime;

public class Supply {

    private final long id;
    private final SupplyType serviceType;
    private int price;
    private LocalDateTime serviceDateTime;

    public Supply(SupplyType serviceType, int price, long id, LocalDateTime serviceDateTime) {
        this.id = id;
        this.serviceType = serviceType;
        this.price = price;
        this.serviceDateTime = serviceDateTime;
    }

    public LocalDateTime getServiceDateTime() {
        return serviceDateTime;
    }

    public long getId() {
        return id;
    }

    public SupplyType getServiceType() {
        return serviceType;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
