package com.example.tech_prototype.Web;


import com.example.tech_prototype.Model.Exceptions.InvalidUserCredentialsException;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    private final AuthService authService;

    @Autowired
    LoginController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping
    ResponseEntity<?> loginUser(@Valid @RequestBody User u){
        try{
            User user = this.authService.login(u.getUsername(), u.getPassword()).get();
            return ResponseEntity.ok().body(user);
        } catch(InvalidUserCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}