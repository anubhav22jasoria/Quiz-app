package com.anubhav.quizapp.controller;

import com.anubhav.quizapp.model.Question;
import com.anubhav.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

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


//    @DeleteMapping("delete/{questionId}")
//    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId){
//        return questionService.deleteQuestion(questionId);
//    }
}
