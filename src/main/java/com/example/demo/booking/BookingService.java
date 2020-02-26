package com.example.demo.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRep bookingRep;

    public BookingRep getBookingRep() {
        return bookingRep;
    }

    public void setBookingRep(BookingRep bookingRep) {
        this.bookingRep = bookingRep;
    }
}
