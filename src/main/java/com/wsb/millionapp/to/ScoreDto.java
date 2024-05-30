package com.wsb.millionapp.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {
    private Long id;
    private LocalDate date;
    private int result;
    private Long userId;

}
