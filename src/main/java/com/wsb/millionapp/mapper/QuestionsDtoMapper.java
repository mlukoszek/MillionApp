package com.wsb.millionapp.mapper;

import com.wsb.millionapp.domain.Question;
import com.wsb.millionapp.repository.QuestionsRepository;
import com.wsb.millionapp.to.QuestionDto;
import org.springframework.stereotype.Service;

@Service
public class QuestionsDtoMapper {
    public QuestionsRepository questionsRepository;

    QuestionsDtoMapper(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public QuestionDto map(Question questions) {
        QuestionDto dto = new QuestionDto();
        dto.setQuestionId(questions.getQuestionId());
        dto.setQuestionBody(questions.getQuestionBody());
        dto.setAnswerA(questions.getAnswerA());
        dto.setAnswerB(questions.getAnswerB());
        dto.setAnswerC(questions.getAnswerC());
        dto.setAnswerD(questions.getAnswerD());
        dto.setRightAnswer(questions.getRightAnswer());
        dto.setDifficulty(questions.getDifficulty());

        return dto;
    }
}
