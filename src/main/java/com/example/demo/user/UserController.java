package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(){
        //debugging: create two test-users.
        User u = User.of("test", "pass");
        User v = User.of("jacob", "asdf");
    }


    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam(name="username") String username,
                                            @RequestParam(name="password") String password){

        AuthResponse response = userService.logIn(username, password);

        if(response.isValid()){

            session.setAttribute("currentUser", username );
            session.setAttribute("validated", true);
            return "index";
        } else {
            session.setAttribute("currentUser", "");
            session.setAttribute("validated", false);
            return "welcomePage";
        }

    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("currentUser", "");
        session.setAttribute("validated", false);
        return "welcomePage";
    }


}
