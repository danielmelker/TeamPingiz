package com.academy.pingiz.user;


public enum ResponseType{
    VALID("Valid credentials."),
    INVALID_USER("Invalid username."),
    INVALID_PASSWORD("Wrong password. ");

    private String message;

    ResponseType(String message){
        this.message= message;
    }

    String getMessage(){
        return message;
    }
}
