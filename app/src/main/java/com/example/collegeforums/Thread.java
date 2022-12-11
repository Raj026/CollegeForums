package com.example.collegeforums;

import java.util.ArrayList;

public class Thread {

    public String title, comment, userName, userUid;
    public ArrayList<String> likes, comments;


    public Thread(){}

    public Thread(String title, String comment, String userName, String userUid, ArrayList<String> likes, ArrayList<String> comments) {
        this.title = title;
        this.comment = comment;
        this.userName = userName;
        this.userUid = userUid;
        this.likes = likes;
        this.comments = comments;
    }
}
