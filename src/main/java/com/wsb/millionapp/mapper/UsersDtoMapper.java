package com.wsb.millionapp.mapper;

import com.wsb.millionapp.domain.Authorisation;
import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.to.UserDto;

public class UsersDtoMapper {
    public static UserDto map(User user) {
        Long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUsername();
        int age = user.getAge();
        String role = user.getRole();
        boolean active = user.isActive();
        return new UserDto(id, firstName, lastName,username, age, role, active);
    }
}

