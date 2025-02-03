package com.wsb.millionapp.domain;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class NewUserDto{
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String password;
    private String role;
    private boolean active;
}