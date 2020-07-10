# 使用Spring Security进行Http-Basic认证
spring security 默认开启了‘默认登录页面’认证，因此默认下会跳转到登录页面，而不是Http-basic认证。
配置方案：
继承WebSecurityConfigurerAdapter并覆盖configure(HttpSecurity http)方法。