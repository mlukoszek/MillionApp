package com.wsb.millionapp.service;

import com.wsb.millionapp.repository.ScoreRepository;
import com.wsb.millionapp.domain.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final UsersService usersService;

    public ScoreService(ScoreRepository scoreRepository, UsersService usersService) {
        this.scoreRepository = scoreRepository;
        this.usersService = usersService;
    }

//    public List<ScoreDto> findAllByUser(String userName) {
//        Optional<UserDto> userDto = usersService.findUserByUsername(userName);
//        List<Score> scoreList = scoreRepository.findAllByUserId(userDto.ifPresent(););
//        return scoreList.stream()
//                .map(ScoreDtoMapper::map)
//                .collect(Collectors.toList());
//    }

    public Score saveResult(Score score) {
        return scoreRepository.save(score);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }
}
