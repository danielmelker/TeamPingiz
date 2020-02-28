package com.academy.pingiz.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
        setupTestUsers();
    }


    private void setupTestUsers(){
        //debugging: create two test-users.
        User u1 = User.of("Jacob", "pass");
        User u2 = User.of("Elvira", "pass");
        User u3 = User.of("Caroline", "pass");
        User u4 = User.of("Daniel", "pass");
        User u5 = User.of("Christoffer", "pass");

        User andreas = User.of("Andreas", "pass");

        Stream.of(u1,u2,u3,u4,u5).forEach(user -> user.setAcademyClass("Java")) ;
        Stream.of(u1,u2,u3,u4,u5).forEach(user -> user.setDescription("Pingiz developer and ping-pong master!")) ;
        u4.setFileURL("https://upload.wikimedia.org/wikipedia/commons/5/53/Donald_Trump_official_portrait_%28cropped%29.jpg");
        u4.setDescription("Make Pingis Great Again!");
        u5.setFileURL("https://media-exp1.licdn.com/dms/image/C4D03AQHAuUy7IJsm_w/profile-displayphoto-shrink_200_200/0?e=1586390400&v=beta&t=_5Q0DcIwGkMulRZaIISS775N1-WgP4u9xawCU0C-Y_Y");

        u1.setFileURL("https://pbs.twimg.com/profile_images/1134082549041393672/QbihPzrL_400x400.png");
        u2.setFileURL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Boris_Johnson_official_portrait_%28cropped%29.jpg/330px-Boris_Johnson_official_portrait_%28cropped%29.jpg");
        u3.setFileURL("https://static.poder360.com.br/2020/02/Bolsonaro-Defesa-FerandoAzevedo-Ira-4-2-868x644.jpg");


        andreas.setDescription("Java teacher and pingis enthusiast.");

        userService.register(u1,u2,u3,u4,u5,andreas);
    }


    @GetMapping("/editProfile")
    public String editProfile(HttpSession session){


        if(session.getAttribute("validated") != null && (boolean)session.getAttribute("validated")){
            User u = userService.getUser((String)session.getAttribute("currentUser"));


            session.setAttribute("userImg", u.getFileURL());
            session.setAttribute("userDescription", u.getDescription());
            return "editProfile";
        }

        return "redirect:/login";
    }

    @PostMapping("/editProfile")
    public String editProfile(HttpSession session,
                              @RequestParam(name="description") String description,
                              @RequestParam(name="imgFileURL") String imgFileURL,
                              @RequestParam(name="academyClass") String academyClass,
                              @RequestParam(name="password", defaultValue="") String password){
        if(session.getAttribute("validated")==null){
            return "redirect:'/login";
        }

        if((boolean)session.getAttribute("validated")){
            User u = userService.getUser((String)session.getAttribute("currentUser"));

            session.setAttribute("user", u);

            if(description!=null && !description.equalsIgnoreCase(""))
                u.setDescription(description);
            if(imgFileURL!=null && !imgFileURL.equalsIgnoreCase(""))
                u.setFileURL(imgFileURL);
            if(academyClass!=null && !academyClass.equalsIgnoreCase(""))
                u.setAcademyClass(academyClass);

            if(password != null && !password.equals("")){
                u.setPassword(password);
            }

            return "editProfile";
        }

        return "redirect/login";
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
            var u = userService.getUser(username);
            session.setAttribute("user", u);
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
        session.setAttribute("user", null);
        session.setAttribute("validated", false);
        return "redirect:/";
    }


}
