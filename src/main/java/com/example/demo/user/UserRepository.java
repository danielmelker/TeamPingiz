package com.example.demo.user;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRepository {


    private Map<String, User> users;

    public UserRepository(){
        users = new HashMap<>();
    }

    public boolean add(User user){

        if(users.containsKey(user.getUsername())){
            return false; //username is taken.
        } else {
            users.put(user.getUsername(), user);
            return true;
        }
    }



    public Set<User> getAllUsers(){
        return new HashSet<User>(users.values());
    }

    public User findByUsername(String username){
        if(users.containsKey(username)){
            return users.get(username);
        } else {
            return null;
        }
    }
    
}
