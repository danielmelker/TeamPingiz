package com.academy.pingiz.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

@Service
public class ForumService {

    public int postNum=0;

    @Autowired
    ForumRepository forumRepository;



        public void addPost(HttpSession session, String inputText){
            String currentUser = (String)session.getAttribute("currentUser");
            postNum++;
            forumRepository.forumPostList.sort(Comparator.comparingInt(ForumPost::getPostNum));
            forumRepository.forumPostList.add(new ForumPost(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM - yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),inputText,
                    currentUser, postNum));
        }

        public void sortPosts(){
            Collections.reverse(forumRepository.forumPostList);
        }


}
