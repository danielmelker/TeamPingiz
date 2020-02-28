package com.academy.pingiz.matchTracker;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MatchController {



    @GetMapping("/matches")
    public String displayMatches(HttpSession session){

        return "showMatches";
    }

}
