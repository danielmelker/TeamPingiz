package com.academy.pingiz.forum;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ForumService {

    public int postNum=0;

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private ForumRepositorySql forumRepositorySql;

    public List<ForumPost> getForumPostList(){

        return forumRepository.forumPostList;
    }


        public void addPost(String inputText, User poster){
            postNum++;
            forumRepository.forumPostList.sort(Comparator.comparingInt(ForumPost::getPostNum));
            forumRepository.forumPostList.add(new ForumPost(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM - yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    inputText,
                    poster,
                    postNum));
        }

        public void sortPosts(){
            Collections.reverse(forumRepository.forumPostList);
        }


}
