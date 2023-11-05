package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementServices {
    HotelManagementRepository hotelManagementRepo = new HotelManagementRepository();

    public String addHotel(Hotel hotel) {
        return hotelManagementRepo.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return hotelManagementRepo.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return hotelManagementRepo.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return hotelManagementRepo.bookARoom(booking);
    }

    public int getBookings(Integer aadharNo) {
        return hotelManagementRepo.getBookings(aadharNo);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepo.updateFacilities(newFacilities, hotelName);
    }
}
