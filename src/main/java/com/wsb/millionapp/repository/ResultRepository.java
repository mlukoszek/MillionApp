package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultRepository extends CrudRepository<Result, Long> {
    List<Result> findAll();

    Result save(Result score);

    @Query(value = """
        SELECT u.first_name, u.last_name, u.username, r.result, r.date
        FROM users u
        INNER JOIN results r ON u.id = r.users_id
        ORDER BY r.result DESC
    """, nativeQuery = true)
    List<Object[]> findAllResultsOrderedNative();
}

