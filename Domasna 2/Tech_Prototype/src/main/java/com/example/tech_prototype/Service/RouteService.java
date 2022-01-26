package com.example.tech_prototype.Service;

import com.example.tech_prototype.Model.Point;
import com.example.tech_prototype.Model.Route;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    List<Route> getRoutes(String username);
    Route addRoute(String username, Geometry start, List<String> preferences);
    Route getRoute(Long id);
    Route updateStatus(Long id, String status);
    Route updateDestinations(Long id, double longitude, double latitude);
}
