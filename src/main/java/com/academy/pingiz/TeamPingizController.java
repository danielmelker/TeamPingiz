package com.academy.pingiz;

import com.academy.pingiz.booking.BookingService;
import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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
