package com.wsb.millionapp.to;

import java.time.LocalDate;

public record UserResultDto(
        String firstName,
        String lastName,
        String username,
        int result,
        LocalDate date
) {}
