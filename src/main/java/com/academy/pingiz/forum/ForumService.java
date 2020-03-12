package com.academy.pingiz.forum;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
public class ForumService {

    public int postNum=0;

    @Autowired
    private ForumRepositorySql forumRepositorySql;

    public List<ForumPost> getForumPostList(){
        return (List)forumRepositorySql.findAllByOrderByTimePostedDesc();
    }

        public void addPost(String inputText, User poster){
            postNum++;
            forumRepositorySql.save(new ForumPost(LocalDate.now(),
                    LocalTime.now(),
                    inputText,
                    poster,
                    postNum));
        }
}
