package com.anubhav.questionservice.dao;

import com.anubhav.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "select questionid from question q where q.category=:category order by random() limit :noOfQuestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);
}
