package com.example.tech_prototype.Service.impl;

import com.example.tech_prototype.Model.Point;
import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.Status;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Repository.PointRepository;
import com.example.tech_prototype.Repository.RouteRepository;
import com.example.tech_prototype.Service.RouteService;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final PointRepository pointRepository;

    @Autowired
    RouteServiceImpl(RouteRepository repository, PointRepository pointRepository){
        this.routeRepository = repository;
        this.pointRepository = pointRepository;
    }

    @Override
    public List<Route> getRoutes(String username) { return routeRepository.findAllByUserUsername(username); }

    @Override
    public Route addRoute(User user, Geometry start, String [] preferences){
        List<Point> viableDestinations = new ArrayList<>();
        for(String s : preferences){
            viableDestinations.addAll(this.pointRepository.findAllByTourismIsContaining(s));
        }

        ArrayList<Point> routeDestinations = viableDestinations.stream().filter(point -> ((point.getGeom().distance(start) / (180 * Math.PI)) * 6371) < 3).
                collect(Collectors.toCollection(ArrayList::new));

        return this.routeRepository.save(new Route(user, start, routeDestinations));
    }

    @Override
    public Optional<Route> getRoute(Long id) {
        return this.routeRepository.findById(id);
    }

    @Override
    public Route updateStatus(Long id, String status) {
        Route route = this.routeRepository.findById(id).get();
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
    public Route updateDestinations(Long id, Point p){
        Route route = this.routeRepository.findById(id).get();
        ArrayList<Point> updatedDestinations = route.getDestinations().stream().filter(point -> point.equals(p)).collect(Collectors.toCollection(ArrayList::new));
        route.setDestinations(updatedDestinations);
        return this.routeRepository.save(route);
    }
}
