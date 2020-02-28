package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    private List<BookingSlot> bookingSlotList = new ArrayList<>();
    //Calendar calendar = Calendar.getInstance();

    public BookingRepository(){
        createSlots();
    }

    public void createSlots(){
        int startTime = 7;
        int endTime = 19;
        LocalTime slotStart;
        LocalTime slotEnd;
        LocalDate date;

        for(int j = 0; j < 3; j++) {
            date = LocalDate.now().plusDays(j);

            for (int i = startTime; i < endTime; i++) {
                slotStart = LocalTime.of(startTime, 0);
                slotEnd = LocalTime.of(startTime + 1, 0);

                BookingSlot slot = new BookingSlot(slotStart, slotEnd, startTime, date);

                bookingSlotList.add(slot);

                startTime++;
            }
        }
    }

    public List<BookingSlot> getBookingSlotList() {
        return bookingSlotList;
    }

    public void setBookingSlotList(List<BookingSlot> bookingSlotList) {
        this.bookingSlotList = bookingSlotList;
    }

    public List<BookingSlot> getSlotsBookedBy(User user){
        List<BookingSlot> slots = new ArrayList<>();
        for(BookingSlot slot: bookingSlotList){
            if(slot.getBookedBy()!= null && user.getUsername().equals(slot.getBookedBy().getUsername())){
                slots.add(slot);
            }
        }
        return slots;
    }


    public BookingSlot getSlotById(int id){
        return bookingSlotList.stream().filter(t -> t.getSlotID()==id).findFirst().get();

    }

}
