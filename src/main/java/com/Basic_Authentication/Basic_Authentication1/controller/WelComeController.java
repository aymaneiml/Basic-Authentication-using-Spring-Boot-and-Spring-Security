package com.Basic_Authentication.Basic_Authentication1.controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelComeController {

    @GetMapping("/greeting")
    public String greeting(Authentication auth){
        String userName=auth.getName();
        return "Spring Security In-memory Authentication Example - Welcome " + userName;
    }

}
