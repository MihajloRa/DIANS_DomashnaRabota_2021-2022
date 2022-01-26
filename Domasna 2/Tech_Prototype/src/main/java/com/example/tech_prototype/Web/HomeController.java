package com.example.tech_prototype.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( value = "/")
public class HomeController {
    @GetMapping
    public String index() { return "index"; }
}
