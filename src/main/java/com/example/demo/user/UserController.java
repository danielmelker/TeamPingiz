package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
        //debugging: create two test-users.
        User u = User.of("test", "pass");
        User v = User.of("jacob", "asdf");

        System.out.println(userService);

        userService.register(u);
        userService.register(v);
    }


    @GetMapping("/editProfile")
    public String editProfile(HttpSession session){
        if(session.getAttribute("validated")==null){
            return "login";
        }

        if((boolean)session.getAttribute("validated")){
            User u = userService.getUser((String)session.getAttribute("currentUser"));

            session.setAttribute("userDescription", u.getDescription());
            return "editProfile";
        }

        return "login";
    }

    @PostMapping("/editProfile")
    public String editProfile(HttpSession session, @RequestParam(name="description") String description,
                              @RequestParam(name="password", defaultValue="") String password){
        if(session.getAttribute("validated")==null){
            return "index";
        }

        if((boolean)session.getAttribute("validated")){
            User u = userService.getUser((String)session.getAttribute("currentUser"));

            session.setAttribute("userDescription", description);
            u.setDescription(description);

            if(password != null && !password.equals("")){
                u.setPassword(password);
            }

            return "editProfile";
        }

        return "index";
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        return "login";
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
            return "login";
        }

    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("currentUser", "");
        session.setAttribute("validated", false);
        return "welcomePage";
    }


}
