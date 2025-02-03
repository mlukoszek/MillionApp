package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Authorisation;
import org.springframework.data.repository.CrudRepository;
import com.wsb.millionapp.domain.User;

import java.util.Optional;

public interface AuthorisationRepository extends CrudRepository<Authorisation, Integer> {

    String getPasswordByUsersId(Long usersId);

}
