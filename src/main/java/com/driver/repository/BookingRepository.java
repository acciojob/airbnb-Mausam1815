package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class BookingRepository {
    private final HashMap<String, Booking> bookingDB = new HashMap<>();
    private final HashMap<Integer, List<String>> bookingsOnAadhar = new HashMap<>();
    int bookingIdCounter = 1;
    private final HotelRepository hotelRepo;


    public BookingRepository(HotelRepository hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public int bookARoom(Booking booking) {
        int roomsRequired = booking.getNoOfRooms();
        String hotelName = booking.getHotelName();

        if(hotelRepo.getHotelByName(hotelName) != null && roomsRequired <= hotelRepo.getHotelByName(hotelName).getAvailableRooms()) {
            Hotel hotel = hotelRepo.getHotelByName(hotelName);
            String bookingId = UUID.randomUUID().toString();
            int amountToBePaid = roomsRequired * hotel.getPricePerNight();

            booking.setBookingId(bookingId);
            booking.setAmountToBePaid(amountToBePaid);

            bookingDB.put(bookingId, booking);
            hotel.setAvailableRooms(hotel.getAvailableRooms() - roomsRequired);

            int aadharNo = booking.getBookingAadharCard();
            if (!bookingsOnAadhar.containsKey(aadharNo)) {
                bookingsOnAadhar.put(aadharNo, new ArrayList<>());
            }
            bookingsOnAadhar.get(aadharNo).add(bookingId);

            return amountToBePaid;
        }
        return -1;
    }

    public List<Booking> getBookingsByPerson(int aadharCard) {
        List<Booking> userBookings = new ArrayList<>();
        for(Booking booking : bookingDB.values()) {
            if(booking.getBookingAadharCard() == aadharCard) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

}
