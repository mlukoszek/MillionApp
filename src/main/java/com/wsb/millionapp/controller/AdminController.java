package com.wsb.millionapp.controller;

import com.wsb.millionapp.service.AdminService;
import com.wsb.millionapp.to.QuestionDto;
import com.wsb.millionapp.domain.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private AdminService adminService;
    private QuestionDto questionDto;

    public AdminController(AdminService adminService, QuestionDto questionDto) {
        this.adminService = adminService;
        this.questionDto = questionDto;
    }

    @RequestMapping("/addQuestion")
    String adminAddQuestion(@RequestParam String questionBody, String answerA, String answerB, String answerC, String answerD,
                            String rightAnswer, int difficulty, Model model) {
        Question question = new Question(questionBody, answerA, answerB, answerC, answerD, rightAnswer, difficulty);
        Question addedQuestion = adminService.addQuestion(question);
        model.addAttribute("addedQuestionBody", addedQuestion.getQuestionBody());
        model.addAttribute("addedQuestionId", addedQuestion.getQuestionId());
        return "adminTaskDone";
    }

    @RequestMapping("/getAllQuestions")
    String getQuestionsList(Model model) {
        List<Question> questionsList = adminService.getQuestionsList();
        model.addAttribute("list", questionsList);
        return "questionsList";
    }

    @RequestMapping("/getQuestionsByDifficulty")
    String getQuestionsListByDifficulty(@RequestParam int difficulty, Model model) {
        List<Question> questionsList = adminService.getQuestionsListByDifficulty(difficulty);
        model.addAttribute("list", questionsList);
        return "questionsList";
    }

    @RequestMapping("/delete")
    String deleteQuestionById(@RequestParam Long questionId, Model model) {
        List<Question> list = adminService.getQuestionsList();
        list.removeIf(questions -> !(questions.getQuestionId().equals(questionId)));
        System.out.println("Rozmiar listy " + list.size());
        adminService.deleteQuestion(questionId);
        model.addAttribute("list", list);
        return "deleted";
    }
}
