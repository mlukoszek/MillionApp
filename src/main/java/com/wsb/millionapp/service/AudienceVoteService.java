package com.wsb.millionapp.service;

import com.wsb.millionapp.controller.lifeLines.AudienceVoteDto;
import com.wsb.millionapp.to.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AudienceVoteService {

    public AudienceVoteDto calculateAudienceVotes(QuestionDto questionDto) {
        int drawQuantity = 1000;
        double rightAnswerProbability = drawProbability(questionDto.getDifficulty());
        int rightAnswer = letterToInt(questionDto.getRightAnswer());

        int[] votes = new int[4];

        Random random = new Random();

        //Losowanie probabilistyczne
        for (int i = 0; i < drawQuantity; i++) {
            double vote = random.nextDouble();

            if (vote < rightAnswerProbability) {
                votes[rightAnswer]++;
            } else {
                int randomVote;
                do {
                    randomVote = random.nextInt(4);
                } while (randomVote == rightAnswer);
                votes[randomVote]++;
            }
        }
        return new AudienceVoteDto(votes[0], votes[1], votes[2], votes[3]);
    }

    private double drawProbability(int difficulty) {
        double rightAnswerProbability;

        Random random = new Random();
        double randomNumber = random.nextDouble();

        // Dla łatwych pytań (1-4): rozkład: 70% (0.7 szansy na trafienie), 50% (0.2), 30% (0.1)
        if (difficulty <= 4) {
            if (randomNumber < 0.7) {
                rightAnswerProbability = 0.7;
            } else if (randomNumber < 0.9) {
                rightAnswerProbability = 0.5;
            } else {
                rightAnswerProbability = 0.3;
            }
        }
        // Dla średnich pytań (5-8): rozkład: 70% (0.33), 50% (0.33), 30% (0.33)
        else if (difficulty <= 8) {
            if (randomNumber < 0.33) {
                rightAnswerProbability = 0.7;
            } else if (randomNumber < 0.66) {
                rightAnswerProbability = 0.5;
            } else {
                rightAnswerProbability = 0.3;
            }
        }
        // Dla trudnych pytań (9-12): rozkład: 30% (0.7), 50% (0.2), 70% (0.1)
        else {
            if (randomNumber < 0.7) {
                rightAnswerProbability = 0.3;
            } else if (randomNumber < 0.9) {
                rightAnswerProbability = 0.5;
            } else {
                rightAnswerProbability = 0.7;
            }
        }
        return rightAnswerProbability;
    }

    public static int letterToInt(String rightAnswer) {
        switch (rightAnswer) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                throw new IllegalArgumentException("Nieznana litera: " + rightAnswer);
        }
    }
}
