package com.academy.pingiz.forum;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/forum")
    public String showForum(HttpSession session){

            session.setAttribute("postsAtt", forumService.getForumPostList());

            return "forum";
    }

    @GetMapping("/delete")
    public String delete(){
        forumService.deletePost(1);

        return "forum";
    }

    @PostMapping("/forum")
    public String postToForum(@RequestParam(required = false, defaultValue = "") String inputText, HttpSession session){

        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {
            User poster =(User) session.getAttribute("user");
            forumService.addPost(inputText, poster);

            session.setAttribute("postsAtt", forumService.getForumPostList());

            return "redirect:/forum";
        }else {
            return "redirect:/";
        }
    }
}
