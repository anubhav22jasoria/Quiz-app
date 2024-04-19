package com.anubhav.questionservice.service;

import com.anubhav.questionservice.dao.QuestionDao;
import com.anubhav.questionservice.model.Question;
import com.anubhav.questionservice.model.QuestionWrapper;
import com.anubhav.questionservice.model.Response;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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



    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName,int noOfQuestions){
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,noOfQuestions);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> qw = new ArrayList<>();

        for(Integer i:questionIds){
                Question q = questionDao.findById(i).get();
                QuestionWrapper questionWrapper = new QuestionWrapper();
                questionWrapper.setQuestionid(q.getQuestionid());
                questionWrapper.setQuestiontext(q.getQuestiontext());
                questionWrapper.setOptiona(q.getOptiona());
                questionWrapper.setOptionb(q.getOptionb());
                questionWrapper.setOptionc(q.getOptionc());
                questionWrapper.setOptiond(q.getOptiond());

                qw.add(questionWrapper);
        }


        return new ResponseEntity<>(qw,HttpStatus.OK);
    }


    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int ans = 0;

        for(Response response:responses){
            int id = response.getId();
            String choosedAnswer = response.getResponse();

            if(questionDao.findById(id).get().getCorrectanswer().equals(choosedAnswer))
                ans+=1;
        }

        return new ResponseEntity<>(ans,HttpStatus.OK);

    }
}
