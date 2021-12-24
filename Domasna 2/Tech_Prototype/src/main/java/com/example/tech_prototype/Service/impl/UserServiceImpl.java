package com.example.tech_prototype.Service.impl;

import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Repository.UserRepository;
import com.example.tech_prototype.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository repository;

    @Autowired
    UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User getUser(String username) { return repository.findById(username).get(); }

    @Override
    public void save(User user){ this.repository.save(user); }
}
