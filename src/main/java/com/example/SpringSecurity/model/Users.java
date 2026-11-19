package com.example.SpringSecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private long id;
    private String userName;
    private String password;
    private String roll;

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
