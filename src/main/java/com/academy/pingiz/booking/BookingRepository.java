package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    private int startTime = 7;
    private int endTime = 19;
    private LocalDate date = LocalDate.now();

    private List<BookingDay> allDays = new ArrayList<>();

//    public BookingRepository (){
//        for(int i = 0; i < 3; i++){
//            BookingDay bookingDay = new BookingDay(startTime, endTime, date.plusDays(i), i);
//            allDays.add(bookingDay);
//        }
//    }

    public List<BookingSlot> getSlotsBookedBy(User user){
        List<BookingSlot> slots = new ArrayList<>();
        for(BookingDay day: allDays){
            for(BookingSlot slot: day.getBookingSlotList()) {
                if (slot.getBookedBy() != null && user.getUsername().equals(slot.getBookedBy().getUsername())) {
                    slots.add(slot);
                }
            }
        }
        return slots;
    }

    public List<BookingDay> getAllDays() {
        return allDays;
    }

    public List<BookingSlot> getAllSlots(){
        List<BookingSlot> allSlots = new ArrayList<>();
        for(BookingDay day : allDays){
            for(BookingSlot slot : day.getBookingSlotList()){
                allSlots.add(slot);
            }
        }
        return allSlots;
    }

    public BookingSlot getSlotById(int id){
        return getAllSlots().stream().filter(t -> t.getSlotID()==id).findFirst().get();
    }

    public void setAllDays(List<BookingDay> allDays) {
        this.allDays = allDays;
    }
}
