package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepositoryJPA repJpa;

    private int startTime = 7;
    private int endTime = 19;
    private LocalDate date = LocalDate.now();

    public void createSlots(int startTime, int endTime, LocalDate date){
        for (int t = 0; t < 3; t++) {
            for (int i = startTime; i < endTime; i++) {
                LocalTime slotStart = LocalTime.of(startTime, 0);
                LocalTime slotEnd = LocalTime.of(startTime + 1, 0);

                repJpa.save(new BookingSlot(slotStart, slotEnd, date));

                startTime++;
            }
            date.plusDays(1);
        }
    }

    public void setToBooked(int slotID, User booker) {

        Optional<BookingSlot> slot = repJpa.findById(slotID);

        if (slot.isEmpty() ||! slot.get().getIsAvailable()) {
            return;
        }

        slot.get().setAvailable(false);
        slot.get().setBookedBy(booker);

    }

    public void cancelBooking(int id){
        Optional<BookingSlot> slot = repJpa.findById(id);

        if (slot.isEmpty()) {
            return;
        }

        slot.get().setAvailable(true);
        slot.get().setBookedBy(null);
    }
}
