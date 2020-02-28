package com.example.demo.forum;

import com.example.demo.user.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ForumPost {

    public String datePosted;
    public String timePosted;
    public String thePost;
    public int postNum;
    public String postedBy;

    public ForumPost(String datePosted, String timePosted, String thePost, String poster, int postNum){

        this.datePosted = datePosted;
        this.timePosted = timePosted;
        this.thePost = thePost;
        this.postNum = postNum;
        this.postedBy = poster;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public ForumPost(){

    }



}
