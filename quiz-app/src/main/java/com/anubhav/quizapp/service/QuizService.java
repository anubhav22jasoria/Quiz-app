package com.anubhav.quizapp.service;

import com.anubhav.quizapp.dao.QuestionDao;
import com.anubhav.quizapp.dao.QuizDao;
import com.anubhav.quizapp.model.Question;
import com.anubhav.quizapp.model.QuestionWrapper;
import com.anubhav.quizapp.model.Quiz;
import com.anubhav.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<String> create(String category, int noOfQuestions, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,noOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
       Optional<Quiz> quiz = quizDao.findById(id);
       List<Question> questionList = quiz.get().getQuestions();
       List<QuestionWrapper> questionWrapperList = new ArrayList<>();

       for(Question q : questionList){
           QuestionWrapper qw = new QuestionWrapper(q.getQuestionid(),q.getQuestiontext(),q.getOptiona(),q.getOptionb(),q.getOptionc(),q.getOptiond());
           questionWrapperList.add(qw);
       }
       return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int ans = 0;
        for (Response rp : responses) {
            int qid = rp.getId();
            String ca = "";
            for (Question q : questions) {
                if (q.getQuestionid() == qid)
                    ca = q.getCorrectanswer();
            }

            if (ca.equals(rp.getResponse()))
                ans++;
        }
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }
}
