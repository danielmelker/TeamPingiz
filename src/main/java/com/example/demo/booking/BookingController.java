package com.example.demo.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/booking")
    String getBooking(Model model){
        model.addAttribute("bookingList", bookingService.getBookingRep().getBookingSlotList());
        return "booking";
    }

    @PostMapping("/booking")
    String booking(Model model){
     return "booking";
    }
}
