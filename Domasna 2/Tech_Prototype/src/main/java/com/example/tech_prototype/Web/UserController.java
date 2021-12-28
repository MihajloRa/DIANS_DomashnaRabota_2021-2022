package com.example.tech_prototype.Web;

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
@RequestMapping(value = "/api/user")
public class UserController {
    UserService userService;
    RouteService routeService;

    @Autowired
    UserController(UserService userService, RouteService routeService) {
        this.userService = userService;
        this.routeService = routeService;
    }

    @GetMapping
    ResponseEntity<User> getUser(HttpServletRequest request){
        User u = (User) request.getSession().getAttribute("user");
        return ResponseEntity.ok().body(u);
    }

    @GetMapping("/{username}/routes")
    ResponseEntity<List<Route>> getUserRoutes(@PathVariable String username){
        List<Route> routes = this.routeService.getRoutes(username);
        return ResponseEntity.ok().body(routes);
    }

    @PostMapping("/{username}/routes")
    ResponseEntity<Route> addUserRoute(@PathVariable String username,
                                       @RequestParam String latitude,
                                       @RequestParam String longitude,
                                       @RequestParam String [] preferences) {
        User user = this.userService.loadUserByUsername(username);
        Geometry start = new GeometryFactory().createPoint(new Coordinate
                (Double.parseDouble(latitude), Double.parseDouble(longitude)));
        Route route = this.routeService.addRoute(user, start, preferences);
        return ResponseEntity.ok().body(route);
    }
}