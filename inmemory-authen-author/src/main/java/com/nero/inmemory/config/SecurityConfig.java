package com.nero.inmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //TODO 在内存中配置用户及角色(方式一)
    //    auth.inMemoryAuthentication()
    //            .withUser("admin").password("$2a$10$e4xCb3dc2i.0jrK0tffQYOkd5w96D/bO/w9.7098lfwTHQMkUKJ1a").roles("ADMIN", "USER").and()//注意：配置密码需要加上对应PasswordEncoder的
    //            .withUser("user").password("13ba4d7f782c60f6f9a22a22a29e52d2c854dd2b81454e0a8c4929be396715634cbeb296af49cc7a").roles("USER");
    //}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")//指定ADMIN角色才能访问/admin/**
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/app/**").anonymous()//匿名用户可以访问/app/**
                .anyRequest().authenticated().and()//其他所有请求进行安全认证
                .formLogin();//开启页面登录
    }

    //TODO 在内存中配置用户及角色(方式二)
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN", "USER").build());
        manager.createUser(User.withUsername("user").password("{noop}user").roles("USER").build());
        return manager;
    }

    //配置PasswordEncoder
    //@Bean
    //public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    //}
}
