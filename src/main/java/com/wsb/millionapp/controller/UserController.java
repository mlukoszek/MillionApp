package com.wsb.millionapp.controller;

import com.wsb.millionapp.domain.NewUserDto;
import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.to.UserDto;
import com.wsb.millionapp.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.wsb.millionapp.controller.QuestionsController.getLoggedUserName;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
class UserController {
    private final UsersService usersService;

    @PostMapping("/registerUser")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
    public ResponseEntity<String> registerUser(@RequestBody NewUserDto newUserDto) {
        User addedUser = usersService.saveNewUser(newUserDto);

        String responseMessage = String.format("Zapisano użytkownika id nr %d o nazwie: %s",
                addedUser.getId(),
                addedUser.getUsername());
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/getMyDetails")
    @ResponseBody
    public UserDto getUserByUsername() {
        String username = getLoggedUserName();
        Optional<UserDto> userDto = usersService.findUserByUsername(username);
        return userDto.orElseGet(UserDto::new);
    }
}