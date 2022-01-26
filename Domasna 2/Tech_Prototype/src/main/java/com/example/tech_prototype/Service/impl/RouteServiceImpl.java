package com.example.tech_prototype.Service.impl;

import com.example.tech_prototype.Model.DTOs.DestinationCoordinates;
import com.example.tech_prototype.Model.Exceptions.InvalidArgumentException;
import com.example.tech_prototype.Model.Point;
import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.Status;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Repository.PointRepository;
import com.example.tech_prototype.Repository.RouteRepository;
import com.example.tech_prototype.Repository.UserRepository;
import com.example.tech_prototype.Service.RouteService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Coordinates;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    @Autowired
    RouteServiceImpl(RouteRepository repository, PointRepository pointRepository, UserRepository userRepository){
        this.routeRepository = repository;
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Route> getRoutes(String username) {
        User u = this.userRepository.findByUsername(username).orElseThrow(InvalidArgumentException::new);
        return this.routeRepository.findAllByUser(u);
    }

    @Override
    public Route addRoute(String username, Geometry start, List<String> preferences){
        List<Point> viableDestinations = new ArrayList<>();
        User user = this.userRepository.findByUsername(username).orElseThrow(InvalidArgumentException::new);
        for(String s : preferences){
            viableDestinations.addAll(this.pointRepository.findAllByTourismContaining(s));
        }

        ArrayList<Point> routeDestinations = viableDestinations.stream().filter(point -> ((point.getGeom().distance(start) / (180 * Math.PI)) * 6371) < 3).
                collect(Collectors.toCollection(ArrayList::new));

        user.getUser_routes().add(new Route(user, start, routeDestinations));
        this.userRepository.save(user);
        return this.routeRepository.save(new Route(user, start, routeDestinations));
    }

    @Override
    public Route getRoute(Long id) {
        return this.routeRepository.findById(id).orElseThrow(InvalidArgumentException::new);
    }

    @Override
    public Route updateStatus(Long id, String status) {
        Route route = this.routeRepository.findById(id).orElseThrow(InvalidArgumentException::new);
        Status s;
        switch (status) {
            case "ONGOING":
                s = Status.ONGOING;
                break;
            case "FINISHED":
                s = Status.FINISHED;
                break;
            default:
                s = Status.PAUSED;
        }
        route.setS(s);
        return this.routeRepository.save(route);
    }

    @Override
    public Route updateDestinations(Long id, double latitude, double longitude){
        Route route = this.routeRepository.findById(id).orElseThrow(InvalidArgumentException::new);
        Geometry toDelete = new GeometryFactory().createPoint(new Coordinate
                (latitude, longitude));
        ArrayList<Point> updatedDestinations = route.getDestinations().stream().dropWhile(p -> p.getGeom().distance(toDelete) / (180 * Math.PI) * 6371 < 0.05)
                .collect(Collectors.toCollection(ArrayList::new));
        route.setDestinations(updatedDestinations);
        return this.routeRepository.save(route);
    }
}
