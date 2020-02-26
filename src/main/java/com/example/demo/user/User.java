package com.example.demo.user;

import static com.google.common.base.Preconditions.checkNotNull;

public class User {
    private static final long serialVersion = 1L;


    private final String username;
    private String password;

    private User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public static User of(String username, String password, String email) {
        checkNotNull(username);
        checkNotNull(password);

        return new User(username, password);
    }

    public String getUsername(){
        return username;
    }
}
