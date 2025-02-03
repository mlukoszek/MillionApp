package com.wsb.millionapp.to;

import lombok.*;
import org.springframework.stereotype.Component;


@Component
@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto{
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String password;
    private String role;
    private boolean active;
}