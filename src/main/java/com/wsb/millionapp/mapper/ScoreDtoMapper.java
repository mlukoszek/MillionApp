package com.wsb.millionapp.mapper;

import com.wsb.millionapp.domain.Result;
import com.wsb.millionapp.to.ScoreDto;

import java.time.LocalDate;

public class ScoreDtoMapper {
    static ScoreDto map(Result score) {
        Long id = score.getId();
        LocalDate date = score.getDate();
        int result = score.getResult();
        Long userId = score.getUserId();
        return new ScoreDto(id, date, result, userId);
    }
}

