package com.wsb.millionapp.service;

import com.wsb.millionapp.domain.Authorisation;
import com.wsb.millionapp.domain.NewUserDto;
import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.repository.AuthorisationRepository;
import com.wsb.millionapp.to.UserDto;
import com.wsb.millionapp.mapper.UsersDtoMapper;
import com.wsb.millionapp.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final AuthorisationRepository authorisationRepository;

    @Transactional
    public User saveNewUser(NewUserDto newUserDto) {
        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setFirstName(newUserDto.getFirstName());
        user.setLastName(newUserDto.getLastName());
        user.setAge(newUserDto.getAge());
        user.setRole("USER");
        User addedUser = usersRepository.save(user);
        Long userId = addedUser.getId();
        String decodedPassword = new BCryptPasswordEncoder().encode(newUserDto.getPassword());
        Authorisation authorisation = new Authorisation();
        authorisation.setPassword(decodedPassword);
        authorisation.setUsersId(userId);
        authorisation.setCreatedAt(LocalDateTime.now());
        authorisationRepository.save(authorisation);
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

    public String activateUser(Long userId) {
        User user = usersRepository.findUserById(userId);
        if (user != null) {
            user.setActive(true);
            usersRepository.save(user);
            return "User activated: " + user.getUsername();
        }
        return "Aktywacja nieudana";
    }

    public String deActivateUser(Long userId) {
        User user = usersRepository.findUserById(userId);
        if (user != null) {
            user.setActive(false);
            usersRepository.save(user);
            return "User deactivated: " + user.getUsername();
        }
        return "Dezaktywacja nieudana";
    }

    public String deleteUser(Long id) {
        //usunąć najpierw Authorisation
        usersRepository.deleteById(String.valueOf(id));
        return String.format("Użytkownik o id $s usunięty", id);
    }
}