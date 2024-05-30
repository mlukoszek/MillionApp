package com.wsb.millionapp.service;

import com.wsb.millionapp.domain.Question;
import com.wsb.millionapp.mapper.QuestionsDtoMapper;
import com.wsb.millionapp.repository.QuestionsRepository;
import com.wsb.millionapp.to.QuestionDto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

@Service
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final QuestionsDtoMapper questionsDtoMapper;

    public QuestionsService(QuestionsRepository questionsRepository, QuestionsDtoMapper questionsDtoMapper) {
        this.questionsRepository = questionsRepository;
        this.questionsDtoMapper = questionsDtoMapper;
    }

    public QuestionDto drawQuestion(int difficulty) {
        List<Question> questionsList = questionsRepository.findAllByDifficulty(difficulty);
        Collections.shuffle(questionsList);
        return questionsDtoMapper.map(questionsList.get(0));
    }

    public Boolean evaluate(String userAnswer, String rightAnswer) {
        return userAnswer.equals(rightAnswer);
    }
}


