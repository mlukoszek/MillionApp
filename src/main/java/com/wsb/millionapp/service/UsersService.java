package com.wsb.millionapp.service;

import com.wsb.millionapp.domain.Authorisation;
import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.repository.AuthorisationRepository;
import com.wsb.millionapp.to.UserDto;
import com.wsb.millionapp.mapper.UsersDtoMapper;
import com.wsb.millionapp.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final AuthorisationRepository authenticationRepository;

    public User saveNewUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setRole("USER");
        User addedUser = usersRepository.save(user);
        Long userId = addedUser.getId();
        String decodedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        Authorisation authentication = new Authorisation();
        authentication.setPassword(decodedPassword);
        authentication.setUsersId(userId);
        authentication.setCreatedAt(LocalDateTime.now());
        authenticationRepository.save(authentication);
        return addedUser;
    }

    public Optional<UserDto> findUserByUsername(String userName) {
        User user = usersRepository.findUserByUsername(userName);
        return Optional.of(UsersDtoMapper.map(user));
    }

    public Optional<UserDto> findUserById(Long id) {
        User user = usersRepository.findUserById(id);
        return Optional.of(UsersDtoMapper.map(user));
    }

//    public String activateUser(Long userId){
//        User user = usersRepository.findUserById(userId);
//        if(user == null){
//            return "Nie znaleziono użytkownika o podanym ID";
//        }
//        user.setActive(true);
//        return usersRepository.updateUsersByUsername(user.getUsername(),user);
//    }
}