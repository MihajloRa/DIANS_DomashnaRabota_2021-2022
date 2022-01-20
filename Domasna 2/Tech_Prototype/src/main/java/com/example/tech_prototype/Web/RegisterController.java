package com.example.tech_prototype.Web;


import com.example.tech_prototype.Model.Exceptions.InvalidArgumentException;
import com.example.tech_prototype.Model.Exceptions.PasswordsDoNotMatchException;
import com.example.tech_prototype.Model.Role;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<?> registerUser(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String repeatedPassword,
                                      @RequestParam String email,
                                      @RequestParam Role role){
        try{
            User u = this.userService.register(username, password, repeatedPassword, email, role);
            return ResponseEntity.ok().body(u);
        } catch (InvalidArgumentException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}