package com.wsb.millionapp.config;

import com.wsb.millionapp.service.UsersService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsManager implements UserDetailsService {
    private final UsersService usersService;

    public CustomUserDetailsManager(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersService.findUserByUsername(username)
                //.filter(user -> user.isActive())
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found or inactive: " + username));
    }
}