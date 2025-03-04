package com.wsb.millionapp.service;

import com.wsb.millionapp.domain.Authorisation;
import com.wsb.millionapp.domain.User;
import com.wsb.millionapp.repository.AuthorisationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorisationService {
    private final AuthorisationRepository authorisationRepository;

    public String findPasswordByUsersId(Long userId){
        String password = authorisationRepository.getPasswordByUsersId(userId);
        return password;
    }

}

