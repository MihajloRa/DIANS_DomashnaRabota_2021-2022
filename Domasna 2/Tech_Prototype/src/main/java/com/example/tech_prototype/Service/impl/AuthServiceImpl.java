package com.example.tech_prototype.Service.impl;

import com.example.tech_prototype.Model.Exceptions.InvalidArgumentException;
import com.example.tech_prototype.Model.Exceptions.InvalidUserCredentialsException;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Repository.UserRepository;
import com.example.tech_prototype.Service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        if(username==null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
