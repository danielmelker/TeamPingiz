package com.academy.pingiz.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDay {

    private List<BookingSlot> bookingSlotList = new ArrayList<>();
    private LocalDate date;
    private Integer dayID;

    public BookingDay(int startTime, int endTime, LocalDate date, Integer dayID){
        this.date = date;
        this.dayID = dayID;
        createSlots(startTime, endTime, date);
    }

//    public void createSlots(int startTime, int endTime, LocalDate date){
//
//        for (int i = startTime; i < endTime; i++) {
//            LocalTime slotStart = LocalTime.of(startTime, 0);
//            LocalTime slotEnd = LocalTime.of(startTime + 1, 0);
//
//            BookingSlot slot = new BookingSlot(slotStart, slotEnd, date);
//
//            bookingSlotList.add(slot);
//
//            startTime++;
//        }
//    }

    public List<BookingSlot> getBookingSlotList() {
        return bookingSlotList;
    }

    public void setBookingSlotList(List<BookingSlot> bookingSlotList) {
        this.bookingSlotList = bookingSlotList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDayID() {
        return dayID;
    }

    public void setDayID(Integer dayID) {
        this.dayID = dayID;
    }
}
