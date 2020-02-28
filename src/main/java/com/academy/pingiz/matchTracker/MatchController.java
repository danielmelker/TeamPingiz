package com.academy.pingiz.matchTracker;


import com.academy.pingiz.user.User;
import com.academy.pingiz.user.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;


    @GetMapping("/matches")
    public String displayMatches(HttpSession session, Model model){
        if(matchService.getAllMatches().size() == 0){
            setupTestMatches();
        }

        model.addAttribute("matches", matchService.getAllMatches() );

        return "viewMatches";
    }


    private void setupTestMatches(){

        User jacob = userService.getUser("Jacob");
        User elvira = userService.getUser("Elvira");



        Match m1 = new Match.Single(jacob,elvira)
                .addSet(5,11)
                .addSet(11,7)
                .addSet(3,11)
                .build();


        matchService.addMatch(m1);
    }


}
