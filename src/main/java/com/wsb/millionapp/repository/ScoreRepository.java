package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScoreRepository extends CrudRepository<Score, Long> {
    List<Score> findAll();

    Score save(Score score);


}
