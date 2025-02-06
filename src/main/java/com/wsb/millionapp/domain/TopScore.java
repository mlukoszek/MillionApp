package com.wsb.millionapp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class TopScore {

    private String firstName;
    private String lastName;
    private String username;
    private int result;
    private LocalDate date;

}
