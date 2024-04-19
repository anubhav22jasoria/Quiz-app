package com.anubhav.questionservice.controller;

import com.anubhav.questionservice.model.Question;
import com.anubhav.questionservice.model.QuestionWrapper;
import com.anubhav.questionservice.model.Response;
import com.anubhav.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable int questionId,@RequestBody Question question) throws Exception {
        return questionService.updateQuestion(questionId,question);
    }


    //THIS ALL ARE FOR QUIZ SERVICE
    // generate
    // getQuestions(questionid)
    // getScore

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int noOfQuestions){
        return questionService.getQuestionsForQuiz(categoryName,noOfQuestions);
    }



    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){

        //Load Balancing
        //        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }



    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }










//    @DeleteMapping("delete/{questionId}")
//    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId){
//        return questionService.deleteQuestion(questionId);
//    }
}
