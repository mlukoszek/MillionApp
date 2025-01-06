package com.wsb.millionapp.controller.answerChecker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AnswerRequest {
    private String userAnswer;
    private String correctAnswer;
    private int difficulty;

}