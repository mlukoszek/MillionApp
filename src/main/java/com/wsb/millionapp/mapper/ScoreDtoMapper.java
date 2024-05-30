package com.wsb.millionapp.mapper;

import com.wsb.millionapp.domain.Score;
import com.wsb.millionapp.to.ScoreDto;

import java.time.LocalDate;

public class ScoreDtoMapper {
    static ScoreDto map(Score score) {
        Long id = score.getId();
        LocalDate date = score.getDate();
        int result = score.getResult();
        Long userId = score.getUserId();
        return new ScoreDto(id, date, result, userId);
    }
}

