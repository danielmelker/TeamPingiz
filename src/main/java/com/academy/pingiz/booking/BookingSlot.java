package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotID;
    private boolean isAvailable;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
//    private static int lastID = 0;

    @ManyToOne
    private User bookedBy = null;

    public BookingSlot (LocalTime startTime, LocalTime endTime, LocalDate date){
        this.startTime = startTime;
        this.endTime = endTime;
//        this.slotID = lastID;
        this.date = date;
        isAvailable = true;
//        lastID++;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getSlotID() {
        return slotID;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.isAvailable=false;
        this.bookedBy = bookedBy;
    }
    public void unbook(){
        this.isAvailable=true;
        this.bookedBy=null;
    }

}
