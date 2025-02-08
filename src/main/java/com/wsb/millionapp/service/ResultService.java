package com.wsb.millionapp.service;

import com.wsb.millionapp.mapper.ResultDtoMapper;
import com.wsb.millionapp.repository.ResultRepository;
import com.wsb.millionapp.domain.Result;
import com.wsb.millionapp.to.UserResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final ResultRepository scoreRepository;
    private final UsersService usersService;
    private final ResultDtoMapper resultDtoMapper;

    public ResultService(ResultRepository scoreRepository, UsersService usersService, ResultDtoMapper resultDtoMapper) {
        this.scoreRepository = scoreRepository;
        this.usersService = usersService;
        this.resultDtoMapper = resultDtoMapper;
    }

    public Result saveResult(Result score) {
        return scoreRepository.save(score);
    }

    public List<UserResultDto> getAllResultsOrdered() {
        List<Object[]> results = scoreRepository.findAllResultsOrderedNative();
        return resultDtoMapper.mapToDtoList(results);
    }

    public List<UserResultDto> getResultsOrderedByUsername(String username) {
        List<Object[]> results = scoreRepository.findMyResultsOrderedNative(username);
        return resultDtoMapper.mapToDtoList(results);
    }
}
