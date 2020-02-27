package com.example.demo.forum;

import com.example.demo.user.User;
import com.example.demo.user.UserController;
import com.example.demo.user.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
public class ForumController {

    @Autowired
    ForumRepository forumRepository;



    @GetMapping("/forum")
    public String showForum(HttpSession session){
        session.setAttribute("postsAtt", forumRepository.forumPostList);
        return "forum";
    }



    @PostMapping("/forum")
    public String postToForum(@RequestParam String inputText, HttpSession session){
        String currentUser = (String)session.getAttribute("currentUser");

        forumRepository.forumPostList.add(new ForumPost(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM - yyyy")),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),inputText,
                currentUser));

        session.setAttribute("postsAtt",forumRepository.forumPostList);

        return "forum";
    }
}
