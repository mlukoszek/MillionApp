package com.wsb.millionapp.mapper;

import com.wsb.millionapp.to.UserResultDto;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultDtoMapper {

    public List<UserResultDto> mapToDtoList(List<Object[]> results) {
        return results.stream()
                .map(obj -> new UserResultDto(
                        (String) obj[0],  // firstName
                        (String) obj[1],  // lastName
                        (String) obj[2],  // username
                        (int) obj[3],     // result
                        obj[4] instanceof Date ? ((Date) obj[4]).toLocalDate() : null
                ))
                .collect(Collectors.toList());
    }
}
