package com.wsb.millionapp.repository;

import com.wsb.millionapp.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends CrudRepository<Question, Long> {

    List<Question> findAllByDifficulty(int difficulty);

    Question getQuestionByQuestionId(int questionId);
}
