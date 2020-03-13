package com.academy.pingiz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public AuthResponse logIn(String username, String password){

        Optional<User> u = userRepo.findByUsername(username);
        if(u.isEmpty()){
            return AuthResponse.of(ResponseType.INVALID_USER,username);
        } else if( u.get().checkPassword(password)){
            return AuthResponse.of(ResponseType.VALID,username);
        } else {
            return AuthResponse.of(ResponseType.INVALID_PASSWORD,username);
        }
    }

    public Optional<User> getUser(String username){
        return userRepo.findByUsername(username);
    }

    public boolean register(User user){
        if(userRepo.findByUsername(user.getUsername()).isEmpty()){
            userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean register(User... users){
        boolean b= true;
        for(User u:users){
            if(!register(u))
                b=false;
        }
        return b;
    }

    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    public void update(User u) {
        userRepo.save(u);
    }
}
