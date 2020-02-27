package com.example.demo.forum;

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
import java.util.List;


@Controller
public class ForumController {

    @Autowired
    ForumRepository forumRepository;

    @GetMapping("/forum")
    public String showForum(HttpSession session){
        session.setAttribute("tidigareTexter", forumRepository.postList);
        return "forum";
    }



    @PostMapping("/forum")
    public String postToForum(@RequestParam String inputText, HttpSession session){


        String nuu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM - yyyy"));
        String nuub = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));


        session.setAttribute("timea",nuu);
        session.setAttribute("timeb",nuub);


        forumRepository.postList.add(inputText);
        session.setAttribute("tidigareTexter", forumRepository.postList);
        return "forum";
    }
}
