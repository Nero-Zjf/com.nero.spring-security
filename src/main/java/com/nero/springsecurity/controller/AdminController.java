package com.nero.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/home")
    public String getHome() {
        return "welcome admin home";
    }
}
