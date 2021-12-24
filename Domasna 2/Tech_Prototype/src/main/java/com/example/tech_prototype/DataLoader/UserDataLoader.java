package com.example.tech_prototype.DataLoader;

import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public UserDataLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new User("Micho", "mikica420", "mihajlorasajkovski@gmail.com"));
    }
}