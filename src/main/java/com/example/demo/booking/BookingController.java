package com.example.demo.booking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class BookingController {

    @GetMapping("/booking")
    String getBooking(){
        return "booking";
    }

    @PostMapping("/booking")
    String booking(){
     return "booking";
    }
}
