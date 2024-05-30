package com.wsb.millionapp.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto{
    private Long id;
    private String username;
    private String password;
    private String role;

    public Long getId() {
        return id;
    }
}