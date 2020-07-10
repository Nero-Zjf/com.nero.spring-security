package com.nero.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/form")
public class FormController {

    @GetMapping("/{form}")
    public String form(@PathVariable String form) {
        return  form;
    }

    @GetMapping("/hi")
    @ResponseBody
    public String hi(){
        return "hi test";
    }
}
