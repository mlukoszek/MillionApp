package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends CrudRepository<Result, Long> {
    List<Result> findAll();

    Result save(Result score);

    @Query(value = """
        SELECT u.first_name, u.last_name, u.username, r.result, r.date
        FROM users u
        INNER JOIN results r ON u.id = r.users_id
        ORDER BY r.result DESC LIMIT 10
    """, nativeQuery = true)
    List<Object[]> findAllResultsOrderedNative();

    @Query(value = """
    SELECT u.first_name, u.last_name, u.username, r.result, r.date
    FROM users u
    INNER JOIN results r ON u.id = r.users_id
    WHERE u.username = :username
    ORDER BY r.result DESC 
    LIMIT 20
""", nativeQuery = true)
    List<Object[]> findMyResultsOrderedNative(@Param("username") String username);
}

