package com.example.tech_prototype.Service;

import com.example.tech_prototype.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    User getUser(String id);
}
