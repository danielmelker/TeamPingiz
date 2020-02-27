package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TeamPingizController {



    @GetMapping
    public String showIndex(HttpSession session) {
        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {
            return "index";
        } else {
            return "landingPage";
        }
    }

}
