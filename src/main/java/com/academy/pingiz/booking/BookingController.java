package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import com.academy.pingiz.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @GetMapping("/booking")
    String getBooking(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "0") int bookingPage){
        model.addAttribute("bookingDay", bookingService.getBookingRep().getAllDays().get(bookingPage));
        model.addAttribute("allDays", bookingService.getBookingRep().getAllDays());
        model.addAttribute("currentPage", bookingPage);
        model.addAttribute("currentDate", bookingService.getBookingRep().getAllDays().get(bookingPage).getDate());
        model.addAttribute("numOfDays", bookingService.numberOfBookingDays());
        if(session.getAttribute("validated") == null){
            session.setAttribute("validated",false);
        }
//        if(session.getAttribute("validated") != null && (boolean)session.getAttribute("validated")){
//
//        }
        return "booking";
    }

    @PostMapping("/booking/{slotID}")
    String booking(HttpSession session, @PathVariable int slotID){

        if(session.getAttribute("validated") != null && (boolean)session.getAttribute("validated")){
            String username = (String)session.getAttribute("currentUser");
            User user = userService.getUser(username).get();
            bookingService.setToBooked(slotID,user);
        }
    return "redirect:/booking";

    }


    @GetMapping("/booking/cancel/{id}")
    String cancel(Model model, HttpSession session, @PathVariable("id") int id){
        bookingService.cancelBooking(id);
        return "redirect:/";
    }
}
