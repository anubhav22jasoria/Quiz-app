package com.anubhav.quizapp.service;

import com.anubhav.quizapp.dao.QuestionDao;
import com.anubhav.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<String> updateQuestion(int questionId, Question question) throws Exception {
        Question oldQ = questionDao.findById(questionId).orElseThrow(() -> new Exception("Question id doesnt exist " +questionId));
        oldQ.setQuestiontext(question.getQuestiontext());
        oldQ.setCategory(question.getCategory());
        oldQ.setOptiona(question.getOptiona());
        oldQ.setOptionb(question.getOptionb());
        oldQ.setOptionc(question.getOptionc());
        oldQ.setOptiond(question.getOptiond());
        oldQ.setCorrectanswer(question.getCorrectanswer());
        questionDao.save(oldQ);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


//    public ResponseEntity<String> deleteQuestion(int questionId) {
//        questionDao.deleteById(questionId);
//        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
//    }
}
