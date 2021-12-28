package com.example.tech_prototype.Service;

import com.example.tech_prototype.Model.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String username, String password);
}
