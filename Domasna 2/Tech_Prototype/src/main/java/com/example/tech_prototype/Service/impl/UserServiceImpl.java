package com.example.tech_prototype.Service.impl;

import com.example.tech_prototype.Model.Exceptions.EmailAlreadyExistsException;
import com.example.tech_prototype.Model.Exceptions.InvalidUsernameOrPasswordException;
import com.example.tech_prototype.Model.Exceptions.PasswordsDoNotMatchException;
import com.example.tech_prototype.Model.Exceptions.UsernameAlreadyExistsException;
import com.example.tech_prototype.Model.Role;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Repository.UserRepository;
import com.example.tech_prototype.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user){ this.userRepository.save(user); }

    @Override
    public User loadUserByUsername(String username) { return this.userRepository.findByUsername(username).get();}

    @Override
    public User register(String username, String password, String repeatPassword, String email, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        if(this.userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyExistsException(email);
        User user = new User(username,passwordEncoder.encode(password), email,userRole);
        return userRepository.save(user);
    }

}
