package com.wsb.millionapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;
    @Column(name = "question_body")
    String questionBody;
    @Column(name = "answer_a")
    String answerA;
    @Column(name = "answer_b")
    String answerB;
    @Column(name = "answer_c")
    String answerC;
    @Column(name = "answer_d")
    String answerD;
    @Column(name = "right_answer")
    String rightAnswer;
    @Column(name = "difficulty")
    int difficulty;

    public Question(String questionBody, String answerA, String answerB, String answerC,
                    String answerD, String rightAnswer, int difficulty) {
        this.questionBody = questionBody;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.rightAnswer = rightAnswer;
        this.difficulty = difficulty;
    }
}
