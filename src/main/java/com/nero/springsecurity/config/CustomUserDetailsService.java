package com.nero.springsecurity.config;

import com.nero.springsecurity.jpa.UserRepository;
import com.nero.springsecurity.po.Role;
import com.nero.springsecurity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found user haha");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role :
                user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword()
                , grantedAuthorities);
    }
}
