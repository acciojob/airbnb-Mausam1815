package com.driver.services;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepo;

    @Autowired
    public HotelService(HotelRepository hotelRepo) {
        this.hotelRepo = hotelRepo;
    }
    
    public String getHotelWithMostFacilities() {
        return hotelRepo.getHotelWithMostFacilities();
    }
    
    public Hotel updateFacilities(String hotelName, List<Facility> newFacilities) {
        return hotelRepo.updateFacilities(hotelName, newFacilities);
    }

    public boolean addHotel(Hotel hotel) {
        if(hotel == null || hotel.getHotelName() == null) {
            return false;
        }
        if(hotelRepo.getHotelByName(hotel.getHotelName()) != null) {
            return false;
        }

        hotelRepo.addHotel(hotel);
        return true;
    }
}
