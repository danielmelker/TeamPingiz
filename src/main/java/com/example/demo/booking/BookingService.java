package com.example.demo.booking;

import com.example.demo.user.User;
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

    public void setToBooked(int slotID, User booker) {
        for(BookingSlot slot : bookingRep.getBookingSlotList()){
            if(slot.getSlotID() == slotID){
                slot.setAvailable(false);
                slot.setBookedBy(booker);
            }
        }
    }
}
