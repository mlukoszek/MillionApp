package com.wsb.millionapp.controller;

import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.to.UserDto;
import com.wsb.millionapp.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
class UserController {
    private final UsersService usersService;

    @PostMapping("/registerUser")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        User addedUser = usersService.saveNewUser(userDto);

        String responseMessage = String.format("Zapisano użytkownika id nr %d o nazwie: %s",
                addedUser.getId(),
                addedUser.getUsername());
        return ResponseEntity.ok(responseMessage);
    }
}