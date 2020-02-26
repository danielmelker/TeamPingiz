package com.example.demo.user;

import static com.google.common.base.Preconditions.checkNotNull;

public class User {
    private static final long serialVersion = 1L;

    private static long lastId=1;


    private final String username;
    private String password;
    private long id;

    private User(String username, String password) {
        this.username = username;
        this.password = password;

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

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
