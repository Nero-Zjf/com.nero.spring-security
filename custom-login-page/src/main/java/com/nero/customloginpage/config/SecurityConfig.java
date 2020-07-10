package com.nero.customloginpage.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()//不拦截样式文件
                .anyRequest().authenticated().and()//其他所有请求进行安全认证
                .formLogin()//开启页面登录
                .loginPage("/login.html")//配置自定义的登录页面,Spring security会对应提供指定登录页面的一个POST请求映射用于验证登录信息
                .loginProcessingUrl("/login")//指定用于验证登录的url（由Spring security生成，这是只是配置url的名称，注意自定义的登录页面action指定的url需与此对应）
                //TODO 登录成功后的跳转地址（默认跳转到登录前访问的地址）
                //.defaultSuccessUrl("/index.html")
                //TODO 登录失败后的跳转地址（默认跳转到{loginpage}?error}）
                //.failureUrl("/error.html")
                //TODO 登录成功后的处理器（下面演示前后端分离中返回json数据）
                //.successHandler(new AuthenticationSuccessHandler() {
                //    @Override
                //    public void onAuthenticationSuccess(HttpServletRequest request,
                //                                        HttpServletResponse response,
                //                                        Authentication authentication) throws IOException, ServletException {
                //        response.setContentType("application/json;charset=UTF-8");
                //        response.getWriter().write("\"error_code\":\"0\",\"msg\":\"welcome\" " + authentication.getName());
                //    }
                //})
                //TODO 登录失败后的处理器（下面演示前后端分离中返回json数据）
                //.failureHandler(new AuthenticationFailureHandler() {
                //    @Override
                //    public void onAuthenticationFailure(HttpServletRequest request,
                //                                        HttpServletResponse response,
                //                                        AuthenticationException exception) throws IOException, ServletException {
                //exception包含了验证的异常信息
                //        response.setStatus(401);
                //        response.setContentType("application/json;charset=UTF-8");
                //        response.getWriter().write("\"error_code\":\"-1\",\"msg\":\"" + exception.getMessage() + "\"");
                //    }
                //})
                .permitAll()//不拦截登录页面
                .and()
                .csrf().disable();//关闭csrf，为什么要关闭？
    }
}
