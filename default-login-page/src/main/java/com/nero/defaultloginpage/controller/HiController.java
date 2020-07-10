package com.nero.defaultloginpage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @GetMapping("/hi")
    public String hi() {
        return "hi spring security";
    }
}
