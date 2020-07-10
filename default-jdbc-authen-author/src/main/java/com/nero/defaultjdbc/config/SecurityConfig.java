package com.nero.defaultjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

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

    //TODO 使用
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        userDetailsService.setDataSource(dataSource);
        //userDetailsService.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN","USER").build());
        return userDetailsService;
    }

    //配置PasswordEncoder
    //@Bean
    //public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    //}
}
