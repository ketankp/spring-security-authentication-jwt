package com.example.mpl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public String getUser(Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("ROle " + authentication.getAuthorities());
        return principal.getName();
    }
}
