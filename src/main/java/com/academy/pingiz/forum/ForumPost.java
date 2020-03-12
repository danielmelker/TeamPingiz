package com.academy.pingiz.forum;

import com.academy.pingiz.user.User;
import org.springframework.stereotype.Service;

import javax.persistence.*;


@Entity
public class ForumPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postNum;
    @Column(name = "DATE_POSTED")
    private String datePosted;
    @Column(name = "TIME_POSTED")
    private String timePosted;
    @Column(name = "THE_POST")
    private String thePost;
    @Column(name = "POSTED_BY")
    private String postedBy;

    //private User poster;


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
       // this.poster = poster;
    }

    public int getPostNum() {
        return postNum;
    }

   // public User getPoster(){ return poster; }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public ForumPost(){

    }



}
