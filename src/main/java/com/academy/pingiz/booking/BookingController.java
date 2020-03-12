package com.academy.pingiz.booking;

import com.academy.pingiz.user.User;
import com.academy.pingiz.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @GetMapping("/booking")
    String getBooking(Model model, HttpSession session,
                      @RequestParam(required = false)
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentPageDate){
        if(currentPageDate == null){
            currentPageDate = LocalDate.now();
        }

        if(bookingService.getGroupedByDay().get(LocalDate.now()).isEmpty()){
            bookingService.createSlots(LocalDate.now());
            bookingService.createSlots(LocalDate.now().plusDays(1));
            bookingService.createSlots(LocalDate.now().plusDays(2));
        }

        if(bookingService.getGroupedByDay().get(LocalDate.now().plusDays(2)).isEmpty()){
            bookingService.createSlots(LocalDate.now().plusDays(2));
        }

        model.addAttribute("bookingDay", bookingService.getGroupedByDay());
        var days = bookingService.getGroupedByDay().keySet().toArray();
        Arrays.sort(days);
        model.addAttribute("allDays", days);
        model.addAttribute("currentPageDate", currentPageDate);
        if(session.getAttribute("validated") == null){
            session.setAttribute("validated",false);
        }

        return "booking";
    }

    @PostMapping("/booking/{id}")
    String booking(HttpSession session, @PathVariable int id){

        if(session.getAttribute("validated") != null && (boolean)session.getAttribute("validated")){
            String username = (String)session.getAttribute("currentUser");
            User user = userService.getUser(username).get();
            bookingService.setToBooked(id,user);
        }
    return "redirect:/booking";

    }


    @GetMapping("/booking/cancel/{id}")
    String cancel(Model model, HttpSession session, @PathVariable("id") int id){
        bookingService.cancelBooking(id);
        return "redirect:/";
    }
}
