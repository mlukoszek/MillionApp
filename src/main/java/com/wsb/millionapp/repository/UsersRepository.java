package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
    User findUserByUsername(String userName);

    User findUserById(Long id);

    List<User> findAllByUsername(String userName);

}