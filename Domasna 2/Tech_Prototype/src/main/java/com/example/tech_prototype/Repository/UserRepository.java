package com.example.tech_prototype.Repository;

import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByEmail(String email);
    boolean deleteByUsername(String username);
}
