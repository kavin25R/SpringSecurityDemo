package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.model.Users;
import com.example.SpringSecurity.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/add")
    public void addUser(@RequestBody Users users){
        userService.add(users);
    }
}
