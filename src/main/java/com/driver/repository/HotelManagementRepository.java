package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class HotelManagementRepository {
    private HashMap<String, Hotel> hotelDB;
    private HashMap<Integer, User> userDB;
    private HashMap<String, Booking> bookingDB;
    private HashMap<Integer, List<String>> bookingsByAadhar;

    public HotelManagementRepository(){
        this.hotelDB = new HashMap<>();
        this.userDB = new HashMap<>();
        this.bookingDB = new HashMap<>();
        this.bookingsByAadhar = new HashMap<>();
    }

    public String addHotel(Hotel hotel) {
        String hotelName = hotel.getHotelName();
        if(hotelName == null || hotelName.length() == 0) {
            return "FAILURE";
        }else if(hotel == null || hotelDB.containsKey(hotelName)) {
            return "FAILURE";
        }else {
            hotelDB.put(hotelName, hotel);
            return "SUCCESS";
        }
    }

    public Integer addUser(User user) {
        int aadharNo = user.getaadharCardNo();
        if(!userDB.containsKey(aadharNo)) {
            userDB.put(aadharNo, user);
        }
        return aadharNo;
    }

    public String getHotelWithMostFacilities() {
        if(hotelDB.size() == 0) {
            return "";
        }
        int maxFacilitiesCount = 0;
        String hotelWithMostFacilities = "";

        for(String hotelName : hotelDB.keySet()) {
            int facilities = hotelDB.get(hotelName).getFacilities().size();
            if(facilities > maxFacilitiesCount) {
                hotelWithMostFacilities = hotelName;
                maxFacilitiesCount = facilities;
            } else if (maxFacilitiesCount > 0 && facilities == maxFacilitiesCount) {
                if(hotelWithMostFacilities.compareTo(hotelName) > 0) {
                    hotelWithMostFacilities = hotelName;
                }
            }
        }
        return hotelWithMostFacilities;
    }

    public int bookARoom(Booking booking) {
        int requiredRooms = booking.getNoOfRooms();
        String hotelName = booking.getHotelName();

        if(hotelDB.containsKey(hotelName) && requiredRooms <= hotelDB.get(hotelName).getAvailableRooms()) {
            Hotel hotel = hotelDB.get(hotelName);
            String bookingId = UUID.randomUUID().toString();
            int totalAmountToBePaid = requiredRooms * hotel.getPricePerNight();

            booking.setBookingId(bookingId);
            booking.setAmountToBePaid(totalAmountToBePaid);

            bookingDB.put(bookingId, booking);

            hotel.setAvailableRooms(hotel.getAvailableRooms() - requiredRooms);

            int aadharNo = booking.getBookingAadharCard();
            if(!bookingsByAadhar.containsKey(aadharNo)) {
                bookingsByAadhar.put(aadharNo, new ArrayList<>());
            }
            bookingsByAadhar.get(aadharNo).add(bookingId);
            return totalAmountToBePaid;
        }
        return -1;
    }

    public int getBookings(Integer aadharNo) {
        if(bookingsByAadhar.containsKey(aadharNo)) {
            return bookingsByAadhar.get(aadharNo).size();
        }
        return 0;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        if(hotelDB.containsKey(hotelName)) {
            Hotel hotel = hotelDB.get(hotelName);
            List<Facility> currFacilities = hotel.getFacilities();

            for(Facility facility : newFacilities) {
                if(!currFacilities.contains(facility)) {
                    currFacilities.add(facility);
                }
            }
            hotel.setFacilities(currFacilities);
            return hotel;
        }
        return null;
    }
}
