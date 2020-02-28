package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Integer> numberOfBookingDays(){
        List<Integer> numOfDays = new ArrayList<>();
        for(Integer i = 1; i < bookingRep.getAllDays().size(); i++){
            numOfDays.add(i);
        }
        return numOfDays;
    }

    public void setToBooked(int slotID, User booker) {
        for(BookingDay day : bookingRep.getAllDays()) {
            for (BookingSlot slot : day.getBookingSlotList()) {
                if (slot.getSlotID() == slotID) {
                    slot.setAvailable(false);
                    slot.setBookedBy(booker);
                }
            }
        }
    }

    public List<BookingSlot> getSlotsBookedBy(User user){
        return bookingRep.getSlotsBookedBy(user);
    }
}
