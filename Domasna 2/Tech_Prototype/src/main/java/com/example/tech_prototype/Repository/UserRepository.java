package com.example.tech_prototype.Repository;

import com.example.tech_prototype.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
}
