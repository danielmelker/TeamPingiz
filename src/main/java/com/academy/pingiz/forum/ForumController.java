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
    public String showForum(HttpSession session, @RequestParam(required = false, defaultValue = "0") Integer page, Model model){

        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {
            //find all?
            session.setAttribute("postsAtt", forumService.getForumPostList());

         /*   model.addAttribute("page",page);
            model.addAttribute("currentPage",page);
            model.addAttribute("show",forumRepository.splitIntoPages(page));*/
            return "forum";
        } else {
            return "landingPage";
        }
    }

    @GetMapping("/forum/{page}")
    public String showForum1(HttpSession session, @RequestParam(required = false, defaultValue = "1") Integer page, Model model){
        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {
            session.setAttribute("postsAtt", forumService.getForumPostList());
      /*      model.addAttribute("page",page);
            model.addAttribute("currentPage",page);
            model.addAttribute("show",forumRepository.splitIntoPages(2));*/
            return "forum";
        } else {
            return "landingPage";
        }



    }




    @PostMapping("/forum")
    public String postToForum(Model model,@RequestParam(required = false, defaultValue = "") String inputText, HttpSession session,@RequestParam(required = false, defaultValue = "1") Integer page){



        if (session.getAttribute("validated") != null && (boolean) session.getAttribute("validated")) {

            User poster =(User) session.getAttribute("user");

            forumService.addPost(inputText, poster);
            forumService.sortPosts();

            session.setAttribute("postsAtt", forumService.getForumPostList());


         /*   model.addAttribute("page", page);
            model.addAttribute("currentPage", page);*/

            return "forum";
        }else {
            return "redirect:/landingPage";
        }
    }
}
