package com.example.SpringSecurity.service;

import com.example.SpringSecurity.model.UserPrincipal;
import com.example.SpringSecurity.model.Users;
import com.example.SpringSecurity.repo.UserDetailsRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    UserDetailsRepo userRepo;
    public MyUserDetailsService(UserDetailsRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users users=userRepo.getByUserName(username);
        if(users==null){
            throw  new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(users);
    }

}
