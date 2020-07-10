package com.nero.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
public class LoginController {
    //@Autowired
    //private AuthenticationManager authenticationManager;

    //@PostMapping("/login")
    //public Object login(@RequestParam String username, @RequestParam String password) {
    //    // 1 创建UsernamePasswordAuthenticationToken
    //    UsernamePasswordAuthenticationToken token
    //            = new UsernamePasswordAuthenticationToken(username, password);
    //    // 2 认证
    //    Authentication authentication = this.authenticationManager.authenticate(token);
    //    // 3 保存认证信息
    //    SecurityContextHolder.getContext().setAuthentication(authentication);
    //    // 4 加载UserDetails
    //    UserDetails details = (UserDetails) authentication.getDetails();
    //    // 5 生成自定义token
    //    return tokenProvider.createToken(details);
    //}
}
