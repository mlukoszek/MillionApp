package com.wsb.millionapp.controller;

import com.wsb.millionapp.to.QuestionDto;
import com.wsb.millionapp.service.QuestionsService;
import com.wsb.millionapp.to.UserDto;
import com.wsb.millionapp.service.UsersService;
import com.wsb.millionapp.domain.Result;
import com.wsb.millionapp.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.wsb.millionapp.controller.answerChecker.AnswerRequest;
import com.wsb.millionapp.controller.answerChecker.EvaluationResponse;

import java.time.LocalDate;
import java.util.Optional;


@Controller
@RequestMapping("/api")
@AllArgsConstructor
class QuestionsController {
    private QuestionsService questionsService;
    private UsersService usersService;
    private ResultService scoreService;

    @PostMapping("/drawQuestion")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
    public ResponseEntity<QuestionDto> getQuestion(@RequestParam int difficulty) {
        int adjustedDifficulty = difficulty + 1;
        QuestionDto questionDto = questionsService.drawQuestion(adjustedDifficulty);
        return ResponseEntity.ok(questionDto);
    }

    @PostMapping("/response")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<?> checkAnswer(@RequestBody AnswerRequest answerRequest) {
        String userAnswer = answerRequest.getUserAnswer();
        String goodAnswer = answerRequest.getCorrectAnswer();

        boolean result = questionsService.evaluate(userAnswer, goodAnswer);
        String responseMessage;

        if (result) {
            responseMessage = "Odpowiedź jest poprawna";
            if (answerRequest.getDifficulty()==12) {
                saveGameResult(12);
                return ResponseEntity.status(HttpStatus.OK).body(new EvaluationResponse(responseMessage + ". Gratulacje wygrałeś milion złotych", 12));
            }
        } else {
            responseMessage = "Odpowiedź jest błędna";
            int yourPrize = answerRequest.getDifficulty() - 1;
            saveGameResult(yourPrize);
            return ResponseEntity.status(HttpStatus.OK).body(new EvaluationResponse(responseMessage, yourPrize));
        }

        return ResponseEntity.ok(new EvaluationResponse(responseMessage, answerRequest.getDifficulty()-1));
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
            Long id = userDto.getId();
            LocalDate localDate = LocalDate.now();
            Result score = new Result(localDate, difficulty, id);
            scoreService.saveResult(score);
        }
    }

    public static String getLoggedUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
