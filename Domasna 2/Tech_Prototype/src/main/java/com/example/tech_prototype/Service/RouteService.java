package com.example.tech_prototype.Service;

import com.example.tech_prototype.Model.Point;
import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.User;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RouteService {
    List<Route> getRoutes(String username);
    Route addRoute(User user, Geometry start, String [] preferences);
    Optional<Route> getRoute(Long id);
    Route updateStatus(Long id, String status);
    Route updateDestinations(Long id, Point p);
}
