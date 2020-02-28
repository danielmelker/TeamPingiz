package com.example.demo.booking;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRep {

    private int startTime = 7;
    private int endTime = 19;
    private LocalTime slotStart;
    private LocalTime slotEnd;
    private LocalDate date = LocalDate.now();

    private List<List> allSlots = new ArrayList<>();
    private List<BookingSlot> bookingSlotList = new ArrayList<>();
    private List<BookingSlot> bookingSlotList2 = new ArrayList<>();
    private List<BookingSlot> bookingSlotList3 = new ArrayList<>();

    public BookingRep (){
        createSlots1();
        createSlots2();
        createSlots3();
        allSlots.add(bookingSlotList);
        allSlots.add(bookingSlotList2);
        allSlots.add(bookingSlotList3);
    }

    public void createSlots1(){

        for (int i = startTime; i < endTime; i++) {
                slotStart = LocalTime.of(startTime, 0);
                slotEnd = LocalTime.of(startTime + 1, 0);

                BookingSlot slot = new BookingSlot(slotStart, slotEnd, startTime, date);

                bookingSlotList.add(slot);

                startTime++;
            }
        }

    public void createSlots2() {
        startTime = 7;
        endTime = 19;

        for (int i = startTime; i < endTime; i++) {
            slotStart = LocalTime.of(startTime, 0);
            slotEnd = LocalTime.of(startTime + 1, 0);

            LocalDate dateTomorrow = date.plusDays(1);

            BookingSlot slot = new BookingSlot(slotStart, slotEnd, startTime, dateTomorrow);

            bookingSlotList2.add(slot);

            startTime++;
        }
    }

    public void createSlots3() {
        startTime = 7;
        endTime = 19;

        for (int i = startTime; i < endTime; i++) {
            slotStart = LocalTime.of(startTime, 0);
            slotEnd = LocalTime.of(startTime + 1, 0);

            LocalDate dateTomorrow = date.plusDays(2);

            BookingSlot slot = new BookingSlot(slotStart, slotEnd, startTime, dateTomorrow);

            bookingSlotList3.add(slot);

            startTime++;
        }
    }


    public List<BookingSlot> getBookingSlotList() {
        return bookingSlotList;
    }

    public void setBookingSlotList(List<BookingSlot> bookingSlotList) {
        this.bookingSlotList = bookingSlotList;
    }

    public List<BookingSlot> getBookingSlotList2() {
        return bookingSlotList2;
    }

    public void setBookingSlotList2(List<BookingSlot> bookingSlotList2) {
        this.bookingSlotList2 = bookingSlotList2;
    }

    public List<BookingSlot> getBookingSlotList3() {
        return bookingSlotList3;
    }

    public void setBookingSlotList3(List<BookingSlot> bookingSlotList3) {
        this.bookingSlotList3 = bookingSlotList3;
    }

    public List<List> getAllSlots() {
        return allSlots;
    }

    public void setAllSlots(List<List> allSlots) {
        this.allSlots = allSlots;
    }
}
