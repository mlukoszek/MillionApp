package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Authorisation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.wsb.millionapp.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorisationRepository extends CrudRepository<Authorisation, Integer> {

    @Query("SELECT a.password FROM Authorisation a WHERE a.usersId = :usersId")
    String getPasswordByUsersId(@Param("usersId") Long usersId);

}
