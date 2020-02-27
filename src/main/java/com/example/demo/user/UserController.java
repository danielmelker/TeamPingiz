package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


        if(session.getAttribute("validated") != null && (boolean)session.getAttribute("validated")){
            User u = userService.getUser((String)session.getAttribute("currentUser"));

            session.setAttribute("userDescription", u.getDescription());
            return "editProfile";
        }

        return "redirect:/login";
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
        return "login2";
    }


    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam(name="username") String username,
                                            @RequestParam(name="password") String password){

        AuthResponse response = userService.logIn(username, password);

        if(response.isValid()){
            session.setAttribute("currentUser", username );
            session.setAttribute("validated", true);
            return "redirect:/";
        } else {
            session.setAttribute("currentUser", "");
            session.setAttribute("validated", false);
            return "redirect:/login";
        }

    }


    @GetMapping("/register")
    public String register(HttpSession session){
        return "register";
    }


    @PostMapping("/register")
    public String register(HttpSession session, @RequestParam(name="username") String username,
                        @RequestParam(name="password") String password){

        boolean userCreated = userService.register(User.of(username,password));

        if(userCreated){
            return "redirect:/login";
        } else {
            return "redirect:/register";
        }

    }

    @GetMapping("/users")
    public String getUsers(HttpSession session, Model model){

        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "viewUsers";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("currentUser", "");
        session.setAttribute("validated", false);
        return "redirect:/";
    }


}
