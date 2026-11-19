package com.example.SpringSecurity.service;

import com.example.SpringSecurity.model.Users;
import com.example.SpringSecurity.repo.UserDetailsRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDetailsRepo repo;
    public BCryptPasswordEncoder bCrypt=new BCryptPasswordEncoder(12);

    public UserService(UserDetailsRepo repo){
        this.repo=repo;
    }
    public void add(Users users) {
        users.setPassword(bCrypt.encode(users.getPassword()));
        repo.save(users);
    }
}
