package com.example.demo.forDummies;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class forDummiesController {

    @GetMapping("/forDummies")
    String getBooking(Model model){
//        model.addAttribute();
        return "forDummies";
    }

}
