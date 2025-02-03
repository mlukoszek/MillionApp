package com.wsb.millionapp.config;

import com.wsb.millionapp.service.AuthorisationService;
import com.wsb.millionapp.service.UsersService;
import com.wsb.millionapp.to.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CustomUserDetailsManager implements UserDetailsService {
        private final UsersService usersService;
        private final AuthorisationService authorisationService;

        public CustomUserDetailsManager(UsersService usersService, AuthorisationService authorisationService) {
            this.usersService = usersService;
            this.authorisationService = authorisationService;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<UserDto> userDto = usersService.findUserByUsername(username);
            Long userId = userDto.map(UserDto::getId).orElse(null);
            String authPassword = authorisationService.findPasswordByUsersId(userId);
            return usersService.findUserByUsername(username)
                    .filter(UserDto::isActive)
                    .map(user -> org.springframework.security.core.userdetails.User
                            .withUsername(user.getUsername())
                            .password(authPassword)
                            .roles(user.getRole())
                            .build()
                    )
                    .orElseThrow(() -> new UsernameNotFoundException("User not found or inactive: " + username));
        }
}