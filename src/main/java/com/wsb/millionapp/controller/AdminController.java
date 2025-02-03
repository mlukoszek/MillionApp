package com.wsb.millionapp.controller;

import com.wsb.millionapp.repository.UsersRepository;
import com.wsb.millionapp.service.AdminService;
import com.wsb.millionapp.service.UsersService;
import com.wsb.millionapp.to.QuestionDto;
import com.wsb.millionapp.domain.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UsersRepository usersRepository;
    private final UsersService usersService;
    private AdminService adminService;
    private QuestionDto questionDto;

    public AdminController(AdminService adminService, QuestionDto questionDto, UsersRepository usersRepository, UsersService usersService) {
        this.adminService = adminService;
        this.questionDto = questionDto;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    @PostMapping("/addQuestion")
    @ResponseBody
    public ResponseEntity<String> adminAddQuestion(@RequestBody QuestionDto questionDto) {
        Question question = new Question(
                questionDto.getQuestionBody(),
                questionDto.getAnswerA(),
                questionDto.getAnswerB(),
                questionDto.getAnswerC(),
                questionDto.getAnswerD(),
                questionDto.getRightAnswer(),
                questionDto.getDifficulty()
        );
        Question addedQuestion = adminService.addQuestion(question);
        String responseMessage = String.format("Zapisano pytanie id nr %d o treści: %s",
                addedQuestion.getQuestionId(),
                addedQuestion.getQuestionBody());
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/getAllQuestions")
    @ResponseBody
    public Map<Long, String> getAllQuestions() {
        List<Question> questionsList = adminService.getQuestionsList();
        Map<Long, String> response = new HashMap<>();
        for (Question question : questionsList) {
            response.put(question.getQuestionId(), question.getQuestionBody());
        }
        return response;
    }

    @GetMapping("/getQuestionsByDifficulty")
    @ResponseBody
    public Map<Long, String> getQuestionsListByDifficulty(@RequestParam int difficulty) {
        List<Question> questionsList = adminService.getQuestionsListByDifficulty(difficulty);
        Map<Long, String> response = new HashMap<>();
        for (Question question : questionsList) {
            response.put(question.getQuestionId(), question.getQuestionBody());
        }
        return response;
    }

    @DeleteMapping("/deleteQuestion")
    ResponseEntity<String> deleteQuestionById(@RequestBody Map<String, Long> body) {
        Long questionId = body.get("questionId"); // Pobierz questionId z ciała zapytania
        adminService.deleteQuestion(questionId);
        String responseMessage = String.format("Usunięto pytanie id nr %d", questionId);
        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/activateUser")
    ResponseEntity<String> setUserAsActive(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        String userActivated = usersService.activateUser(userId);
        return ResponseEntity.ok(userActivated);
    }

}
