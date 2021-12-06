package com.example.tech_prototype.Web;

import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {
    UserService userService;

    @GetMapping(value = "/client/{id}")
    public User getLogedIn(@PathVariable Long id) {
        return userService.getUser(id);
    }

}