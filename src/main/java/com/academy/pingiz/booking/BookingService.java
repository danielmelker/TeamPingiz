package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class BookingService {

    @Autowired
    private BookingRepositoryJPA repJpa;

    private int startTime = 7;
    private int endTime = 19;

    public void createSlots(LocalDate date){
            for (int i = startTime; i < endTime; i++) {
                LocalTime slotStart = LocalTime.of(startTime, 0);
                LocalTime slotEnd = LocalTime.of(startTime + 1, 0);
                var slot = new BookingSlot(slotStart, slotEnd, date);
                repJpa.save(slot);

                startTime++;
                System.out.println(date);
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

    public Map<LocalDate, List> getGroupedByDay() {
        LocalDate date = LocalDate.now();

        List<BookingSlot> day1 = repJpa.findByDate(date);
        List<BookingSlot> day2 = repJpa.findByDate(date.plusDays(1));
        List<BookingSlot> day3 = repJpa.findByDate(date.plusDays(2));

        Map<LocalDate, List> days = new HashMap<>();

        days.put(date.plusDays(2), day3);
        days.put(date.plusDays(1), day2);
        days.put(date, day1);

        return days;
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
