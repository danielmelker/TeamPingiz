package com.academy.pingiz.forum;

import org.springframework.stereotype.Service;

@Service
public class ForumPost {

    private String datePosted;
    private String timePosted;
    private String thePost;
    private int postNum;
    private String postedBy;

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
