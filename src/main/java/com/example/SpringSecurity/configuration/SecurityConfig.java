package com.example.SpringSecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //here disable the csrf 1.same site strict(this code in application properties), 2.session stateless
        //functional interface and lambda expression
        http.csrf(cutomizer->cutomizer.disable());//--- disabling the csrf
        http.authorizeHttpRequests(auth-> auth
//                .requestMatchers("/hello","/home","/home").permitAll()
//                .requestMatchers("/students").authenticated()
                  .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
        );
        //http.formLogin(Customizer.withDefaults());   --- for default login page
        http.httpBasic(Customizer.withDefaults());    //--- this used for avoid to show the login page in postman like testing tools
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//---if you the default login page means this gives again
                                                                                //and again login so comment the default login page code from security filterChain
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);//Dao->data access layer
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));//(NoOpPasswordEncoder.getInstance());
//        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

   /* @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> users=new ArrayList<>();
        UserDetails user1= User
                .withDefaultPasswordEncoder()
                .username("gayathiri")
                .password("2901")
                .roles("USER")
                .build();
        UserDetails user2= User
                .withDefaultPasswordEncoder()
                 .username("hunter")
                .password("1307")
                .roles("USER")
                .build();
        //return new InMemoryUserDetailsManager(user1,user2);  --- var arg method

        users.add(user1);
        users.add(user2);
        return new InMemoryUserDetailsManager(users);//  ---collections method
    }*/
}
