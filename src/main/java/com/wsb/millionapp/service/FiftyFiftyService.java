package com.wsb.millionapp.service;

import com.wsb.millionapp.to.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.wsb.millionapp.service.AudienceVoteService.letterToInt;

@Service
public class FiftyFiftyService {

    public QuestionDto deleteTwoAnswers(QuestionDto questionDto) {
        int rightAnswer = letterToInt(questionDto.getRightAnswer());
        Random random = new Random();
        int randomDraw;
        do {
            randomDraw = random.nextInt(4);
        }
        while (randomDraw == rightAnswer);

        for (int i = 0; i < 4; i++) {
            if (i != rightAnswer && i != randomDraw) {
                switch (i) {
                    case 0:
                        questionDto.setAnswerA("");
                        break;
                    case 1:
                        questionDto.setAnswerB("");
                        break;
                    case 2:
                        questionDto.setAnswerC("");
                        break;
                    case 3:
                        questionDto.setAnswerD("");
                        break;
                }
            }
        }
        return questionDto;
    }
}

