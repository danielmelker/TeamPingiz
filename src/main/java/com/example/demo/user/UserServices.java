package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepo;




    public AuthResponse logIn(String username, String password){

        return AuthResponse.of(ResponseType.VALID,username);

    }

    public void register(User user){
        userRepo.add(user);
    }



}
