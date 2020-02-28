package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRep;

    public BookingRepository getBookingRep() {
        return bookingRep;
    }

    public void setBookingRep(BookingRepository bookingRep) {
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

    public List<BookingSlot> getSlotsBookedBy(User user){
        return bookingRep.getSlotsBookedBy(user);
    }
}
