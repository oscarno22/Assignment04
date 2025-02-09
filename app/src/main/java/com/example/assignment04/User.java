package com.example.assignment04;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String email;
    String role;

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User() {
    }
}
