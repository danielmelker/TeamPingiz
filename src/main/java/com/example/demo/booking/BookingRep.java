package com.example.demo.booking;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;

public class BookingRep {

    static List<BookingSlot> bookingSlotList = new ArrayList<>();
    //Calendar calendar = Calendar.getInstance();


    public void createSlots(){
        int startTime = 7;
        int endTime = 19;
        LocalTime slotStart;
        LocalTime slotEnd;


        for(int i = startTime; i < endTime; i++){
            slotStart = LocalTime.of(startTime, 0);
            slotEnd = LocalTime.of(startTime+1, 0);

            BookingSlot slot = new BookingSlot(slotStart, slotEnd, startTime);

            bookingSlotList.add(slot);

            startTime++;
        }

        for(BookingSlot s : bookingSlotList){
            System.out.println(s.getStartTime().toString() + " " + s.getEndTime().toString());
        }

    }
}
