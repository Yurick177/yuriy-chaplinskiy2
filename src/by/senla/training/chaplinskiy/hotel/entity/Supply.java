package by.senla.training.chaplinskiy.hotel.entity;

public class Supply {

    private Long id;
    private final SupplyType serviceType;
    private int price;

    public Supply(SupplyType serviceType, int price) {
        this.serviceType = serviceType;
        this.price = price;
    }

    public Long getId() {
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

    public void setId(Long id) {
        this.id = id;
    }

}
