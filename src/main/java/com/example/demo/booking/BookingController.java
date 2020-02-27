package com.example.demo.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/booking")
    String getBooking(Model model){
        model.addAttribute("bookingList", bookingService.getBookingRep().getBookingSlotList());
        return "booking";
    }

    @PostMapping("/booking/{slotID}")
    String booking(Model model, @PathVariable int slotID){
        bookingService.setToBooked(slotID);
     return "booking";
    }
}
