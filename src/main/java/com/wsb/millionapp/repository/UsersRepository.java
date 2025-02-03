package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
    User findUserByUsername(String userName);

    User findUserById(Long id);

    List<User> findAllByUsername(String userName);

    Optional<User> findByUsername(String username);

}