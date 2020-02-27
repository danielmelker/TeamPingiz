package com.example.demo.user;

import static com.google.common.base.Preconditions.checkNotNull;

public class AuthResponse {

    private String message;
    private String username;
    private ResponseType type;

    private AuthResponse(ResponseType type, String username){
        this.type = type;
        this.username = username;

        switch(type){
            case VALID:
                this.message = type.getMessage() + " Logged in as "+ username + ".";
                break;
            case INVALID_USER:
                this.message = type.getMessage() + " User " + username + " does not exist.";
                break;

            case INVALID_PASSWORD:
                this.message = type.getMessage() ;
                break;
        }
    }

    public static AuthResponse of(ResponseType type, String username) {
        checkNotNull(type);
        checkNotNull(username);
        return new AuthResponse(type, username);
    }

    public boolean isValid(){
        return type == type.VALID;
    }
    public ResponseType getType(){return type; }
}

