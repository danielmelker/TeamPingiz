package com.example.demo;

import com.example.demo.booking.BookingService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TeamPingizController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    public String showIndex(HttpSession session, Model model) {
        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {

            User user = (User)session.getAttribute("user");
            var slots = bookingService.getSlotsBookedBy(user);
            model.addAttribute("bookedSlots", slots);
            return "index";
        } else {
            return "landingPage";
        }
    }

}
