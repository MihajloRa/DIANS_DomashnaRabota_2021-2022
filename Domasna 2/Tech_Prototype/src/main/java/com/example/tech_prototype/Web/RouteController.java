package com.example.tech_prototype.Web;

import com.example.tech_prototype.Model.Point;
import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Service.RouteService;
import com.example.tech_prototype.Service.UserService;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/route")
public class RouteController {
    UserService userService;
    RouteService routeService;

    @Autowired
    RouteController(UserService userService, RouteService routeService) {
        this.userService = userService;
        this.routeService = routeService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<Route> getRoute(@PathVariable String id){
        Route route = this.routeService.getRoute(Long.parseLong(id));
        return ResponseEntity.ok().body(route);
    }

    @PostMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<Route> updateStatus(@PathVariable String id,@RequestParam String updateStatus){
        Route route = this.routeService.updateStatus(Long.parseLong(id), updateStatus);
        return ResponseEntity.ok().body(route);
    }

    @PostMapping(value = "/{id}/destination", produces = "application/json")
    ResponseEntity<Route> updateDestinations(@PathVariable String id,
                                             @RequestParam String latitude,
                                             @RequestParam String longitude){
        Route route = this.routeService.updateDestinations(Long.parseLong(id),
                Double.parseDouble(latitude), Double.parseDouble(longitude));
        return ResponseEntity.ok().body(route);
    }
}