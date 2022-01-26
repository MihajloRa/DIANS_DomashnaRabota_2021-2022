package com.example.tech_prototype.Web;

import com.example.tech_prototype.Config.JwtTokenUtil;
import com.example.tech_prototype.Model.Exceptions.InvalidArgumentException;
import com.example.tech_prototype.Model.Exceptions.PasswordsDoNotMatchException;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password){
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(user);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String repeatedPassword,
                                   @RequestParam String email){
        try{
            User u = this.userService.register(username, password, repeatedPassword, email);
            return ResponseEntity.ok().body(u);
        } catch (InvalidArgumentException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
