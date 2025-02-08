package com.wsb.millionapp.controller;

import com.wsb.millionapp.service.ResultService;
import com.wsb.millionapp.to.UserResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.wsb.millionapp.controller.QuestionsController.getLoggedUserName;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/getTopScores")
    @ResponseBody
    public List<UserResultDto> getTopScores() {
        List<UserResultDto> topScores = resultService.getAllResultsOrdered();
        return topScores;
    }

    @GetMapping("/getMyResults")
    @ResponseBody
    public List<UserResultDto> getMyResults() {
        String username = getLoggedUserName();
        return resultService.getResultsOrderedByUsername(username);
    }
}
