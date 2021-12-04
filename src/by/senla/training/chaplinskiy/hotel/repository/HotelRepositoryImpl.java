package by.senla.training.chaplinskiy.hotel.repository;

import by.senla.training.chaplinskiy.hotel.entity.Hotel;

public class HotelRepositoryImpl implements HotelRepository {

    private static HotelRepositoryImpl hotelRepository = null;
    private Hotel hotel;

    private HotelRepositoryImpl() {
    }

    public Hotel getHotel() {
        if (hotel == null) {
            hotel = new Hotel();
        }
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static HotelRepositoryImpl getHotelRepository() {
        if (hotelRepository == null) {
            hotelRepository = new HotelRepositoryImpl();
        }
        return hotelRepository;
    }

}
