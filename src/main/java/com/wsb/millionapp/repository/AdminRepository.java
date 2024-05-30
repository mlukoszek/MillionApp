package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Question, Long> {

    Question save(Question question);

    void deleteById(Long id);

    List<Question> findAll();

    List<Question> findAllByDifficulty(int difficulty);

}
