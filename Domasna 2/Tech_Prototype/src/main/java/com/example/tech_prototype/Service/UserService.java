package com.example.tech_prototype.Service;

import com.example.tech_prototype.Model.Role;
import com.example.tech_prototype.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    void save(User user);
    User loadUserByUsername(String username);
    User register(String username, String password, String repeatPassword, String email, Role userRole);
}
