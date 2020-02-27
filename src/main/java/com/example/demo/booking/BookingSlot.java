package com.example.demo.booking;

import com.example.demo.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingSlot {
    private boolean isAvailable;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private int slotID;

    private User bookedBy = null;

    public BookingSlot (LocalTime startTime, LocalTime endTime, int slotID, LocalDate date){
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotID = slotID;
        this.date = date;
        isAvailable = true;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }
}
