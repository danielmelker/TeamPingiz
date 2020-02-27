package com.example.demo.booking;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @GetMapping("/booking")
    String getBooking(Model model){
        model.addAttribute("bookingList", bookingService.getBookingRep().getBookingSlotList());
        return "booking";
    }

    @PostMapping("/booking/{slotID}")
    String booking(HttpSession session, @PathVariable int slotID){

        if(session.getAttribute("validated") != null && (boolean)session.getAttribute("validated")){
            String username = (String)session.getAttribute("currentUser");
            User user = userService.getUser(username);
            bookingService.setToBooked(slotID,user);
        }
    return "redirect:/booking";

    }
}
