package com.wsb.millionapp.controller;

import com.wsb.millionapp.service.AudienceVoteService;
import com.wsb.millionapp.service.FiftyFiftyService;
import com.wsb.millionapp.service.QuestionsService;
import com.wsb.millionapp.to.QuestionDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wsb.millionapp.controller.lifeLines.AudienceVoteDto;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class LifelinesController {
    private QuestionsService questionsService;
    private AudienceVoteService audienceVoteService;
    private FiftyFiftyService fiftyFiftyService;

    @PostMapping("/audienceVote")
    public ResponseEntity<AudienceVoteDto> simulateAudienceVote(@RequestParam int questionId) {
        QuestionDto questionDto = questionsService.findQuestionById(questionId);
        if (null != questionDto) {
            AudienceVoteDto audienceVoteDto = audienceVoteService.calculateAudienceVotes(questionDto);
            return ResponseEntity.ok(audienceVoteDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/fiftyFifty")
    public ResponseEntity<QuestionDto> fiftyFiftyDraw(@RequestParam int questionId) {
        QuestionDto questionDto = questionsService.findQuestionById(questionId);
        if (null != questionDto) {
            QuestionDto twoAnswersQuestionDto = fiftyFiftyService.deleteTwoAnswers(questionDto);
            return ResponseEntity.ok(twoAnswersQuestionDto);
        }
        return ResponseEntity.notFound().build();
    }
}