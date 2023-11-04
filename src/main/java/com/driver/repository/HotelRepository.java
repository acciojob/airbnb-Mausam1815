package com.driver.repository;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class HotelRepository {
//    Hotel h1 = new Hotel("Hotel A", 12, Arrays.asList(Facility.GYM, Facility.FOOD), 200);
    private HashMap<String, Hotel> hotelDB = new HashMap<>();

    public String getHotelWithMostFacilities() {
        int maxFacilityCount = 0;
        String hotelWithMostFacilities = "";

        for(Hotel hotel : hotelDB.values()) {
            int facilityCount = hotel.getFacilities().size();
            if(facilityCount > maxFacilityCount) {
                maxFacilityCount = facilityCount;
                hotelWithMostFacilities = hotel.getHotelName();
            }else if(facilityCount == maxFacilityCount) {
                hotelWithMostFacilities = hotel.getHotelName().compareTo(hotelWithMostFacilities) < 0 ? hotel.getHotelName() : hotelWithMostFacilities;
            }
        }
        return hotelWithMostFacilities;
    }

    public Hotel updateFacilities(String hotelName, List<Facility> newFacilities) {
        if(hotelDB.get(hotelName) != null) {
            Hotel hotel = hotelDB.get(hotelName);
            List<Facility> facilities = hotel.getFacilities();
            for(Facility facility : newFacilities) {
                if(!facilities.contains(facility)) {
                    facilities.add(facility);
                }
            }
            hotel.setFacilities(facilities);
            return hotel;
        }
        return null;
    }
    public Hotel getHotelByName(String hotelName) {
        return hotelDB.get(hotelName);
    }

    public void addHotel(Hotel hotel) {
        if(hotel != null && hotel.getHotelName() != null) {
            hotelDB.put(hotel.getHotelName(), hotel);
        }
    }
}
