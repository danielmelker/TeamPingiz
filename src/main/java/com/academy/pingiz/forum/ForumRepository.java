package com.academy.pingiz.forum;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ForumRepository {

    List<String> postList = new ArrayList<>();
    List<ForumPost> forumPostList = new ArrayList<>();


    public List<ForumPost> getForumPostList(){
        return forumPostList;
    }

    /*public List<ForumPost> splitIntoPages(int to){

            return forumPostList.subList(0,to);
    }*/
}