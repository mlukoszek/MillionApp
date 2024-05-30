package com.wsb.millionapp.controller;

import com.wsb.millionapp.to.QuestionDto;
import com.wsb.millionapp.service.QuestionsService;
import com.wsb.millionapp.to.UserDto;
import com.wsb.millionapp.service.UsersService;
import com.wsb.millionapp.domain.Score;
import com.wsb.millionapp.service.ScoreService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Controller
class QuestionsController {
    private QuestionsService questionsService;
    private QuestionDto questionDto;
    private UsersService usersService;
    private ScoreService scoreService;

    public QuestionsController(QuestionsService questionsService, QuestionDto questionDto, UsersService usersService,
                               ScoreService scoreService) {
        this.questionsService = questionsService;
        this.questionDto = questionDto;
        this.usersService = usersService;
        this.scoreService = scoreService;
    }

    @RequestMapping("/drawQuestion")
    String getQuestion(@RequestParam String start, HttpSession session, Model model) {
        if (session.getAttribute("difficulty") == null) {
            session.setAttribute("difficulty", 0);
            questionDto = questionsService.drawQuestion(1);
        } else {
            int diff = (int) session.getAttribute("difficulty");
            int temp = (parseInt(start) + diff + 1);
            questionDto = questionsService.drawQuestion(temp);
        }
        session.setAttribute("rightAnswer", questionDto.getRightAnswer());
        session.setAttribute("difficulty", questionDto.getDifficulty());
        session.setAttribute("questionId", questionDto.getQuestionId());
        model.addAttribute("questionBody", questionDto.getQuestionBody());
        model.addAttribute("answerA", questionDto.getAnswerA());
        model.addAttribute("answerB", questionDto.getAnswerB());
        model.addAttribute("answerC", questionDto.getAnswerC());
        model.addAttribute("answerD", questionDto.getAnswerD());
        return "question";
    }

    @RequestMapping("/response")
    String checkAnswer(@RequestParam String userAnswer, HttpSession session, Model model) {
        String goodAnswer = (String) session.getAttribute("rightAnswer");
        boolean result = questionsService.evaluate(userAnswer, goodAnswer);
        if (result) {
            model.addAttribute("result", "poprawna");
            if (session.getAttribute("difficulty").equals(12)) {
                saveGameResult(12);
            }
            return "evaluation";
        } else {
            model.addAttribute("result", "błedna");
            int yourPrize = (int) session.getAttribute("difficulty");
            model.addAttribute("prize", yourPrize - 1);
            saveGameResult(yourPrize);
            session.setAttribute("difficulty", 0);
            System.out.println(getLoggedUserName());
            return "end";
        }
    }

    @RequestMapping("/getStats")
    @ResponseBody
    LocalDate home() {
        return LocalDate.now();
    }

    void saveGameResult(int difficulty) {
        String name = getLoggedUserName();
        Optional<UserDto> optUserDto = usersService.findUserByUsername(name);
        if (optUserDto.isPresent()) {
            UserDto userDto = optUserDto.get();
            Score score = new Score(LocalDate.now(), difficulty, userDto.getId());
            scoreService.saveResult(score);
        }
    }

    String getLoggedUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
