package com.academy.pingiz.forDummies;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class forDummiesController {

    @GetMapping("/forDummies")
    public String getBooking(){
        return "forDummies";
    }

}
