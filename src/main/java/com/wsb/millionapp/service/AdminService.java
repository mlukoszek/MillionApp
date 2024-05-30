package com.wsb.millionapp.service;

import com.wsb.millionapp.domain.Question;
import com.wsb.millionapp.mapper.QuestionsDtoMapper;
import com.wsb.millionapp.repository.AdminRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AdminService {
    private AdminRepository adminRepository;
    private QuestionsDtoMapper questionsDtoMapper;

    public AdminService(AdminRepository adminRepository, QuestionsDtoMapper questionsDtoMapper) {
        this.adminRepository = adminRepository;
        this.questionsDtoMapper = questionsDtoMapper;
    }

    public Question addQuestion(Question question) {
        adminRepository.save(question);
        return question;
    }

    public List<Question> getQuestionsList() {
        return adminRepository.findAll();
    }

    public void deleteQuestion(Long id) {
        adminRepository.deleteById(id);
    }

    public List<Question> getQuestionsListByDifficulty(int difficulty) {
        return adminRepository.findAllByDifficulty(difficulty);
    }
}