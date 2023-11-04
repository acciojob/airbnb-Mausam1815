package com.driver.services;

import com.driver.model.Booking;
import com.driver.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepo;

    public BookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public int bookARoom(Booking booking) {
        return bookingRepo.bookARoom(booking);
    }
    public List<Booking> getBookingsByPerson(int aadharCard) {
        return bookingRepo.getBookingsByPerson(aadharCard);
    }
}
