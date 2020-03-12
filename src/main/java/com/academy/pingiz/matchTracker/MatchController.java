package com.academy.pingiz.matchTracker;


import com.academy.pingiz.user.User;
import com.academy.pingiz.user.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        model.addAttribute("users", userService.getUsers());
        model.addAttribute("matches", matchService.getAllMatches() );

        return "viewMatches";
    }

    @PostMapping("/matches")
    public String registerMatch(HttpSession session,
                                @RequestParam(name="player1") String player1,
                                @RequestParam(name="player2") String player2,
                                @RequestParam(name="player1points") int player1points,
                                @RequestParam(name="player2points") int player2points){

        if(session.getAttribute("validated")!= null && (boolean)session.getAttribute("validated")){

            User p1 = userService.getUser(player1).get();
            User p2 = userService.getUser(player2).get();

            Match.Single s = new Match.Single(p1, p2);
            for(int i=0;i<player1points;i++){
                s = s.addSet(11,0);
            }
            for(int i=0;i<player2points;i++){
                s = s.addSet(0,11);
            }
            Match m = s.build();

            matchService.addMatch(m);
            return "redirect:/matches";
        } else {
            return "redirect:/";
        }





    }


    private void setupTestMatches(){

        User jacob = userService.getUser("Jacob").get();
        User elvira = userService.getUser("Elvira").get();



        Match m1 = new Match.Single(jacob,elvira)
                .addSet(5,11)
                .addSet(11,7)
                .addSet(3,11)
                .build();


        matchService.addMatch(m1);
    }


}
