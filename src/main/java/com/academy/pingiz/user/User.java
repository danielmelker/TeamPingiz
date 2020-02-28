package com.academy.pingiz.user;

import static com.google.common.base.Preconditions.checkNotNull;

public class User {
    private static final long serialVersion = 1L;

    private static long lastId=1;


    private final String username;
    private String password;
    private long id;

    private String description;
    private String academyClass;
    private String fileURL;

    public String getAcademyClass() {
        return academyClass;
    }

    public void setAcademyClass(String academyClass) {
        this.academyClass = academyClass;
    }

    private User(String username, String password) {
        this.username = username;
        this.password = password;
        this.description = "no description entered...";
        this.academyClass = "not specified";

        this.fileURL="https://upload.wikimedia.org/wikipedia/commons/4/4e/Shakehand1.jpg";

        this.id = lastId;
        lastId++;

    }

    public static User of(String username, String password) {
        checkNotNull(username);
        checkNotNull(password);

        return new User(username, password);
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
