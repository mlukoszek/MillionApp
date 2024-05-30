package com.wsb.millionapp.mapper;

import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.to.UserDto;

public class UsersDtoMapper {
    public static UserDto map(User user) {
        Long id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String role = user.getRole();
        return new UserDto(id, username, password, role);
    }
}

