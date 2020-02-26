package com.example.demo.forum;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForumController {

    @GetMapping("/forum")
    public String showForum(){

        return "forum";
    }

    @PostMapping("/forum")
    public String postToForum(@RequestParam String inputText, Model model){

        model.addAttribute("textAtt",inputText);
        return "forum";
    }
}
