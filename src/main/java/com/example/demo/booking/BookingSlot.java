package com.example.demo.booking;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingSlot {
    private boolean isAvailable;
//    private int startTime;
//    private int endTime;
    private LocalTime startTime;
    private LocalTime endTime;

    public BookingSlot (LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
}
