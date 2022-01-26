package com.example.tech_prototype.Repository;

import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.Status;
import com.example.tech_prototype.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RestResource(exported = false)
@PreAuthorize("hasRole('ROLE_USER')")
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByUser(User user);
    List<Route> findByUserAndS(User user, Status s);
}
