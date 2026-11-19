package com.example.SpringSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] xyz){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
        System.out.println(encoder.encode("admin1234"));
    }
}
