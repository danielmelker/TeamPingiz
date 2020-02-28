package com.academy.pingiz.matchTracker;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {



    @GetMapping("/matches")
    public String displayMatches(){

        return "showMatches";
    }

}
