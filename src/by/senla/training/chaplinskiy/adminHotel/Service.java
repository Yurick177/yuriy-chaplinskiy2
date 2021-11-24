package by.senla.training.chaplinskiy.adminHotel;

import java.time.LocalDateTime;

public class Service {

    private final long id;
    private final ServiceType serviceType;
    private int price;
    private LocalDateTime serviceDateTime;

    public Service(ServiceType serviceType, int price, long id, LocalDateTime serviceDateTime) {
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

    public ServiceType getServiceType() {
        return serviceType;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
