package com.nero.customloginpage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HiController {
        @GetMapping("/hi")
        public String hi() {
            return "hi spring security";
        }

        //@PostMapping("/login")
        //public String login() {
        //    return "ok";
        //}
}
