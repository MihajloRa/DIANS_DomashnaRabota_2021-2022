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
@RequestMapping(value = "/api/route")
public class RouteController {
    UserService userService;
    RouteService routeService;

    @Autowired
    RouteController(UserService userService, RouteService routeService) {
        this.userService = userService;
        this.routeService = routeService;
    }

    @GetMapping
    ResponseEntity<User> getUser(HttpServletRequest request){
        User u = (User) request.getSession().getAttribute("user");
        return ResponseEntity.ok().body(u);
    }

    @GetMapping("/{id}")
    ResponseEntity<Route> getRoute(@PathVariable String id){
        Route route = this.routeService.getRoute(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(route);
    }

    @PutMapping("/{id}")
    ResponseEntity<Route> updateStatus(@PathVariable String id,@RequestParam String updateStatus){
        Route route = this.routeService.updateStatus(Long.parseLong(id), updateStatus);
        return ResponseEntity.ok().body(route);
    }

    @PutMapping("/{id}/destination")
    ResponseEntity<Route> updateDestinations(@PathVariable String id, @RequestBody Point p){
        Route route = this.routeService.updateDestinations(Long.parseLong(id), p);
        return ResponseEntity.ok().body(route);
    }
}