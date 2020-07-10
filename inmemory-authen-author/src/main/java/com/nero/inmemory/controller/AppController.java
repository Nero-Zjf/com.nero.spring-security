package com.nero.inmemory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {
    @GetMapping("/hi")
    public String hi() {
        return "hi anonymous";
    }

    //@PostMapping("/login")
    //public String login() {
    //    return "ok";
    //}
}
