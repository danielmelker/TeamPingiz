package com.academy.pingiz.forum;

import com.academy.pingiz.user.User;
import org.springframework.stereotype.Service;

@Service
public class ForumPost {

    private String datePosted;
    private String timePosted;
    private String thePost;
    private int postNum;
    private String postedBy;

    private User poster;


    public String getDatePosted() {
        return datePosted;
    }

    public String getTimePosted() {
        return timePosted;
    }

    public String getThePost() {
        return thePost;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public ForumPost(String datePosted, String timePosted, String thePost, User poster, int postNum){

        this.datePosted = datePosted;
        this.timePosted = timePosted;
        this.thePost = thePost;
        this.postNum = postNum;
        this.postedBy = poster.getUsername();
        this.poster = poster;
    }

    public int getPostNum() {
        return postNum;
    }

    public User getPoster(){ return poster; }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public ForumPost(){

    }



}
