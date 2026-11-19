package com.example.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Centeral {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Hunter";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request){
        return "Home Page "+request.getSession().getId();
    }

    @GetMapping("/admin/about")
    public String about(){
        return "About page";
    }

    @GetMapping("/public/demo")
    public String demo(){
        return "Public demo Page";
    }

}
