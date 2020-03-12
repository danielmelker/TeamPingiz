package com.academy.pingiz.forum;

import com.academy.pingiz.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class ForumPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postNum;
    @Column(name = "DATE_POSTED")
    private LocalDate datePosted;
    @Column(name = "TIME_POSTED")
    private LocalTime timePosted;
    @Column(name = "THE_POST")
    private String thePost;
    @Column(name = "POSTED_BY")
    private String postedBy;

    @ManyToOne
    private User poster;


    public LocalDate getDatePosted() {
        return datePosted;
    }

    public LocalTime getTimePosted() {
        return timePosted;
    }

    public String getThePost() {
        return thePost;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public ForumPost(LocalDate datePosted, LocalTime timePosted, String thePost, User poster){

        this.datePosted = datePosted;
        this.timePosted = timePosted;
        this.thePost = thePost;

        this.postedBy = poster.getUsername();
        this.poster = poster;
    }

    public long getPostNum() {
        return postNum;
    }

    public User getPoster(){ return poster; }

    public void setPostNum(long postNum) {
        this.postNum = postNum;
    }

    public ForumPost(){

    }



}
