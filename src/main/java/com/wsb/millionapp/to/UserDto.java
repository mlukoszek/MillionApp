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
    private String firstName;
    private String lastName;
    private String username;
    private int age;
    private String role;
    private boolean active;
}