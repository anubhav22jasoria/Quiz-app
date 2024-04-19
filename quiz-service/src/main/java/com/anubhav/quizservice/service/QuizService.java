package com.anubhav.quizservice.service;

import com.anubhav.quizservice.dao.QuizDao;
import com.anubhav.quizservice.feign.QuizInterface;
import com.anubhav.quizservice.model.QuestionWrapper;
import com.anubhav.quizservice.model.Quiz;
import com.anubhav.quizservice.model.Response;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> create(String category, int noOfQuestions, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,noOfQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }











    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
       Quiz quiz = quizDao.findById(id).get();
       List<Integer> qids = quiz.getQuestionIds();
        List<QuestionWrapper> questionWrapperList = quizInterface.getQuestionsFromId(qids).getBody();
       return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }









    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
     return quizInterface.getScore(responses);
    }
}
