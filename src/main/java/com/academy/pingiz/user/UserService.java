package com.academy.pingiz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public AuthResponse logIn(String username, String password){

        User u = userRepo.findByUsername(username);
        if(u == null){
            return AuthResponse.of(ResponseType.INVALID_USER,username);
        } else if( u.checkPassword(password)){
            return AuthResponse.of(ResponseType.VALID,username);
        } else {
            return AuthResponse.of(ResponseType.INVALID_PASSWORD,username);
        }
    }

    public User getUser(String username){
        return userRepo.findByUsername(username);
    }

    public boolean register(User user){
        return userRepo.add(user);
    }

    public boolean register(User... users){
        boolean b= true;
        for(User u:users){
            if(!userRepo.add(u))
                b=false;
        }
        return b;
    }



    public Set<User> getUsers() {
        return userRepo.getAllUsers();
    }
}
