package com.academy.pingiz;

import com.academy.pingiz.booking.BookingService;
import com.academy.pingiz.user.User;
import com.academy.pingiz.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.UniqueConstraint;
import javax.servlet.http.HttpSession;

@Controller
public class TeamPingizController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @GetMapping
    public String showIndex(HttpSession session, Model model) {
        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {
            User user = (User)session.getAttribute("user");

            user = userService.getUser(user.getUsername()).get();
            session.setAttribute("user", user);

            if(user.getBookings()==null){
                System.out.println("bookings is null!");
            }
            System.out.println(user.getBookings().size());

            model.addAttribute("bookings", user.getBookings());
            return "index";
        } else {
            return "landingPage";
        }
    }

}
